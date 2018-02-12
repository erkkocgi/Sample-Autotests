package ee.cgi.sample.helpers;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

    /**
     * Junit Rule/TestWatcher to take Selenium screenshot when required AND also when test fails.
     *
     */

public class ScreenShotRule extends TestWatcher {

    private static final Logger logger = LogManager.getLogger(ScreenShotRule.class);

    private String folderName;

    /**
     * Constructor for initializing ScreenShotRule for taking screenshots after failed test
     *
     */

    public ScreenShotRule(String testClassName) {
        this.folderName = "target"+File.separator+ EnvironmentProperties.get().logsFolderName + File.separator + UtilTest.getDate() +
                File.separator + testClassName + File.separator;
    }

    /**
     * Method overrides TestWatcher failed() method
     *
     * @param e error
     * @param description description
     */

    @Override
    protected void failed(Throwable e, Description description) {
        String filePath = buildFilePath(description.getMethodName() + "_failed.png");
        logger.error("Taking screenshot for failure: " + filePath, e);
        _takeScreenshot(filePath);
    }

    /**
     * Method for taking screenshot for positive case
     *
     * @param fileName for the screenshot file
     */

    public void takeScreenshot(String fileName) {
        String filePath = buildFilePath(fileName);
        logger.info("Taking screenshot: " + filePath);
        _takeScreenshot(filePath);
    }

    /**
     * Helper method for taking screenshots
     *
     * @param filePath for creating screenshot
     */

    private void _takeScreenshot(String filePath) {
        TakesScreenshot takesScreenshot = WebDriver.get().takesScreenshot();
        try {
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (Exception ex) {
            logger.warn("Taking screenshot failed!", ex);
        }
    }

    /**
     * Helper method for creating path for screenshot file
     *
     * @param fileName for building full path
     * @return returns full filepath of the file
     */

    private String buildFilePath(String fileName) {
        String filePath = folderName + fileName;
        if(!filePath.endsWith(".png")) filePath += ".png";
        return filePath;
    }
}
