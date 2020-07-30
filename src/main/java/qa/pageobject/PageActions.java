package qa.pageobject;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Создание объекта страницы
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class PageActions extends ElementActions {

  public PageActions(WebDriver driver) {
    super(driver);
  }

  /**
   * Преобразование класса в тип объекта
   * @param o     - объект
   * @param clazz - класс
   * @param <T>   - тип класса
   *
   * @return типизированный класс
   */
  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }

  /**
   * Создание объекта страницы с инициализатором по умолчанию
   * @param clazz - класс страницы
   * @param <T>   - тип страницы
   *
   * @return объект созданной страницы
   */
  public <T> T page(Class<T> clazz) {
    try {
      Constructor constructor = clazz.getConstructor(WebDriver.class);

      return convertInstanceOfObject(constructor.newInstance(driver), clazz);

    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }
}
