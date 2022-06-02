package tests.amazon;

import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Test01 {
    @Test
    public void test01() {
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        AmazonPage amazonPage = new AmazonPage();

        amazonPage.aramaKutusu.sendKeys("Nutella");

        Driver.closeDriver();


    }
}
