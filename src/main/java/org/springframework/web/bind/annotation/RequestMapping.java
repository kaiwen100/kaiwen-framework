package org.springframework.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author kaiwen
 * @create 2019-05-14 17:13
 * @since 1.0
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    /**
     * 表示访问该方法的url
     * @return
     */
    String value() default "";
}
