import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.adminPanel.AdmCustomersPage;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import taras.yanishevskyi.adminPanel.AdmProductPage;
import taras.yanishevskyi.constants.DriverProvider;
import java.time.Duration;

import static taras.yanishevskyi.constants.DriverProvider.getDriver;

/*
- Устанавливаем модуль "Общие товары для продавцов"
- Работаем с категорией "Игровые приставки" и товаром "PlayStation 4"
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
        admHomePage.clickButtonInstallAddon();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(5))).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//li[@class='dropdown nav__header-main-menu-item ']")));
        DriverProvider.getDriver().navigate().refresh();
        //Работаем со страницей редактирования товара
        AdmProductPage admProductPage = admHomePage.navigateToProductPage();
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        admProductPage.clickProductInSearchList();
        admProductPage.clickProductVendor();
        admProductPage.selectProductBelongsToAllVendors();
        admProductPage.clickButtonSaveOnEditProductPage();
        AdmCustomersPage admCustomersPage = admHomePage.navigateToCustomersPage();
        admCustomersPage.navigateToGearwheelOfSimtechVendor();
        admCustomersPage.clickActAsUser();
        focusBrowserTab(1);

        //Работаем как продавец
        admCustomersPage.clickVendorProductMenu();
        admCustomersPage.clickVendorProductPage();
        admCustomersPage.clickSidebar_ProductsThatCanBeSold();
        admCustomersPage.clickProductButtonSell();
        admCustomersPage.clickAndTypeInStockField("19");
        //Проверяем, что вкладка "АВ: Комплекты для товаров" присутствует
        Assert.assertTrue(admCustomersPage.tabProductSets.isDisplayed(),
                "There is no the tab of our add-on on the editing product page!");
        admCustomersPage.clickTabProductSets();
        admProductPage.clickAddNewSet();
        makePause();
        admProductPage.clickAddProductsToSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        admCustomersPage.selectAProductForSet(By.cssSelector("input[value='255']"));
        admCustomersPage.selectAProductForSet(By.cssSelector("input[value='257']"));
        admCustomersPage.selectAProductForSet(By.cssSelector("input[value='278']"));
        admProductPage.clickButtonAddProductsAndClose();
        admProductPage.clickButtonSaveOnEditProductPage();
        focusBrowserTab(0);
        admHomePage.navigateToProductPage();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".alert")).size()>0){
            DriverProvider.getDriver().findElement(By.cssSelector(".close.cm-notification-close")).click();
        }   //Выключаем сообщение о предупредлении, если оно появилось
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        admProductPage.clickIconThumbUp();
        admProductPage.clickProductInSearchList();
        admProductPage.clickGearWheelOfProduct();
        admProductPage.clickPreviewButton();
        focusBrowserTab(1);

        //Работаем с витриной
        //Проверяем, что мы на странице продавца Simtech
        String actualPage = DriverProvider.getDriver().findElement(By.cssSelector("div.ut2-vendor-block__name a")).getText();
        String expectedPage = "Simtech";
        Assert.assertEquals(actualPage.toLowerCase(), expectedPage.toLowerCase(), "It is not a vendor page!");
        //На странице продавца проверяем, что комплект присутствует

    }
}