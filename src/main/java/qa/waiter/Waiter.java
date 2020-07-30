package qa.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.pageobject.ElementActions;

/**
 * Набор стандартных ожиданий
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class Waiter extends ElementActions {

  long implicitlyWait = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", "5000")) / 1000;

  public Waiter(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * Ожидание по слушателю
   * @param condition - слушатель ожидания
   *
   * @return boolean
   */
  public boolean waitForCondition(ExpectedCondition condition) {
    WebDriverWait webDriverWait = new WebDriverWait(driver, implicitlyWait);
    try {
      webDriverWait.until(condition);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * Ожидание, что элемент не отображается на странице
   * @param by - локатор элемента
   *
   * @return boolean
   */
  public boolean waitForElementNotPresent(By by) {
    return waitForCondition(webDriver -> $$(by).size() == 0);
  }

  /**
   * Ожидание, что элемент отображается на странице
   * @param by - локатор элемента
   *
   * @return boolean
   */
  public boolean waitForElementPresent(By by) {
    return waitForCondition(webDriver -> $$(by).size() > 0);
  }
}
