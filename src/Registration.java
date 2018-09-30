import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.rules.TestName;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Registration {
    private static WebDriver driver;

    public TestName name = new TestName();

    @BeforeClass
    public static void first() {
        String url = ReadFile.getData("URL");
        System.out.println("URL: " + url);
        String browser = ReadFile.getData("BROWSER");
        System.out.println("BROWSER: " + browser);
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            System.out.println("Opening url: " + url);
            driver.get(url);
        }
        if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            driver = new FirefoxDriver();
            System.out.println("Opening url: " + url);
            driver.get(url);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public static void registerOrSignIn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {  //  הרשמה לאתר
        TestName name = new TestName();

        System.out.println("Driver = " + driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement clickRegister = driver.findElement(By.className(Constants.REGISTER));
        System.out.println("Register element = " + clickRegister);
        clickRegister.isDisplayed();
        clickRegister.click();

        //new user registration
//        WebElement clickNewUser = driver.findElement(By.xpath(Constants.NEW_USER));
//        clickNewUser.click();
//        WebElement enterName = driver.findElement(By.xpath(Constants.NAME));
//        enterName.sendKeys("Avital");
//        WebElement enterEmail = driver.findElement(By.xpath(Constants.EMAIL));
//        enterEmail.sendKeys("AvitalMe1@gmail.com");
//        WebElement enterPassword = driver.findElement(By.xpath(Constants.PASSWORD));
//        enterPassword.sendKeys("AvitalMe11");
//        WebElement enterVerification = driver.findElement(By.xpath(Constants.VERIFICATION));
//        enterVerification.sendKeys("AvitalMe11");
//        enterVerification.click();
//        WebElement clickConsent = driver.findElement(By.xpath(Constants.CONSENT));
//        clickConsent.click();
//        WebElement submit = driver.findElement(By.xpath(Constants.SUBMIT));
//        submit.click();

        //existing user sign-in
        WebElement enterEmail = driver.findElement(By.xpath(Constants.EMAIL));
        enterEmail.sendKeys("AvitalMe1@gmail.com");
        WebElement enterPassword = driver.findElement(By.xpath(Constants.PASSWORD));
        enterPassword.sendKeys("AvitalMe11");

        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(SenderAndReceiverInfo.takeScreenShot(name.getMethodName() + "1", driver)).build());
        Thread.sleep(3000);

        WebElement submit = driver.findElement(By.xpath(Constants.SUBMIT));
        submit.click();
        Thread.sleep(1000);
    }
}



