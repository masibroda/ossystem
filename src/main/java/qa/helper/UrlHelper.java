package qa.helper;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

/**
 * Вспомогательные методы работы с адресной строкой
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class UrlHelper {

  private WebDriver driver;

  public UrlHelper(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Проверяем, что URL текущей страницы содержит {linkUrl}")
  public void currentPageUrlShouldBeContains(String linkUrl) {
    assertThat(driver.getCurrentUrl())
        .as("URL страницы должен содержать %s", linkUrl)
        .containsIgnoringCase(linkUrl);
  }
}
