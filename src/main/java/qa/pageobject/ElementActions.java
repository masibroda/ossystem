package qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Методы взаимодействия с элементами
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public abstract class ElementActions {

  protected WebDriver driver = null;

  public ElementActions(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Возвращает actions
   * @return Actions
   */
  public Actions withAction() {
    return new Actions(driver);
  }

  /**
   * Возвращает элемент по локатору
   * @param by - локатор
   *
   * @return элемент
   */
  public WebElement $(By by) {
    return driver.findElement(by);
  }

  /**
   * Возвращает элемент по локатору
   * @param cssXpath - локатор
   *
   * @return элемент
   */
  public WebElement $(String cssXpath) {
    return $(cssXpathToBy(cssXpath));
  }

  /**
   * Возвращает список элементов по локатору
   * @param by - локатор
   *
   * @return список элементов
   */
  public List<WebElement> $$(By by) {
    return driver.findElements(by);
  }

  /**
   * Возвращает список элементов по локатору
   * @param cssXpath - локатор
   *
   * @return список элементов
   */
  public List<WebElement> $$(String cssXpath) {
    return $$(cssXpathToBy(cssXpath));
  }

  /**
   * Преобразовывает строку в стратегию поиска элемента
   * @param cssXpath - локатор ввиде строки
   *
   * @return стратегия поиска
   */
  public By cssXpathToBy(String cssXpath) {
    if (cssXpath.matches("\\(?/{1,2}.*?")) return By.xpath(cssXpath);
    return By.cssSelector(cssXpath);
  }

  /**
   * Вводит текст в элемент
   * @param element - элемент
   * @param str     - строка
   */
  public void typeInto(WebElement element, String str) {
    element.clear();
    element.sendKeys(str);
  }
}
