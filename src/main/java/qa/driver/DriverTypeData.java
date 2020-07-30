package qa.driver;

/**
 * ТИпы поддерживаемых WebDriver
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public enum DriverTypeData {
  Chrome("chrome"),
  Remote("remote");

  private String type;

  DriverTypeData(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
