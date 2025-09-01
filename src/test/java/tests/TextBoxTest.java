package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TextBoxPage;

public class TextBoxTest extends BaseTest {

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][]{
            // Positive cases
            {"SK Automation", "sk@test.com", "123 Main St", "456 Park Ave"},
            {"John Doe", "john@example.com", "789 Elm St", "101 Pine Ave"},

            // Negative / Edge cases
            {"", "sk@test.com", "123 Main St", "456 Park Ave"}, // Empty name
            {"Jane", "invalid-email", "123", "456"},           // Invalid email
            {"Very long name exceeding limits to test edge cases", "long@test.com", "Addr1", "Addr2"}
        };
    }

    @Test(dataProvider = "userData")
    public void testTextBox(String name, String email, String curr, String perm) {
        ExtentTest test = extent.createTest("TextBox Test for: " + name);
        TextBoxPage textBox = new TextBoxPage(driver);

        test.info("Filling text box with Name: " + name + ", Email: " + email);
        textBox.fillTextBox(name, email, curr, perm);

        String actualName = textBox.getNameOutput();
        String actualEmail = textBox.getEmailOutput();

        // Validate Name
        try {
            if (!name.isEmpty() && email.contains("@")) {
                Assert.assertTrue(actualName.contains(name));
                test.pass("Name validated successfully: " + actualName);
            } else {
                test.info("Negative/Edge Case - Name may not appear");
                Assert.assertTrue(actualName.isEmpty() || !actualName.contains(name));
                test.pass("Negative/Edge case handled correctly");
            }
        } catch (AssertionError e) {
            test.fail("Name validation failed: " + actualName);
        }

        // Validate Email
        try {
            if (!email.isEmpty() && email.contains("@")) {
                Assert.assertTrue(actualEmail.contains(email));
                test.pass("Email validated successfully: " + actualEmail);
            } else {
                test.info("Negative/Edge Case - Email may not appear");
                Assert.assertTrue(actualEmail.isEmpty() || !actualEmail.contains(email));
                test.pass("Negative/Edge case handled correctly");
            }
        } catch (AssertionError e) {
            test.fail("Email validation failed: " + actualEmail);
        }
    }
}
