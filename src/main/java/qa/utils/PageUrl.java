package qa.utils;

import java.lang.annotation.*;

/**
 * Аннотация навигационной валидации
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface PageUrl {
  String value() default "";

  String[] urls() default {};
}
