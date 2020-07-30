package qa.helper;

import static org.junit.Assert.assertNotEquals;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для управления окнами браузера
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class WindowsHelper {

  private WebDriver driver;

  public WindowsHelper(WebDriver driver) {
    this.driver = driver;
  }

  private static ThreadLocal<ConcurrentHashMap> sessionTHREAD_LOCAL = new ThreadLocal<>();

  public static ConcurrentHashMap getCurrentSession() {
    if (sessionTHREAD_LOCAL.get() == null) {
      sessionTHREAD_LOCAL.set(new ConcurrentHashMap());
    }
    return sessionTHREAD_LOCAL.get();
  }

  private String getMainWindow() {
    return (String) getCurrentSession().get("mainWindow");
  }

  private void setMainWindow() {
    if (getMainWindow() == null) {
      getCurrentSession().put("mainWindow", driver.getWindowHandle());
    }
  }

  @Step("Переключаемся на новое окно")
  public void switchOnNewWindow() {
    setMainWindow();

    List<String> windows = new ArrayList<>(driver.getWindowHandles());
    int current = windows.indexOf(getMainWindow());
    int last = windows.size() - 1;

    assertNotEquals("Новое окно для переключения не найдено", current, last);
    driver.switchTo().window(windows.get(last));
  }
}
