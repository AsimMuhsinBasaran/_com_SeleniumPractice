package tests.hotelMyCamp;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelPage;
import utilities.ConfigReader;
import utilities.Driver;


public class PositiveTest {
    //3) Bir test method olustur positiveLoginTest()
    //https://www.hotelmycamp.com adresine git
    //login butonuna bas
    //test data username : manager
    //test data password : Manager 1
    //Degerleri girildiginde sayfaya basarili sekilde girilebildigini test et



    @Test
    public void positiveLoginTest() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("hotelUrl"));
        HotelPage hp = new HotelPage();

        hp.ilkLoginButonu.click();
        hp.usernameBox.sendKeys(ConfigReader.getProperty("hotelValidUsername"));
        hp.passwordBox.sendKeys(ConfigReader.getProperty("hotelValidPassword"));
        hp.ikinciLoginButonu.click();

        Thread.sleep(3000);
        Assert.assertTrue(hp.managerYazisi.isDisplayed());
        Driver.closeDriver();
    }
}
