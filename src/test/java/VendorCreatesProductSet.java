import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import taras.yanishevskyi.adminPanel.AdmProductPage;
import taras.yanishevskyi.constants.DriverProvider;

/*
- Устанавливаем модуль "Общие товары для продавцов"
- Работаем с категорией "Игровые приставки"
- Под продавцом создаём комплект товаров
 */

public class VendorCreatesProductSet extends TestRunner{
    @Test
    public void checkVendorCanCreateProductSet(){
        //Устанавливаем модуль "Общие товары для продавцов"
        AdmHomePage admHomePage = new AdmHomePage();
        admHomePage.navigateToAddonsManagementPage();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".alert")).size()>0){
            DriverProvider.getDriver().findElement(By.cssSelector(".close.cm-notification-close")).click();
        }   //Выключаем сообщение о предупредлении, если оно появилось
        admHomePage.clickAndTypeSearchFieldAtManagementPage("Общие товары для продавцов");
        if (admHomePage.buttonInstallAddon.isEnabled()) {
        admHomePage.clickButtonInstallAddon();
        makePause();
        }
        AdmProductPage admProductPage = admHomePage.navigateToProductPage();
        admProductPage.clickAndTypeSearchFieldAtProductPage("PlayStation 4");
        admProductPage.clickProductInSearchList();
        admProductPage.clickProductVendor();
        admProductPage.selectProductBelongsToAllVendors();
        admProductPage.clickButtonSaveOnEditProductPage();
    }
}