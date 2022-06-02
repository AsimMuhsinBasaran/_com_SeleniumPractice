package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

public class HotelPage {
    public HotelPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//li[@id='navLogon']")
    public WebElement ilkLoginButonu;

    @FindBy(xpath = "//input[@id='UserName']")
    public WebElement usernameBox;

    @FindBy(xpath = "//input[@id='Password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//input[@id='btnSubmit']")
    public WebElement ikinciLoginButonu;

    @FindBy(xpath = "//span[@class='username username-hide-on-mobile']")
    public WebElement managerYazisi;

    @FindBy(xpath = "//*[text()='Hotel Management']")
    public WebElement hotelManagementElementi;

    @FindBy(xpath = "(//a[@href=\"/admin/HotelAdmin\"])[1]")
    public WebElement hotelListElement;

    @FindBy(xpath = "//*[text()='Add Hotel ']")
    public WebElement addHotelButonu;

    @FindBy(xpath = "//input[@id='Code']")
    public WebElement codeBox;


    @FindBy(xpath ="//select[@id='IDGroup']")
    private WebElement selectElement;

    public Select getPageSelectElement(){
        return new Select(selectElement);
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButonu;

    @FindBy(xpath= "//div[@class='bootbox-body']")
    public WebElement successfullyYazisi;

    @FindBy(xpath ="//*[text()='OK']")
    public WebElement okButonu;

    @FindBy(xpath = "//a[@href='/admin/HotelRoomAdmin']")
    public WebElement hotelRoomElementi;

    @FindBy(xpath = "(//*[text()='List Of Hotelrooms'])[2]")
    public WebElement listOfHotelRooms;

}
