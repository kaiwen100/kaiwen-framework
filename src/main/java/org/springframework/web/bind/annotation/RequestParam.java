package org.springframework.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author kaiwen
 * @create 2019-05-14 17:14
 * @since 1.0
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    /**
     * 表示参数的别名，必填
     * @return
     */
    String value();
}
