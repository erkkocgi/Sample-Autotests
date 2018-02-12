package ee.cgi.sample.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WikiPage {

    Logger logger = Logger.getLogger(MainPage.class);

    @FindBy (how = How.ID, using = "firstHeading")
    private WebElement heading;

    @FindBy (how = How.XPATH, using = "//div[@id='mw-content-text']//p[1]")
    private WebElement firstParagraph;

    //Getters

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getFirstParagraph() {
        return firstParagraph;
    }
}
