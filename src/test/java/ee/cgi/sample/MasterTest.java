package ee.cgi.sample;

import com.aventstack.extentreports.ExtentTest;
import ee.cgi.sample.helpers.EnvironmentProperties;
import ee.cgi.sample.helpers.UtilTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.IOException;

import static ee.cgi.sample.helpers.WebDriver.get;
import static org.jsoup.helper.Validate.fail;
import static org.junit.Assert.assertTrue;
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MasterTest extends TestBase {

    /**
     * Test: User logs in from the main page with correct user name and password. Logs out in the end.
     */
    @Test
    public void searchGoogleForAWikiPage() throws IOException {
        ExtentTest test = extent.createTest(testName.getMethodName());
        final Logger logger = LogManager.getLogger(testName.getMethodName());

        logger.info("Starting test " + testName.getMethodName());
        logger.info("Opening page: " + searchurl);
        openPage(searchurl);

        try {
            // Searching for text
            logger.info("Searching for keyword: " + searchPhrase);
            searchPage.getTxtSearch().sendKeys(searchPhrase);
//            mainPage.getBtnGoogleSearch().click();
            searchPage.getTxtSearch().sendKeys(Keys.ENTER);

            // Asserting search result
            if(!resultsPage.getLinkWikiPage().isDisplayed()){
                test.fail("Error: The Wiki page result is not displayed");
            }
            assertTrue("The user name is not displayed", resultsPage.getLinkWikiPage().isDisplayed());

            // Opening Wiki page
            logger.info("Opening Wiki page");
            resultsPage.getLinkWikiPage().click();

            // Asserting wiki page header
            if(wikiPage.getHeading().getText().equals("Test automation")) {
                logger.info("The correct heading is shown. The first paragraph: " + wikiPage.getFirstParagraph().getText());
                test.pass("The correct heading is shown. The first paragraph: " + wikiPage.getFirstParagraph().getText());
            } else {
                logger.info("Error: The heading is wrong. Was: " + wikiPage.getHeading().getText());
                test.fail("Error: The heading is wrong. Was: " + wikiPage.getHeading().getText());
            }
            assertTrue("The heading is wrong", wikiPage.getHeading().getText().equals("Test automation"));

            logger.info("Ending test " + testName.getMethodName());
            test.pass("Test " + testName.getMethodName() + " passed");

        } catch (Exception e) {
            catchTestFail(test, logger, e, testName.getMethodName());
        }
    }

    @Test
    public void logInToProtonmail() throws IOException {
        ExtentTest test = extent.createTest(testName.getMethodName());
        final Logger logger = LogManager.getLogger(testName.getMethodName());

        logger.info("Starting test " + testName.getMethodName());
        logger.info("Opening page: " + url);

        try {
            // Logging in
            logger.info("Logging in with user: " + user);
            loginPage.logIn(user,pass);

            if(mainPage.getTxtName().getText().equals("CGI TEST")){
                test.pass("Correct username is displayed");
            } else {
                test.fail("Incorrect username is displayed: "  + mainPage.getTxtName().getText());
            }
            assertTrue("Incorrect username is displayed: " + mainPage.getTxtName().getText(), mainPage.getTxtName().getText().equals("CGI TEST"));

            if(mainPage.getBtnCompose().isDisplayed()){
                test.pass("The Compose button is shown");
            } else {
                test.fail("The Compose button is now shown!");
            }
            assertTrue("The Compose button is now shown!", mainPage.getBtnCompose().isDisplayed());

            logger.info("Ending test " + testName.getMethodName());
            test.pass("Test " + testName.getMethodName() + " passed");

        } catch (Exception e) {
            catchTestFail(test, logger, e, testName.getMethodName());
        }
    }

        public void goToMainPage(){
        get().getWebDriver().get(url);
    }

    public String getScreenshotName(String testClassName){
        String projectFolder = System.getProperty("user.dir");
        return projectFolder + File.separator + "target" + File.separator + EnvironmentProperties.get().logsFolderName + File.separator + UtilTest.getDate() + File.separator + testClassName + File.separator + testName.getMethodName() + "_failed.png";
    }

    public void catchTestFail(ExtentTest test, Logger logger, Exception e, String testName) {
        try {
            test.fail("Test " + testName + " failed with message: " + e.getMessage()).addScreenCaptureFromPath(getScreenshotName(this.getClass().getSimpleName()));
        } catch (Exception ex){
            logger.error("Unable to take screenshot. Test " + testName + " failed with message: " + ex.getMessage());
        }
        logger.error("ERROR TRACE: " + e.getMessage());
        logger.error("TEST FAILED!");
        fail("TEST FAILED!");
    }
}
