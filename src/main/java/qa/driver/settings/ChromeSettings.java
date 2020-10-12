package qa.driver.settings;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

/**
 * Настройки GoogleChrome WebDriver
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class ChromeSettings {

  public ChromeOptions getChromeOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("start-maximized");
    chromeOptions.addArguments("--no-first-run");
    chromeOptions.addArguments("--homepage=about:blank");
    System.setProperty("webdriver.chrome.silentOutput", "true");

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

    chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

    chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    chromeOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));

    return chromeOptions;
  }
}
