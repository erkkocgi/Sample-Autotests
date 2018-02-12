package ee.cgi.sample.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    private static String targetFilePath = "target" + File.separator + EnvironmentProperties.get().logsFolderName + File.separator + UtilTest.getDate() + "/extentreport.html";
    private static String confFilePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.xml";


    public static ExtentReports GetExtent(){
        if (extent != null)
            return extent; //avoid creating new instance of html file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {

        htmlReporter = new ExtentHtmlReporter(targetFilePath);

        htmlReporter.loadXMLConfig(confFilePath);
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }
}