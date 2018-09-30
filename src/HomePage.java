import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HomePage {
    private static WebDriver driver;


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
    public static void selectBuyMe(WebDriver driver, ExtentTest test) throws InterruptedException, IOException { // בחירת פרמטרים למתנה: מחיר, אזור וקטגוריה
        TestName name = new TestName();

        Registration.registerOrSignIn(driver, test);
        System.out.println("Trying to click on סכום");
        WebElement chooseAmount = driver.findElement(By.xpath(Constants.AMOUNT));
        chooseAmount.isDisplayed();
        chooseAmount.click();
        System.out.println("Success");
        Thread.sleep(1000);
        WebElement makeChoose = driver.findElement(By.xpath(Constants.CHOICE));
        makeChoose.isDisplayed();
        makeChoose.click();
        System.out.println("choose location");
        WebElement chooseArea = driver.findElement(By.xpath(Constants.AREA));
        chooseArea.isDisplayed();
        chooseArea.click();
        WebElement selectArea = driver.findElement(By.xpath(Constants.TEL_AVIV));
        selectArea.isDisplayed();
        selectArea.click();
        System.out.println("Choose category");
        WebElement chooseCategory = driver.findElement(By.xpath(Constants.CATEGORY));
        chooseCategory.isDisplayed();
        chooseCategory.click();
        System.out.println("Choose chef");
        WebElement selectCategory = driver.findElement(By.xpath(Constants.CHEF));
        selectCategory.isDisplayed();
        selectCategory.click();

        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(SenderAndReceiverInfo.takeScreenShot(name.getMethodName() + "2", driver)).build());
        Thread.sleep(2000);

        System.out.println("Press submit");
        WebElement submit = driver.findElement(By.xpath(Constants.SUBMIT));
        System.out.println("Press submit - done, submit button = " + submit);
        submit.click();
    }
}



