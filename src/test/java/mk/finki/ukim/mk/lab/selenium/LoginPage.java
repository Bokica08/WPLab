package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
    private WebElement username;
    private WebElement password;
    private WebElement submit;
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static LoginPage openLogin(WebDriver driver)
    {
        get(driver,"/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);


    }
    public static BalloonPage doLogin(WebDriver driver,String username,String password,LoginPage loginPage)
    {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, BalloonPage.class);
    }
}
