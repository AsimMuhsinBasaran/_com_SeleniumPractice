package tests.rentalCar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrcPage;
import utilities.ConfigReader;
import utilities.Driver;

public class RentalCarTasks {
    @Test
    public void test01() {
        //-> https://www.bluerentalcars.com/ adresıne gıdıp asagıdakı bılgılerle  login olalim
        //brcValidEmail=customer@bluerentalcars.com
        //brcValidPassword=12345
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));

        //-> Audi Q8 i secip reservasyon yapalim



        //-> reservasyon yaptigimizi dogrulayalim

    }

    @Test
    public void test02() {
        //->Servis Butonunu tiklayip
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));
        BrcPage brcPage = new BrcPage();
        brcPage.ilkLoginButonu.click();
        brcPage.emailKutusu.sendKeys(ConfigReader.getProperty("brcValidEmail"));
        brcPage.passwordKutusu.sendKeys(ConfigReader.getProperty("brcValidPassword"));
        brcPage.ikinciLoginButonu.click();

        brcPage.servicesButonu.click();
        //->Kiraladigimiz araclarin otomatik vitesli olup olmadigini kontrol edelim
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).
                perform();

        brcPage.q8.click();

        String expectedVites = "Automatic";
        String actualVites = brcPage.vitesKutusu.getText();
        Assert.assertEquals(actualVites,expectedVites);
        Driver.closeDriver();
    }

    @Test
    public void test03() {
        //->Login olmadan arac kiralamayacagimizi test edelim.
    }

    @Test
    public void test04() {
        //->rezervasyon için verileri girelim

        //-> continue reservation a tiklayalim

        //-> 2 saniye beleyelim ve

        //-> continue reservation hala görünür oldugunu test edelim

    }

    @Test
    public void test05() {
        //-> Servis e gidelim

        //-> En ucuz arac fiyatinin 12 saatlik dolar oldugunu test edelim

        //-> En paha li aracin fiyatinin 3500 $ oldugunu test edelim
        //-> En ucuz ve en pahali aracin ekran resmini alalim
    }
   @Test
    public void test06() {
        //-> login olalim

        //-> login olduktan sonra profile gideloim ve
        //-> bilgilerin dogru oldugunu kontrol edelim

        //-> logout u tiklayip sistemi terk edelim
    }
}
