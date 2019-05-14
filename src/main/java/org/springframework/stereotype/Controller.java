package org.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author kaiwen
 * @create 2019-05-14 17:08
 * @since 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";
}
