package org.shield.mybatis.plugin;

import java.lang.annotation.*;

/**
 * @author Yue.Gao@geely.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Collection {
}
