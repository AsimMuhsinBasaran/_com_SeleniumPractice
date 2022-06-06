package tests.genelTekrarSorulariTestNG;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BrcPage;
import pages.HotelPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Q1 {
    //1. /https://www.hotelmycamp.com sayfasina gidelim
    // @Dataprovider kullanarak asagidaki gorevi tamamlayin
    //- /Login tusuna basin
    //- Asagidaki 5 kullanici adi ve sifreyi deneyin ve login olmadigini test edin
    //- Manager Manager
    //- Manager1 Manager1
    //- Manager2 Manager2
    //- Manager3 Manager3
    //- Manager4 Manager4
    BrcPage bp = new BrcPage();
    @DataProvider
    public static Object [][] loginDatas(){
        Object [][] loginDatas = {{"Manager","Manager"},{"Manager1","Manager1"},{"Manager2","Manager2"},
                {"Manager3","Manager3"},{"Manager4","Manager4"}};

        return loginDatas;
    }

    @Test(dataProvider = "loginDatas")
    public void dataProviderQ1(String username, String password) {
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));

        bp.ilkLoginButonu.click();
        bp.emailKutusu.sendKeys(username);
        bp.passwordKutusu.sendKeys(password);
        bp.ikinciLoginButonu.click();
        Assert.assertTrue(bp.ikinciLoginButonu.isDisplayed());

    }

}
