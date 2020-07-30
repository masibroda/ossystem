package ua.ossystem.test;

import io.qameta.allure.Epic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import qa.driver.Driver;
import qa.runners.OssRunner;
import ua.ossystem.pages.MainPage;

/**
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
@Epic("Проверка на наличие файлов на странице")
@RunWith(OssRunner.class)
public class CheckJsCssFile_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void first_test() {

    //Открваем страницу
    new MainPage(driver)
        .open()
        //Проверяем файлы
        .fileShouldBePresent();
  }
}
