package tests;

import base.BaseTest;
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
	        {"A very very long name exceeding normal limits to test edge cases", 
	         "long@test.com", "Address 1", "Address 2"}       // Very long input
	    };
	}

	@Test(dataProvider = "userData")
	public void testTextBoxScenarios(String name, String email, String curr, String perm) {
	    TextBoxPage textBox = new TextBoxPage(driver);
	    textBox.fillTextBox(name, email, curr, perm);

	    String actualName = textBox.getNameOutput();
	    String actualEmail = textBox.getEmailOutput();

	    // Positive case validation
	    if(!name.isEmpty() && email.contains("@")) {
	        Assert.assertTrue(actualName.contains(name), "Name not found in output!");
	        Assert.assertTrue(actualEmail.contains(email), "Email not found in output!");
	    } else {
	        // Negative / edge case: should not submit successfully
	        Assert.assertTrue(actualName.isEmpty() || !actualName.contains(name), "Unexpected name in output!");
	    }
	}
}
