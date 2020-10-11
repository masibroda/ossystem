package qa.driver.settings;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

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
    chromeOptions.setExperimentalOption("w3c", false);

    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    chromeOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));

    return chromeOptions;
  }
}
