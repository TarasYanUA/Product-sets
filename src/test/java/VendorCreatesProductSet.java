import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.adminPanel.AdmCustomersPage;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import taras.yanishevskyi.adminPanel.AdmProductPage;
import taras.yanishevskyi.constants.DriverProvider;
import taras.yanishevskyi.storefront.CheckoutPage;
import taras.yanishevskyi.storefront.StProductPage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static taras.yanishevskyi.constants.DriverProvider.getDriver;

/*
- Устанавливаем модуль "Общие товары для продавцов"
- Работаем с категорией "Игровые приставки" и товаром "X-Box 360"
- Под продавцом создаём комплект товаров
- Переходим на страницу продавца и покупаем комплект товаров
- проверяем заказ на количество товаров
 */

public class VendorCreatesProductSet extends TestRunner{
    @Test
    public void checkVendorCanCreateProductSet() throws IOException {
        //Устанавливаем модуль "Общие товары для продавцов"
        AdmHomePage admHomePage = new AdmHomePage();
        admHomePage.navigateToAddonsManagementPage();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".alert")).size()>0){
            DriverProvider.getDriver().findElement(By.cssSelector(".close.cm-notification-close")).click();
        }   //Выключаем сообщение о предупредлении, если оно появилось
        admHomePage.clickAndTypeSearchFieldAtManagementPage("Общие товары для продавцов");
        if(DriverProvider.getDriver().findElements(By.cssSelector("td.nowrap.right a[href*='addon=master_products']")).size()==1){
        admHomePage.clickButtonInstallAddon();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(5))).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//li[@class='dropdown nav__header-main-menu-item ']")));
        DriverProvider.getDriver().navigate().refresh();    }
        //Работаем со страницей редактирования товара
        AdmProductPage admProductPage = admHomePage.navigateToProductPage();
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        admProductPage.clickProductInSearchList();
        if (DriverProvider.getDriver().findElements(By.cssSelector("label[for*='elm_parent_product']")).size() < 1) {
            admProductPage.clickProductVendor();
            admProductPage.selectProductBelongsToAllVendors();
            admProductPage.clickButtonSaveOnEditProductPage();
        }
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
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        admProductPage.clickIconThumbUp();
        admProductPage.clickProductInSearchList();
        admProductPage.clickGearWheelOfProduct();
        admProductPage.clickPreviewButton();
        focusBrowserTab(2);

        //Работаем с витриной
        //Проверяем, что мы на странице продавца Simtech
        String actualPage = DriverProvider.getDriver().findElement(By.cssSelector("div.ut2-vendor-block__name a")).getText();
        String expectedPage = "Simtech";
        Assert.assertEquals(actualPage, expectedPage, "It is not a vendor page!");
        //На странице продавца проверяем, что комплект присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElement(By.cssSelector(".sol-inner-container")).isEnabled(),
                "Product set is absent on vendor's product page!");
        takeScreenShot("210 Product set on Simtech product page");
        //Покупаем комплект товаров
        StProductPage stProductPage = new StProductPage();
        stProductPage.clickFieldSelectProducts();
        stProductPage.clickButtonSelectAllProductsForSet();
        makePause();
        stProductPage.clickButtonCloseForSet();
        stProductPage.clickButtonAddToCart();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close.close")));
        CheckoutPage checkoutPage = stProductPage.navigateToCheckoutPage();   //Находимся на странице чекаута
        checkoutPage.clickCountryField();
        checkoutPage.selectCountryField("UA");
        checkoutPage.clickAndTypeCityField("Киев");
        makePause();
        checkoutPage.clickPaymentMethod();
        checkoutPage.choosePaymentMethod_PhoneOrdering();
        makePause();
        checkoutPage.checkAgreementTermsAndConditions();
        if(getDriver().findElements(By.xpath("//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")).size()>0){
            checkoutPage.checkAgreementPersonalData();
        }
        checkoutPage.checkAgreementSimtech();
        checkoutPage.clickPaymentMethod();
        checkoutPage.clickButtonPlaceOrder();   //Заказ оформлен!
        //Проверяем, что мы на странице завершения заказа
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ty-checkout-complete__buttons")));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout.complete&order_id="),
                "Process of placing the order has failed!");
        //Проверяем наличие всех товаров в заказе
        checkoutPage.clickButtonOrderDetails();
        checkoutPage.scrollToBlockProductInformation();
        List<WebElement> listOfProductsQuantity = checkoutPage.countQuantityOfProducts();
        int actualQuantity = listOfProductsQuantity.size();
        int expectedQuantity = 4;
        Assert.assertEquals(actualQuantity, expectedQuantity, "There is a wrong number of products at the order!");
        takeScreenShot("220 Order from vendor has been purchased successfully");
    }
}