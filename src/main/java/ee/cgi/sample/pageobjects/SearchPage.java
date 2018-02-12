package ee.cgi.sample.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Pageobjects which define UI elements on the main page
 */

public class SearchPage {

Logger logger = Logger.getLogger(SearchPage.class);

//Web elements

@FindBy (how = How.ID, using = "lst-ib")
private WebElement txtSearch;

//    @FindBy (how = How.CSS, using = "input[aria-label='Google Search']")
//    private WebElement btnGoogleSearch;

//Getters
public WebElement getTxtSearch() { return txtSearch; }

//    public WebElement getBtnGoogleSearch() { return btnGoogleSearch; }


//Functions
}
