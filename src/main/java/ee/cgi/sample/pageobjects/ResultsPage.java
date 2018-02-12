package ee.cgi.sample.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResultsPage {

    Logger logger = Logger.getLogger(MainPage.class);

    @FindBy (how = How.XPATH, using = "//div[@id='rso']//h3/a[text()='Test automation - Wikipedia']")
    private WebElement linkWikiPage;

    //Getters

    public WebElement getLinkWikiPage() {
        return linkWikiPage;
    }

}
