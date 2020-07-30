package qa.pageobject;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import qa.utils.DefaultUrl;
import qa.utils.PageUrl;
import qa.waiter.Waiter;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Навигация
 * @param <T> - тип страницы
 *
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class NavigationObject<T> extends PageActions {

  protected Waiter waiter;

  public NavigationObject(WebDriver driver) {
    super(driver);
    this.waiter = new Waiter(driver);
  }

  /**
   * Открывает страницу
   */
  public T open() {
    String defaultUrl = getDeclaredDefaultUrl();
    if (defaultUrl.startsWith("http")) {
      driver.get(defaultUrl);
    } else {
      driver.get(getBaseUrl() + defaultUrl);
    }
    assert isCurrentPageAt() :
        String.format("Страница должна иметь верный URL <%s>", getClass().getAnnotation(PageUrl.class).value());
    int actualCode = getCodeHttpRequest();
    assert actualCode == 200 :
        String.format("Страница вернула http код ответа %d", actualCode);
    return (T) page(getClass());
  }

  /**
   * Возвращает URL из аннотации DefaultBaseUrl
   * @return URL
   */
  private String getDeclaredDefaultUrl() {
    DefaultUrl urlAnnotation = getClass().getAnnotation(DefaultUrl.class);
    if (getClass().isAnnotationPresent(DefaultUrl.class)) {
      if (urlAnnotation.start().isEmpty()) return urlAnnotation.value();
      return urlAnnotation.value().replace("//", "//" + urlAnnotation.start());
    }
    return "";
  }

  /**
   * Возвращает webdriver.base.url
   * @return Base URL
   */
  private String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
  }

  /**
   * Проверяет, что открыта страница с ожидаемым URL
   * @return boolean
   */
  public boolean isCurrentPageAt() {
    PageUrl page = getClass().getAnnotation(PageUrl.class);
    if (getClass().isAnnotationPresent(PageUrl.class)) {
      return page.urls().length == 0 ? isValidatePage(page.value()) :
          Stream.of(page.urls()).anyMatch(this::isValidatePage);
    }
    return true;
  }

  /**
   * Проверка URL страницы
   * @param url - урл страницы
   *
   * @return boolean
   */
  private boolean isValidatePage(String url) {
    if (url.startsWith("http")) {
      return waiter.waitForCondition(webDriver -> driver.getCurrentUrl().replaceAll("\\?.*", "").matches(url));
    } else {
      String domain = "http[s]?://.*?(?::\\\\d+)?";
      return waiter.waitForCondition(webDriver -> driver.getCurrentUrl().replaceAll("\\?.*", "").matches(domain + url));
    }
  }

  /**
   * Проверяет, что страница возвращает ожидаемый код HTTP запроса
   * @return код http запроса
   */
  public int getCodeHttpRequest() {
    Response response = given().relaxedHTTPSValidation().when().get(driver.getCurrentUrl(), new HashMap<>());
    return response.getStatusCode();
  }
}
