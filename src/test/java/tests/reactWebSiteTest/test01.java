package tests.reactWebSiteTest;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ReactPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class test01 {
    @Test
    public void testName() throws InterruptedException {
        // 1."https://react-shopping-cart-67954.firebaseapp.com/" adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("reactUrl"));

        // 2.Web sitesindeki tüm öğeleri listeleyin ve yazdirin
        // (sitede 16 urun var, 1.urun : 2.urun :.....seklinde yazdirin )
        ReactPage rp = new ReactPage();
        for (WebElement each : rp.tumOgelerList) {
            System.out.println(each.getText());
        }


        // 3.Stringlerden olusan bir ArrayList oluşturun ve Ürün Adlarını ArrayList'e yerleştirin
        List<String> urunIsimleri = new ArrayList<String>();
        for (WebElement each : rp.tumOgelerList) {
            urunIsimleri.add(each.getText());
        }

        // 4.Siteden Rastgele 5 öğe seçin, sepete ekleyin ve sectiginiz öğelerin adlarını yazdırın
        // (Her ürün 1 defadan fazla eklenemez!)

        List<Integer> randomSecilenIndexler = new ArrayList<Integer>();
        double expectedToplamFiyat = 0;

        for (int i = 0; i < 5; i++) {
            Faker faker = new Faker();
            int randomIndex = faker.random().nextInt(urunIsimleri.size());
            if (!(randomSecilenIndexler.contains(randomIndex))) {
                randomSecilenIndexler.add(randomIndex);
                rp.addToCartButonlari.get(randomIndex).click();
                String secilenUrunFiyatiString = rp.urunFiyatlariList.get(randomIndex).getText();
                double secilenUrunFiyatiDouble = Double.parseDouble(secilenUrunFiyatiString.substring(1));
                System.out.println("urun adi = "+urunIsimleri.get(randomIndex));
                System.out.println("fiyat = " + secilenUrunFiyatiDouble);
                expectedToplamFiyat +=secilenUrunFiyatiDouble;
                Thread.sleep(2000);
                rp.xButton.click();

            } else {
                i--;
            }

        }

        // 5.Her bir öğenin fiyatını toplayın ve sonucunuzu web sitesiyle karşılaştırın

        Thread.sleep(1000);
        rp.sepetButonu.click();
        System.out.println("toplam fiyat :" + rp.toplamFiyatWebElement.getText());
        Double actualToplam = Double.valueOf(rp.toplamFiyatWebElement.getText().substring(1));
        actualToplam = Double.parseDouble(new DecimalFormat("##.##").format(actualToplam));
        expectedToplamFiyat = Double.parseDouble(new DecimalFormat("##.##").format(expectedToplamFiyat));
        Assert.assertEquals(actualToplam, expectedToplamFiyat,"toplamlar eşit degil");

        rp.checkoutButonu.click();
        Driver.getDriver().switchTo().alert().accept();
        Driver.closeDriver();


    }
}
