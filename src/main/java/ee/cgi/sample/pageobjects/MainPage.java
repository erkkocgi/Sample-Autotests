package ee.cgi.sample.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

    /**
     * Pageobjects which define UI elements on the main page
     */

public class MainPage {

    Logger logger = Logger.getLogger(MainPage.class);

    //Web elements

    @FindBy (how = How.XPATH, using = "//li[@class='navigationUser']/a/span[@class='navigation-title']")
    private WebElement txtName;

    @FindBy (how = How.XPATH, using = "//section[@id='pm_sidebar']/button")
    private WebElement btnCompose;

    //Getters
    public WebElement getTxtName() { return txtName; }

    public WebElement getBtnCompose() { return btnCompose; }

    //Functions
    public void logIn(){

    }
}
