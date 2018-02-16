package ee.cgi.sample.helpers;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

    /**
     * This class defines WebDriver instance used and driver related helper functions
     *
     */

public class WebDriver {

    private org.openqa.selenium.WebDriver driver;
    private Set<Cookie> storedSession;

    private Logger logger = Logger.getLogger(WebDriver.class);

    private static WebDriver _instance = new WebDriver();

    private WebDriver() {}

    public static WebDriver get() {
        return _instance;
    }

    public org.openqa.selenium.WebDriver getWebDriver() {
        return driver;
    }

    public TakesScreenshot takesScreenshot() {
        return (TakesScreenshot) driver;
    }

        /**
         * This method creates new WebDriver instance
         *
         */

    public void openBrowser() {
        if(driver == null) {
            setDriverPath();
            //openFFBrowser();
            openChromeBrowser();
            setDefaultImplicitTimeout();
        }
    }

    public void openFFBrowser(){
        driver = new FirefoxDriver();
    }

    public void openChromeBrowser(){
        driver = new ChromeDriver();
    }

    /**
     * Method for waiting element to be present
     *
     * @param by element definition
     * @param timeOutInSeconds how many seconds to wait for the element
     *
     * @return element or null
     */

    public WebElement waitElementToBePresent(final By by,int timeOutInSeconds) {
        return useWebDriverWaitWihtImplicitWait(
                new WebDriverWait(driver, timeOutInSeconds),
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    /**
     * Method for waiting element to be clickable
     *
     * @param element element definition
     * @param timeOutInSeconds how many seconds to wait for the element
     *
     * @return element or null
     */

    public WebElement waitElementToBeClickable(WebElement element,long timeOutInSeconds) {
        return useWebDriverWaitWihtImplicitWait(
                new WebDriverWait(getWebDriver(), timeOutInSeconds),
                ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method for waiting element to be visible
     *
     * @param element element definition
     * @param timeOutInSeconds how many seconds to wait for the element
     *
     * @return element or null
     */

    public WebElement waitElementToBeVisible(WebElement element,long timeOutInSeconds) {
        return useWebDriverWaitWihtImplicitWait(
                new WebDriverWait(getWebDriver(), timeOutInSeconds),
                ExpectedConditions.visibilityOf(element));
    }

    /**
     * Helper method for implicit wait
     *
     * @param wait WebDriver wait
     * @param expectedCondition condition
     *
     * @return element or null
     */

    private WebElement useWebDriverWaitWihtImplicitWait(WebDriverWait wait, ExpectedCondition<WebElement> expectedCondition) {
        // http://stackoverflow.com/questions/15164742/combining-implicit-wait-and-explicit-wait-together-results-in-unexpected-wait-ti/15174978#15174978

        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()

            WebElement element = wait.until(expectedCondition);

            return element; //return the element
        } catch (Exception e) {
            logger.info("Wait condition not fulfilled. \"" + expectedCondition.toString() + "\"");
        }
        finally {
            setDefaultImplicitTimeout(); //reset implicitlyWait
        }

        return null;
    }

    /**
     * Method for waiting for the JavaScript alert and accepting the alert
     *
     * @param waitTime defines duration of the Waiting operation
     *
     */

    public void checkAlert(Integer waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTime);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getWebDriver().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            logger.info("Failed to accept alert!");
        }
    }
    /**
     * Method for waiting for the page to load
     *
     *
     */
    public void pageLoadTimeout() {
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    }

    /**
     * Method for refreshing the page
     *
     *
     */

    public void refreshPage()  {
        driver.navigate().refresh();
    }

    /**
     * Method for refreshing the page and waiting for the specified WebElement
     *
     * @param element element which we are going to wait for
     * @param timeOutInSeconds how long to wait
     *
     */

    public void refreshAndWaitFor(WebElement element, long timeOutInSeconds) {
        refreshPage();
        waitElementToBeVisible(element,timeOutInSeconds);
    }

    /**
     * Method for managing ImplicitWait
     *
     *
     */

    public void setDefaultImplicitTimeout() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Method for closing WebDriver instance
     *
     *
     */

    public void closeBrowser() {
        if(driver != null) {
            driver.quit();
        }
        driver = null;
    }

    /**
     * Method for opening the specified page
     *
     */

//    public void doPageOpen() {
//        String url = System.getProperty("env.url");
//
//        if(url == null || url.isEmpty()){
//            driver.get(EnvironmentProperties.get().url);
//        }else {
//            url = System.getProperty("env.url");
//            driver.get(url);
//        }
//
//    }

    /**
     * Method for managing WebDriver executables and setting System properties for the geckodriver
     *
     */

    protected void setDriverPath() {
        String projectFolder = System.getProperty("user.dir");
        if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.gecko.driver",projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"geckodriver_linux");

            System.setProperty("webdriver.chrome.driver",projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"chromedriver_linux");

        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.gecko.driver", projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"geckodriver.exe");

            System.setProperty("webdriver.chrome.driver", projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"chromedriver.exe");

        } else if (SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.gecko.driver",projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"geckodriver_mac");

            System.setProperty("webdriver.chrome.driver",projectFolder + File.separator + "src"+File.separator+"main"
                    +File.separator+"resources"+File.separator+"chromedriver_mac");
        }
        else {
            throw new RuntimeException("Could not initialize browser due to unknown operating system!");
        }
    }
}
