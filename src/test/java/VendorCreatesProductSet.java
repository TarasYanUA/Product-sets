import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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

public class VendorCreatesProductSet extends TestRunner {
    @Test
    public void checkVendorCanCreateProductSet() throws IOException {
        //Устанавливаем модуль "Общие товары для продавцов"
        AdmHomePage admHomePage = new AdmHomePage();
        admHomePage.navigateTo_DownloadedAddonsPage();
        admHomePage.closeNotificationIfPresent();
        admHomePage.clickAndTypeSearchFieldAtManagementPage("Общие товары для продавцов");
        if (DriverProvider.getDriver().findElements(By.cssSelector("td.nowrap.right a[href*='addon=master_products']")).size() == 1) {
            admHomePage.clickButtonInstallAddon();
            (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(5))).until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector(".logo-menu__logo--cscart")));
            DriverProvider.getDriver().navigate().refresh();
        }

        //Работаем со страницей редактирования товара
        AdmProductPage admProductPage = admHomePage.navigateToSection_Products();
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        admProductPage.clickProductInSearchList();
        if (DriverProvider.getDriver().findElements(By.cssSelector("label[for*='elm_parent_product']")).isEmpty()) {
            admProductPage.clickProductVendor();
            admProductPage.selectProductBelongsToAllVendors();
            admProductPage.clickButtonSaveOnEditProductPage();
        }
        AdmCustomersPage admCustomersPage = admHomePage.navigateToSection_Customers();
        admCustomersPage.navigateToVendor_CsCart();
        admCustomersPage.logInAsUser();
        focusBrowserTab(1);

        SoftAssert softAssert = new SoftAssert();

        //Работаем как продавец
        admHomePage.navigateToSection_Products();
        admCustomersPage.button_AddProductFromCatalog.click();
        if (!admCustomersPage.buttons_SellThis.isEmpty()) {
            admCustomersPage.buttons_SellThis.get(0).click();
            admCustomersPage.clickAndTypeInStockField("19");

            //Проверяем, что вкладка "АВ: Комплекты для товаров" присутствует
            softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("#ab__product_sets")).isEmpty(),
                    "There is no the tab of our add-on on the editing product page!");

            admCustomersPage.clickTabProductSets();
            admProductPage.clickAddNewSet();
            makePause();
            admProductPage.clickAddProductsToSet();
            admCustomersPage.selectAProductForSet(By.cssSelector("input[value='255']"));
            admCustomersPage.selectAProductForSet(By.cssSelector("input[value='257']"));
            admCustomersPage.selectAProductForSet(By.cssSelector("input[value='278']"));
            admProductPage.clickButtonAddProductsAndClose();
            admProductPage.clickButtonSaveOnEditProductPage();
        } else {
            System.out.println("Кнопка button_SellThis отсутствует на странице.");
        }
        focusBrowserTab(0);
        admHomePage.navigateToSection_Products();
        admProductPage.clickAndTypeSearchFieldAtProductPage("X-Box 360");
        //admProductPage.clickIconThumbUp();
        admProductPage.clickProductInSearchList();
        admProductPage.clickGearWheelOfProduct();
        admProductPage.clickPreviewButton();
        focusBrowserTab(2);

        //Работаем с витриной
        //Проверяем, что мы на странице продавца CS-Cart
        String actualPage = DriverProvider.getDriver().findElement(By.cssSelector("div.ut2-vendor-block__name a")).getText();
        String expectedPage = "CS-Cart";
        softAssert.assertEquals(actualPage, expectedPage, "It is not a page of 'CS-Cart' vendor!");

        //На странице продавца проверяем, что комплект присутствует
        softAssert.assertTrue(DriverProvider.getDriver().findElement(By.cssSelector(".sol-inner-container")).isEnabled(),
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
        if (!getDriver().findElements(By.xpath("//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")).isEmpty()) {
            checkoutPage.checkAgreementPersonalData();
        }
        checkoutPage.checkAgreementSimtech();
        checkoutPage.clickPaymentMethod();
        checkoutPage.clickButtonPlaceOrder();   //Заказ оформлен!

        //Проверяем, что мы на странице завершения заказа
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ty-checkout-complete__buttons")));
        softAssert.assertTrue(getDriver().getCurrentUrl().contains("checkout.complete&order_id="),
                "Process of placing the order has failed!");

        //Проверяем наличие всех товаров в заказе
        checkoutPage.clickButtonOrderDetails();
        List<WebElement> listOfProductsQuantity = checkoutPage.countQuantityOfProducts();
        int actualQuantity = listOfProductsQuantity.size();
        int expectedQuantity = 4;
        softAssert.assertEquals(actualQuantity, expectedQuantity, "There is a wrong number of products at the order!");
        takeScreenShot("220 Order from vendor has been purchased successfully");
        softAssert.assertAll();
    }
}