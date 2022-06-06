package tests.rentalCar;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrcPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static utilities.ReusableMethods.getScreenshot;
import static utilities.ReusableMethods.waitFor;

public class BrcTasks {
    @Test
    public void test01() {
        //-> https://www.bluerentalcars.com/ adresıne gıdıp asagıdakı bılgılerle  login olalim
        //brcValidEmail=customer@bluerentalcars.com
        //brcValidPassword=12345
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));
        BrcPage brcPage = new BrcPage();
        brcPage.ilkLoginButonu.click();
        brcPage.emailKutusu.sendKeys(ConfigReader.getProperty("brcValidEmail"));
        brcPage.passwordKutusu.sendKeys(ConfigReader.getProperty("brcValidPassword"));
        brcPage.ikinciLoginButonu.click();

        //-> Audi Q8 i secip reservasyon yapalim
        brcPage.getPageSelectElement().selectByVisibleText("Kia Rio");
        Actions actions = new Actions(Driver.getDriver());
        actions.click(brcPage.selectPlace1)
                .sendKeys("Bursa" + Keys.TAB)
                .sendKeys("Ankara" + Keys.TAB)
                .sendKeys("04082024" + Keys.TAB)
                .sendKeys("1223" + Keys.TAB)
                .sendKeys("24092024" + Keys.TAB)
                .sendKeys("1223" + Keys.TAB)
                .sendKeys(Keys.SPACE).perform();

                waitFor(1);

        actions.click(brcPage.kartNoKutusu)
                .sendKeys(ConfigReader.getProperty("brcKartNo") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("brcKartName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("brcKartTarih") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("brcCVCNo") + Keys.TAB)
                .sendKeys(Keys.SPACE)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.SPACE)
                .perform();

        //-> reservasyon yaptigimizi dogrulayalim

        Assert.assertTrue(brcPage.successfullyYazisi.isDisplayed());
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
    public void test05() throws IOException {
        //-> Servis e gidelim
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));
        BrcPage brcp = new BrcPage();
        brcp.servicesButonu.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        //-> En ucuz arac fiyatinin saatlik 12 dolar oldugunu test edelim
        List<String>saatlikFiyatList = new ArrayList<String>();
        List<WebElement> tumAraclar = new ArrayList<WebElement>();

        for (WebElement each:brcp.gorunen6AracList) {
            each.click();
            tumAraclar.add(each);
            saatlikFiyatList.add(brcp.saatlikFiyat.getText());
            if (brcp.saatlikFiyat.getText().contains("12")){
                getScreenshot("en ucuz arac");
            }
            if (brcp.saatlikFiyat.getText().contains("9999")){
                getScreenshot("en pahali arac");
            }
        }
        for (int i = 0; i < 6; i++) {
            brcp.asagiButonu.click();
        }
        for (WebElement each:brcp.gorunen6AracList) {
            each.click();
            tumAraclar.add(each);
            saatlikFiyatList.add(brcp.saatlikFiyat.getText());
            if (brcp.saatlikFiyat.getText().contains("12")){
                getScreenshot("en ucuz arac");
            }
            if (brcp.saatlikFiyat.getText().contains("9999")){
                getScreenshot("en pahali arac");
            }
        }
        for (int i = 4; i <=5; i++) {
            brcp.asagiButonu.click();
            brcp.gorunen6AracList.get(i).click();
            tumAraclar.add(brcp.gorunen6AracList.get(i));
            saatlikFiyatList.add(brcp.saatlikFiyat.getText());
            if (brcp.saatlikFiyat.getText().contains("12")){
                getScreenshot("en ucuz arac 1");
            }
            if (brcp.saatlikFiyat.getText().contains("9999")){
                getScreenshot("en pahali arac 2" );
            }
        }
        // saatlikFiyatList.stream().map(t->t.replaceAll("\\D",""))

        List<Integer> fiyatList = new ArrayList<Integer>();
        waitFor(1);
        for (int i = 0; i <saatlikFiyatList.size() ; i++) {
            fiyatList.add(Integer.valueOf(saatlikFiyatList.get(i).replaceAll("\\D","")));
        }
       // for (int i = 0; i < fiyatList.size(); i++) {
       //     if(fiyatList.get(i+1) > fiyatList.get(i)){
       //         System.out.println("En Buyuk sayi : "+ fiyatList.get(i+1));
       //     }
       // }

        Optional<Integer> max= fiyatList.stream().reduce(Math::max);
        System.out.println("max = $"+max);
        Optional<Integer> min= fiyatList.stream().reduce(Math::min);
        System.out.println("min = $" + min);

        //-> En pahali aracin fiyatinin 9999 $ oldugunu test edelim
        Optional<Integer> expected= Optional.of(9999);
        Assert.assertEquals(max,expected);

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
