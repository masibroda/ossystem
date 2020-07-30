package ua.ossystem.pages;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.pageobject.BasePage;
import qa.utils.DefaultUrl;
import qa.utils.PageUrl;

/**
 * Главная страница
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
@PageUrl("/")
@DefaultUrl("/")
public class MainPage extends BasePage<MainPage> {

  //Локаторы для работы со страницей
  private String scriptWithOutSrcLocator = "//script[not(@src)]";

  //Локаторы метатегов
  private String metaKeywordsLocator = "//meta[@name='keywords']";
  private String metaDescriptionLocator = "//meta[@property='description']";
  private String titleLocator = "//title";
  private String headerLocator = "//h1";

  //Локаторы id элементов
  private String idElementLocator = "//*[@id]";
  private String elementByIdLocator = "//*[@id='%s']";

  //Локаторы файлов
  private String cssFileLocator = "//link[contains(@href, '.css')]";
  private String jsFileLocator = "//script[contains(@src, '.js')]";

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @Step("Открываем главную страницу")
  public MainPage open() {
    super.open();
    return this;
  }

  @Step("Проверяем отсутствие тега <script> без атрибута src")
  public MainPage tagScriptShouldBeContainsSrc() {
    assertThat(waiter.waitForElementNotPresent(By.xpath(scriptWithOutSrcLocator)))
        .as("На странице не должно быть тегов <script> без атрибута src")
        .isTrue();
    return this;
  }

  @Step("Проверяем наличие метатегов")
  public MainPage metaTagShouldBePresent() {
    assertThat(waiter.waitForElementPresent(By.xpath(metaKeywordsLocator)))
        .as("Метатег keywords должен отображаться на странице")
        .isTrue();
    assertThat(waiter.waitForElementPresent(By.xpath(metaDescriptionLocator)))
        .as("Метатег description должен отображаться на странице")
        .isTrue();
    assertThat(waiter.waitForElementPresent(By.xpath(titleLocator)))
        .as("Метатег title должен отображаться на странице")
        .isTrue();
    return this;
  }

  @Step("Проверяем что на странице количество зоголовок не больше {i}")
  public MainPage sizeHeaderShouldBeNotMoreThan(int i) {
    assertThat($$(headerLocator).size() <= i)
        .as("На странице должно быть заголовков не больше чем %d", i)
        .isTrue();
    return this;
  }

  @Step("Проверяем отсутствие дубликтов id")
  public MainPage idElementShouldBeNotDuplicate() {
    int size = $$(idElementLocator).size();
    for (int i = 0; i < size; i++) {
      String id = $$(idElementLocator).get(i).getAttribute("id");
      String locator = String.format(elementByIdLocator, id);
      assertThat($$(locator).size() == 1)
          .as("На странице не должно быть дубликатов id <%s>", id)
          .isTrue();
    }
    return this;
  }

  @Step("Проверяем наличие файлов js на странице")
  private MainPage jsFileShouldBePresent() {
    assertThat($$(jsFileLocator).size() >= 1)
        .as("На странице должен быть хотя бы один файл js")
        .isTrue();
    return this;
  }

  @Step("Проверяем наличие файлов css на странице")
  private MainPage cssFileShouldBePresent() {
    assertThat($$(cssFileLocator).size() >= 1)
        .as("На странице должен быть хотя бы один файл css")
        .isTrue();
    return this;
  }

  @Step("Проверяем наличие файлов на странице")
  public MainPage fileShouldBePresent() {
    jsFileShouldBePresent();
    cssFileShouldBePresent();
    return this;
  }
}
