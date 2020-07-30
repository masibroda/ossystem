package ua.ossystem.test;

import io.qameta.allure.Epic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import qa.driver.Driver;
import qa.runners.OssRunner;
import ua.ossystem.MainPage;

/**
 * @author Anatolii Masibroda <a.masibroda@corp.mail.ru>
 */
@Epic("Проверка на отсутствие тега <script> без атрибута src")
@RunWith(OssRunner.class)
public class CheckTagScript_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void first_test() {

    //Открваем страницу
    new MainPage(driver)
        .open()
        .tagScriptShouldBeContainsSrc();
  }
}
