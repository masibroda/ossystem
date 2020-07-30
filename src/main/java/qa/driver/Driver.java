package qa.driver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для инжекции драйвера в test suite
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Driver {
  DriverTypeData type() default DriverTypeData.Chrome;
}
