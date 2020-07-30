package qa.driver.exceptions;

import qa.driver.DriverTypeData;

/**
 * Ошибка неподдерживаемый тип WebDriver
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public class WebDriverTypeException extends Exception {

  public WebDriverTypeException(DriverTypeData driverTypeData) {
    super(String.format(ExceptionMessages.WebDriverType.getTemplate(), driverTypeData.getType()));
  }
}
