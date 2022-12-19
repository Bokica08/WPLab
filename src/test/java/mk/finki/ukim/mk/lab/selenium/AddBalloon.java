package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddBalloon extends AbstractPage {
    public AddBalloon(WebDriver driver) {
        super(driver);
    }

    private WebElement ime;
    private WebElement description;
    private WebElement idManu;
    private WebElement submit;

    public static BalloonPage addProduct(WebDriver driver, String ime, String description, String idManu) {
        get(driver, "/balloons/add-form");
        AddBalloon addOrEditProduct = PageFactory.initElements(driver, AddBalloon.class);
        addOrEditProduct.ime.sendKeys(ime);
        addOrEditProduct.description.sendKeys(description);
        addOrEditProduct.idManu.click();
        addOrEditProduct.idManu.findElement(By.xpath("//option[. = '" + idManu + "']")).click();
        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }

}
