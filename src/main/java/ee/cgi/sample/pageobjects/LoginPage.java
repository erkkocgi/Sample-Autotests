package ee.cgi.sample.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Pageobjects which define UI elements on the main page
 */

public class LoginPage {

    Logger logger = Logger.getLogger(MainPage.class);

    //Web elements

    @FindBy (how = How.ID, using = "username")
    private WebElement txtUsername;

    @FindBy (how = How.ID, using = "password")
    private WebElement txtPassword;

    @FindBy (how = How.ID, using = "login_btn")
    private WebElement btnLogin;

    //Getters
    public WebElement getTxtUsername() { return txtUsername; }

    public WebElement getTxtPassword() { return txtPassword; }

    public WebElement getBtnLogin() { return btnLogin; }

    //Functions
    public void logIn(String user, String pass){
        this.txtUsername.sendKeys(user);
        this.txtPassword.sendKeys(pass);
        this.btnLogin.click();
    }
}
