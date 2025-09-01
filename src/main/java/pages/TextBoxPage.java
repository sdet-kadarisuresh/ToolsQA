package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBoxPage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(TextBoxPage.class);

    // Input fields
    By fullName = By.id("userName");
    By email = By.id("userEmail");
    By currentAddress = By.id("currentAddress");
    By permanentAddress = By.id("permanentAddress");
    By submitButton = By.id("submit");

    // Output fields
    By nameOutput = By.id("name");
    By emailOutput = By.id("email");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTextBox(String name, String mail, String currAddr, String permAddr) {
        try {
            log.info("Filling Name: " + name);
            driver.findElement(fullName).clear();
            driver.findElement(fullName).sendKeys(name);

            log.info("Filling Email: " + mail);
            driver.findElement(email).clear();
            driver.findElement(email).sendKeys(mail);

            log.info("Filling Current Address: " + currAddr);
            driver.findElement(currentAddress).clear();
            driver.findElement(currentAddress).sendKeys(currAddr);

            log.info("Filling Permanent Address: " + permAddr);
            driver.findElement(permanentAddress).clear();
            driver.findElement(permanentAddress).sendKeys(permAddr);

            // Wait for submit button & click using JS to avoid overlay issues
            WebElement submitBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(submitButton));
            log.info("Clicking Submit button");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

        } catch (ElementClickInterceptedException e) {
            log.error("Submit button click intercepted! Possibly due to overlay/ad: " + e.getMessage());
        } catch (TimeoutException e) {
            log.error("Submit button not clickable after wait: " + e.getMessage());
        } catch (Exception e) {
            log.error("Exception while filling TextBox: " + e.getMessage());
        }
    }
    public String getNameOutput() {
        try {
            WebElement nameEl = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(nameOutput));
            return nameEl.getText();
        } catch (TimeoutException e) {
            log.warn("Name output not found! Possibly empty input.");
            return "";
        }
    }


   

    public String getEmailOutput() {
    	  try {
              WebElement nameEl = new WebDriverWait(driver, Duration.ofSeconds(5))
                      .until(ExpectedConditions.visibilityOfElementLocated(emailOutput));
              return nameEl.getText();
          } catch (TimeoutException e) {
              log.warn("Name output not found! Possibly empty input.");
              return "";
          }
      }
}
    	
    