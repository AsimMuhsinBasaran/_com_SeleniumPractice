package tests.hotelMyCamp;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelPage;
import utilities.ConfigReader;
import utilities.Driver;

public class NegativeTest {
    //3 Farkli test Methodunda 3 senaryoyu test et
    //- yanlisSifre
    //- yanlisKulllanici
    //- yanlisSifreKullanici
    //test data yanlis username: manager1 , yanlis password : manager 1
    //  2) https://www.hotelmycamp.com adresine git
    //  3) Login butonuna bas
    //  4) Verilen senaryolar ile giris yapilamadigini test et


    @Test
    public void negativePasswordTest(){
        Driver.getDriver().get(ConfigReader.getProperty("hotelUrl"));
        HotelPage hp = new HotelPage();

        hp.ilkLoginButonu.click();

        hp.usernameBox.sendKeys(ConfigReader.getProperty("hotelValidUsername"));
        hp.passwordBox.sendKeys(ConfigReader.getProperty("hotelInvalidPassword"));

        hp.ikinciLoginButonu.click();

        Assert.assertTrue(hp.ikinciLoginButonu.isDisplayed());



    }
    @Test(priority = 1)
    public void negativeUsernameTest(){
        Driver.getDriver().get(ConfigReader.getProperty("hotelUrl"));

        HotelPage hp = new HotelPage();
        hp.ilkLoginButonu.click();

        hp.usernameBox.sendKeys(ConfigReader.getProperty("hotelInvalidUsername"));
        hp.passwordBox.sendKeys(ConfigReader.getProperty("hotelValidPassword"));

        hp.ikinciLoginButonu.click();

        Assert.assertTrue(hp.ikinciLoginButonu.isDisplayed());


    }
    @Test (priority = 2)
    public void negativeUsernamePasswordTest(){

        Driver.getDriver().get(ConfigReader.getProperty("hotelUrl"));
        HotelPage hp = new HotelPage();
        hp.ilkLoginButonu.click();

        hp.usernameBox.sendKeys(ConfigReader.getProperty("hotelInvalidUsername"));
        hp.passwordBox.sendKeys(ConfigReader.getProperty("hotelInvalidPassword"));

        hp.ikinciLoginButonu.click();

        Assert.assertTrue(hp.ikinciLoginButonu.isDisplayed());

        Driver.closeDriver();
    }
}
