package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sveinbjorn on 2016-08-21.
 */

//TODO: SG: Make this a field inside the @Element annotation.
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {
    String name();
}
