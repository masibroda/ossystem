package qa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.driver.exceptions.WebDriverTypeException;
import qa.driver.settings.ChromeSettings;
import qa.driver.settings.RemoteSettings;

import java.net.URL;

/**
 * Фабрика WebDriver
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public final class DriverFactory {

  private DriverTypeData driverTypeData;
  private URL url;

  public DriverFactory(DriverTypeData driverTypeData) {
    this.driverTypeData = driverTypeData;
  }

  public DriverFactory(DriverTypeData driverTypeData, URL url) {
    this.driverTypeData = driverTypeData;
    this.url = url;
  }

  public WebDriver getDriverInstance() throws WebDriverTypeException {
    switch (driverTypeData) {
      case Chrome: {
        return new ChromeDriver(new ChromeSettings().getChromeOptions());
      }
      case Remote: {
        return new RemoteWebDriver(url, new RemoteSettings().getChromeOptions());
      }
      default:
        throw new WebDriverTypeException(driverTypeData);
    }
  }
}
