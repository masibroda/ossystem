package qa.runners;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import qa.driver.Driver;
import qa.driver.DriverFactory;
import qa.driver.exceptions.WebDriverTypeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class OssRunner extends BlockJUnit4ClassRunner {

  public OssRunner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  private WebDriver driver = null;

  @Override
  public void runChild(final FrameworkMethod method, RunNotifier notifier) {
    this.driver = newDriver();
    try {
      super.runChild(method, notifier);
    } finally {
      driver.close();
      driver.quit();
    }
  }

  private Set<Field> getAnnotatedFields(Class<? extends Annotation> ann) {
    Set<Field> set = new HashSet<>();
    Class<?> c = getTestClass().getJavaClass();
    while (c != null) {
      for (Field field : c.getDeclaredFields()) {
        if (field.isAnnotationPresent(ann)) {
          set.add(field);
        }
      }
      c = c.getSuperclass();
    }
    return set;
  }

  @Override
  public Statement methodInvoker(final FrameworkMethod method, final Object test) {
    injectDriverInto(driver, test);
    return super.methodInvoker(method, test);
  }

  private void injectDriverInto(WebDriver driver, Object test) {
    Set<Field> sd = getAnnotatedFields(Driver.class);
    for (Field field : sd) {
      try {
        field.setAccessible(true);
        field.set(test, driver);
      } catch (IllegalAccessException e) {
        throw new Error(String.format("Could not access or set web driver field: %s - is this field public?",
            field.getName()), e);
      }
    }
  }

  private URL getRemoteUrl() {
    String remoteUrlProp = System.getProperty("webdriver.remote.url");
    if (remoteUrlProp != null && !remoteUrlProp.isEmpty()) {
      try {
        return new URL(remoteUrlProp);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public WebDriver newDriver() {
    Set<Field> fields = getAnnotatedFields(Driver.class);
    URL url = getRemoteUrl();

    for (Field field : fields) {
      Driver injectDriver = field.getAnnotation(Driver.class);

      DriverFactory driverFactory = url == null
          ? new DriverFactory(injectDriver.type()) :
          new DriverFactory(injectDriver.type(), url);

      try {
        return driverFactory.getDriverInstance();
      } catch (WebDriverTypeException ex) {
        ex.printStackTrace();
      }
    }

    return null;
  }
}
