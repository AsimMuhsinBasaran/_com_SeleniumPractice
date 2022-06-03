package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ReactPage {

    public ReactPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath="//p[@class='sc-124al1g-4 eeXMBo']")
    public List<WebElement> tumOgelerList;

    @FindBy(xpath="//button[@class='sc-124al1g-0 jCsgpZ']")
    public List<WebElement> addToCartButonlari;

    @FindBy(xpath="//button[@class=\"sc-1h98xa9-0 gFkyvN\"]")
    public WebElement xButton;

    @FindBy(xpath="//p[@class='sc-124al1g-6 ljgnQL']")
    public List<WebElement> urunFiyatlariList;

    @FindBy(xpath="//div[@class='sc-1h98xa9-2 fGgnoG']")
    public WebElement sepetButonu;


    @FindBy (xpath = "//*[text()='Add to cart']")
    public List<WebElement> addtoCartButtonList;

    @FindBy (xpath = "//p[@class='sc-124al1g-6 ljgnQL']")
    public List<WebElement> pricesList;

    @FindBy (xpath = "//p[@class='sc-1h98xa9-9 jzywDV']")
    public WebElement toplamFiyatWebElement;

    @FindBy (xpath = "//*[text()='Checkout']")
    public WebElement checkout;

    @FindBy (xpath = "//button[@class='sc-1h98xa9-0 gFkyvN']")
    public WebElement Xbutton;

    @FindBy (xpath = "//button[@class='sc-1h98xa9-0 gFkyvN']")
    public WebElement sepetLink;

    @FindBy(xpath="//p[@class='sc-1h98xa9-9 jzywDV']")
    public WebElement actualFiyat;

    @FindBy(xpath="//*[text()='Checkout']")
    public WebElement checkoutButonu;

}
