package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxPage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(TextBoxPage.class);


    // Input fields
    By fullName = By.id("userName");
    By email = By.id("userEmail");
    By currentAddress = By.id("currentAddress");
    By permanentAddress = By.id("permanentAddress");
    By submitButton = By.id("submit");

    // Output fields (more precise locators)
    By nameOutput = By.id("name");
    By emailOutput = By.id("email");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTextBox(String name, String mail, String currAddr, String permAddr) {
    	 log.info("Filling Full Name: " + name);
         driver.findElement(fullName).clear();
         driver.findElement(fullName).sendKeys(name);

         log.info("Filling Email: " + mail);
         driver.findElement(email).clear();
         driver.findElement(email).sendKeys(mail);

         driver.findElement(currentAddress).clear();
         driver.findElement(currentAddress).sendKeys(currAddr);
         driver.findElement(permanentAddress).clear();
         driver.findElement(permanentAddress).sendKeys(permAddr);

         log.info("Clicking Submit button");
         driver.findElement(submitButton).click();
    }

    // Get individual outputs for precise validation
    public String getNameOutput() {
        return driver.findElement(nameOutput).getText(); // "Name: SK Automation"
    }

    public String getEmailOutput() {
        return driver.findElement(emailOutput).getText();
    }
}
