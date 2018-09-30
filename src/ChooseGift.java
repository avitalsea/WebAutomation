import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class ChooseGift {
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
    }

    @Test
    public static void pickGift(WebDriver driver, ExtentTest test) throws InterruptedException, IOException { // בחירת העסק
        TestName name = new TestName();

        HomePage.selectBuyMe(driver, test);
        Thread.sleep(1000);
        Assert.assertEquals("https://buyme.co.il/search?budget=2&category=16&region=13", driver.getCurrentUrl());

        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(SenderAndReceiverInfo.takeScreenShot(name.getMethodName() + "3", driver)).build());
        Thread.sleep(3000);

        WebElement makeChooseForChef = driver.findElement(By.xpath(Constants.CLARO));
        makeChooseForChef.click();
        WebElement giftValue = driver.findElement(By.xpath(Constants.VALUE));
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(SenderAndReceiverInfo.takeScreenShot(name.getMethodName() + "4", driver)).build());
        Thread.sleep(1000);
        giftValue.click();
    }
}
