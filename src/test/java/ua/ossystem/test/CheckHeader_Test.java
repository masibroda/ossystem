package ua.ossystem.test;

import io.qameta.allure.Epic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import qa.driver.Driver;
import qa.runners.OssRunner;
import ua.ossystem.pages.MainPage;

/**
 * Проверка на наличие мета-тегов title, description и keywords
 * @author Anatolii Masibroda <a.masibroda@corp.mail.ru>
 */
@Epic("Проверка количества заголовков H1")
@RunWith(OssRunner.class)
public class CheckHeader_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void first_test() {

    //Открваем страницу
    new MainPage(driver)
        .open()
        .sizeHeaderShouldBeNotMoreThan(1);
  }
}
