package qa.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация URL по умолчанию
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultUrl {
  String value();

  String start() default "";
}
