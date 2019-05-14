package org.springframework.web.servlet;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author kaiwen
 * @create 2019-05-14 17:16
 * @since 1.0
 **/
public class DispatcherServlet extends HttpServlet {

    // 包扫描路径
    private String basePackage = "";

    // 类的全路径名集合
    private List<String> classNames = new ArrayList<String>();

    /**
     * bean 容器
     */
    private Map<String,Object> beanContain = new HashMap<String, Object>();

    // 保存url和controller对象以及具体方法的映射关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();
    private Map<String,Object> controllerMap = new HashMap<String, Object>();


    /**
     * 前提条件(初始化 触发 -- 不是spring功能，是servlet的功能)
     * @param config
     * @throws ServletException
     */
    @Override
    public void init (ServletConfig config) throws ServletException {
        try {
            // 1.加载配置文件
            doLoadConfig(config.getInitParameter("contextConfigLocation"));
            // 2.初始化所有相关联的类，扫描用户设定的包下面的所有的类
            doScanner(basePackage);

            // 3.拿到扫描到的类，通过反射机制，实例化，并且放到ioc容器中(k-v beanName-bean) beanName默认是首字母小写
            doInstance();

            //4.实现注入，主要针对service注入到controller
            doIoc();

            //5.初始化HandlerMapping(将url和method对应上)
            initHandlerMapping();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 处理请求
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch (HttpServletRequest req, HttpServletResponse resp) {

    }

    private void doLoadConfig (String location) throws Exception {
        if (location.startsWith("classpath:")) {
            location = location.replace("classpath:","");
        } else if(location.contains("/")) {
            int lastSplitIndex = location.lastIndexOf('/');
            location = location.substring(lastSplitIndex + 1, location.length());
        }
        // 把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        Class<? extends DispatcherServlet> aClass = this.getClass();
        URL file = aClass.getResource(location);
        System.out.println(file.toString());
        Document read_doc = new SAXBuilder().build(file);
        Element root = read_doc.getRootElement();
        for (Iterator<Element> itr = root.getChildren().iterator(); itr.hasNext();) {
            Element e = itr.next();
            if ("http://www.springframework.org/schema/context".equals(e.getNamespace().getURI())) {
                basePackage = e.getAttribute("base-package").getValue();    //TODO 获取扫描路径
            }

            if ("http://www.springframework.org/schema/mvc".equals(e.getNamespace().getURI())
            && "annotation-driven".equals(e.getNamespace())) {
                System.out.println("开启注解");
            }
        }
    }

    private void doScanner (String basePackage) {
    }

    private void doInstance () {

    }

    private void doIoc () {

    }

    private void initHandlerMapping () {

    }
}
