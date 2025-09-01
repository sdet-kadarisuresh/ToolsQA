package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public WebDriver driver;
    public ExtentReports extent;

    @BeforeClass
    public void setup() {
        // Initialize ExtentReports
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver();

        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        if(driver != null) driver.quit();
        if(extent != null) extent.flush(); // generate report
    }
}
