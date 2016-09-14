package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sveinbjorn on 2016-08-21.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Element {
    String selector() default "";
}
