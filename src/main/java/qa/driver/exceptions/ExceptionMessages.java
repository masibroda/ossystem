package qa.driver.exceptions;

/**
 * Типы ошибок
 * @author Anatolii Masibroda <masibrod@mail.ru>
 */
public enum ExceptionMessages {
  WebDriverType("WebDriver type %s doesn't supported");

  private String template;

  ExceptionMessages(String template) {
    this.template = template;
  }

  public String getTemplate() {
    return template;
  }
}
