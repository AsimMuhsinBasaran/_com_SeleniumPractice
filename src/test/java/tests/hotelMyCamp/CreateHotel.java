package tests.hotelMyCamp;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HotelPage;
import utilities.ConfigReader;
import utilities.Driver;

public class CreateHotel {
    //https://www.hotelmycamp.com adresine git
    //1. Username textbox ve password metin kutularını locate edin ve asagidaki verileri girin.
    //a. Username : manager
    //b. Password : Manager1
    //2. Login butonuna tıklayın.
    //3 . Hotel Management/Hotel List menusunden ADD HOTEL butonuna tiklayin
    //4 . Açılan sayfadaki tüm metin kutularına istediğiniz verileri girin.
    //5 . Save butonuna tıklayın.
    //6. “Hotel was inserted successfully” textinin göründüğünü test edin.
    //7 . OK butonuna tıklayın.
    //11. Hotel rooms linkine tıklayın.
    //12. "LIST OF HOTELROOMS" textinin göründüğünü doğrulayın..


    @Test
    public void createHotelTest() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("hotelUrl"));
        HotelPage hp = new HotelPage();
        hp.ilkLoginButonu.click();
        hp.usernameBox.sendKeys(ConfigReader.getProperty("hotelValidUsername"));
        hp.passwordBox.sendKeys(ConfigReader.getProperty("hotelValidPassword"));
        hp.ikinciLoginButonu.click();
        Thread.sleep(5000);
        hp.hotelManagementElementi.click();
        hp.hotelListElement.click();
        hp.addHotelButonu.click();
        Faker faker = new Faker();
        Actions actions = new Actions(Driver.getDriver());
        actions.click(hp.codeBox)
                .sendKeys(faker.number().digit() + Keys.TAB)
                .sendKeys(faker.rickAndMorty().character() + Keys.TAB)
                .sendKeys(faker.address().streetAddress() + Keys.TAB)
                .sendKeys(faker.phoneNumber().phoneNumber()+ Keys.TAB)
                .sendKeys(faker.internet().emailAddress()+ Keys.TAB)
                .perform();
        hp.getPageSelectElement().selectByIndex(1);
        hp.saveButonu.click();

        Thread.sleep(3000);

        SoftAssert softAssert = new SoftAssert();

        String expectedSonucYazisi = "Hotel was inserted successfully";
        String actualSonucYazisi = hp.successfullyYazisi.getText();

         softAssert.assertEquals(actualSonucYazisi, expectedSonucYazisi,"Assert Calismadi");

         softAssert.assertTrue(hp.successfullyYazisi.isDisplayed());

         hp.okButonu.click();

        actions.sendKeys(Keys.HOME).perform();

        hp.hotelRoomElementi.click();

        softAssert.assertTrue(hp.listOfHotelRooms.isDisplayed());

        softAssert.assertAll();

        Driver.closeDriver();

    }
}
