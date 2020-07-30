package qa.pageobject;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

/**
 * Базовая страница
 * @author Anatolii Masibroda <a.masibroda@corp.mail.ru>
 */
public abstract class BasePage<T> extends NavigationObject<T> {

  public BasePage(WebDriver driver) {
    super(driver);
  }

  @Step("Проверяем, что страница открыта")
  public T pageShouldBeOpened() {
    assertThat(isCurrentPageAt()).as("Страница %s должна быть открыта", this.getClass().getName())
        .isTrue();
    return (T) this;
  }
}
