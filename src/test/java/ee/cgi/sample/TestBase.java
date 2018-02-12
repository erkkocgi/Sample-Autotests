package ee.cgi.sample;

import com.aventstack.extentreports.ExtentReports;
import ee.cgi.sample.helpers.ExtentManager;
import ee.cgi.sample.helpers.PropertiesLoader;
import ee.cgi.sample.helpers.ScreenShotRule;
import ee.cgi.sample.helpers.WebDriver;
import ee.cgi.sample.pageobjects.*;
import org.apache.logging.log4j.ThreadContext;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;


public class TestBase {

    /**
     * Helper class TestBase which all the other web tests should extend
     * It defines method for getting Properties values, method for
     * initializing logger, method for defining WebDriver executable location
     * and method for initializing Webdriver itself.
     *
     * Also some helper functions are defined for getting WebDriver instance and
     * function for taking screenshots.
     */

	protected static MainPage mainPage;
    protected static SearchPage searchPage;
    protected static ResultsPage resultsPage;
    protected static WikiPage wikiPage;
    protected static LoginPage loginPage;
    protected static ExtentReports extent;
    protected static String url = "";
    protected static String logFolder = "";
    protected static String searchPhrase = "";
    protected static String user = "";
    protected static String pass = "";
    protected static String searchurl = "";

    /**
     * JUNIT RULE to get test name from the testmethod
     */
    @Rule
    public TestName testName = new TestName();

    /**
     * JUNIT RULE to initialize ScreenShotRule class
     */
    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule(getClass().getSimpleName());

    /**
     * Method which runs only once, before the entire test fixture
     */

    @BeforeClass
    public static void setupOnlyOnce() {
        // initialization for all tests
        extent = ExtentManager.GetExtent();
        openBrowser();
        initPageObject();
    }

    private static void openBrowser() {
        WebDriver.get().openBrowser();}

    public static void openPage(String url){
        WebDriver.get().getWebDriver().get(url);
    }
    /**
     * Method which runs only once, after the entire test fixture
     */

    @AfterClass
    public static void tearDownOnlyOnce() {
        extent.flush();
        WebDriver.get().closeBrowser();
    }

    /**
     * Method which runs before each test
     */

    @Before
    public void setUp() {
        ThreadContext.put("className",this.getClass().getSimpleName());
        ThreadContext.put("testName",testName.getMethodName());

        getProperties();
        WebDriver.get().doPageOpen();
    }


    /**
     * Method leverages WebDriver class method for waiting after checking alert
     */

    public void checkWait() {
        WebDriver.get().checkAlert(2);
    }

    /**
     * Method leverages WebDriver class method for waiting for page to load
     */

    public void pageLoadWait() {
        WebDriver.get().pageLoadTimeout();
    }

    /**
     * Method for getting the current URL
     */

    public static String getCurrentUrl() {
        return WebDriver.get().getWebDriver().getCurrentUrl();
    }


    /**
     * Method loads defined settings in readSettingsProperties method
     */

    private static void getProperties() {
        readSettingsProperties();
    }

    /**
     * Method loads settings using PropertiesLoader class and defines each property
     */

    private static void readSettingsProperties() {
        Properties settings = PropertiesLoader.loadGeneralSettings();
        Properties environment = PropertiesLoader.loadEnvironmentSettings();
        url = System.getProperty("env.url");
        if(url == null || url.isEmpty())
            url = environment.getProperty("mail.url");
        searchurl = environment.getProperty("search.url");
        logFolder = environment.getProperty("logs.folder.name");
        searchPhrase = settings.getProperty("searchPhrase");
        user = settings.getProperty("user");
        pass = settings.getProperty("pass");
    }

    /**
     * Method initalizes PageObject
     */

    private static void initPageObject() {
        mainPage = PageFactory.initElements(WebDriver.get().getWebDriver(), MainPage.class);
        searchPage = PageFactory.initElements(WebDriver.get().getWebDriver(), SearchPage.class);
        resultsPage = PageFactory.initElements(WebDriver.get().getWebDriver(), ResultsPage.class);
        wikiPage = PageFactory.initElements(WebDriver.get().getWebDriver(), WikiPage.class);
        loginPage = PageFactory.initElements(WebDriver.get().getWebDriver(), LoginPage.class);
    }

    /**
     * Method for taking screenshot and writing the file to a specified path
     *
     */

    @After
    public void takeScreenshot() {
        screenShotRule.takeScreenshot(testName.getMethodName());
    }

}