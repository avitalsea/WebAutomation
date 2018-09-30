import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SenderAndReceiverInfo {
    @Rule
    public TestName name = new TestName();
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebDriver driver;

    @BeforeClass
    public static void first() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Sender and receiver information", "Fill sender and receiver information");
        test.log(Status.INFO, "@Before class");


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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void sendReceiver() throws InterruptedException, IOException { // פרטים של מקבל ומוסר המתנה
        ChooseGift.pickGift(driver, test);
        WebElement chooseGiftForSomeone = driver.findElement(By.xpath(Constants.SOMEONE));
        chooseGiftForSomeone.isDisplayed();
        chooseGiftForSomeone.click();

        WebElement addRecipientName = driver.findElement(By.xpath(Constants.RECIPIENT));
        addRecipientName.clear();
        addRecipientName.sendKeys("Vered");


        WebElement addSenderName = driver.findElement(By.xpath(Constants.SENDER));
        addSenderName.isDisplayed();
        addSenderName.clear();
        addSenderName.sendKeys("Avital");

        WebElement selectOccasion = driver.findElement(By.xpath(Constants.OCCASION));
        selectOccasion.isDisplayed();
        selectOccasion.click();


        WebElement selectBirthday = driver.findElement(By.xpath(Constants.BIRTHDAY));
        selectBirthday.isDisplayed();
        selectBirthday.click();

        WebElement addToastToGift = driver.findElement(By.xpath(Constants.TOAST));
        addToastToGift.isDisplayed();
        addToastToGift.clear();
        addToastToGift.sendKeys("מזל טוב ליום הולדתך מותק! רק אושר שמחה ואהבה");

        try {
            //check if an image is already loaded, and if so - remove it.
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement removeImage = driver.findElement(By.className(Constants.IMAGE_REMOVE));
            if (removeImage.isDisplayed()) {
                removeImage.click();
            }
        } catch (Exception exp) {
            System.out.println("Did not find (or failed to) remove previous image.");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement addImage = driver.findElement(By.xpath(Constants.IMAGE));
        addImage.click();
        addImage.clear();
        addImage.sendKeys("vered.jpg");

        WebElement selectSendNow = driver.findElement(By.xpath(Constants.SEND_NOW));
        selectSendNow.isDisplayed();
        selectSendNow.click();


        WebElement selectEnvelope = driver.findElement(By.className(Constants.ENVELOPE));
        selectEnvelope.isDisplayed();
        selectEnvelope.click();

        WebElement addEmailReceiverAddress = driver.findElement(By.xpath(Constants.ADDRESS));
        addEmailReceiverAddress.isDisplayed();
        addEmailReceiverAddress.clear();
        addEmailReceiverAddress.sendKeys("veredvered@gmail.com");


        WebElement save = driver.findElement(By.className(Constants.SAVE));
        save.isDisplayed();
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(name.getMethodName() + "5", driver)).build());
        Thread.sleep(1000);
        save.click();

        WebElement clickBuy = driver.findElement(By.xpath(Constants.BUY));
        clickBuy.isDisplayed();
        clickBuy.click();

    }

    @AfterClass
    public static void afterClass() {
        test.log(Status.INFO, "@After test " + "After test method");
        driver.quit();
        // build and flush report
        extent.flush();
    }

    public static String takeScreenShot(String ImagesPath, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile); // jar: commons-codec-1.10
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }
}
