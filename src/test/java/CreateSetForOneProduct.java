import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.DriverProvider;
import taras.yanishevskyi.workPages.AdminPanel;
import taras.yanishevskyi.workPages.CheckoutPage;
import taras.yanishevskyi.workPages.ProductPage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.valueOf;
import static taras.yanishevskyi.DriverProvider.getDriver;

/*
- Работаем с категорией "Гольф", где для товара создаём комплект товаров из двух групп.
- Проверяем наличие кнопок "Быстрый просмотр" и "Удалить" у выбранных товаров.
- Покупаем выбранный товар из комплекта через кнопку "Быстрый просмотр" и проверяем, что в Корзину товар добавился.
- Выключаем товар из второй группы и проверяем, что группа без товаров не отображается на витрине.
- Совершаем покупку всего комплекта и проверяем успешность заказа.
*/

public class CreateSetForOneProduct extends TestRunner{
    @Test
    public void createSetForOneProductAndCheckQuantityOfProducts() throws IOException {
        //Включаем Быстрый просмотр
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = adminPanel.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            adminPanel.settingQuickView.click();
        }
        adminPanel.clickSaveButtonOfSettings();

        //Работаем с товаром
        adminPanel.hoverToProductPage();
        ProductPage productPage = adminPanel.navigateToProductPage();
        productPage.chooseProductTourStaffBag();
        productPage.clickTabProductSets();
        productPage.clickAddNewSet();
        productPage.clickAndTypeTitleOfSet("Клюшки для гольфа");
        productPage.clickAddProductsToSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickCategoryGolfClubs();
        productPage.clickSearchButtonForProductsAtSet();
        makePause();
        productPage.clickCheckboxForAllProducts();
        productPage.clickButtonAddProductsAndClose();
        productPage.clickButtonSaveOnEditProductPage(); //Первый набор товаров готов
        productPage.clickAddNewSet();
        ((JavascriptExecutor) getDriver()).executeScript("scroll(0,500);");
        productPage.clickAndTypeTitleOfSet("Мячи для гольфа");
        productPage.clickAddProductsToSecondSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickCategoryBallsForGolf();
        productPage.clickSearchButtonForProductsAtSet();
        makePause();
        productPage.clickCheckboxForAllProducts();
        productPage.clickButtonAddProductsAndClose();
        productPage.clickButtonSaveOnEditProductPage(); //Второй набор товаров готов
        makePause();
        productPage.clickGearWheelOfProduct();
        productPage.clickPreviewButton();

        //Работаем с витриной на странице товара
        focusBrowserTab(1);
        productPage.clickFieldSelectProducts();
        productPage.clickButtonSelectAllProductsForSet();
        makePause();
        //Проверяем, что кнопка "Быстрый просмотр" присутствует у товаров из комплекта
        int sizeOfQuickviewButtons = getDriver().findElements(By
                .cssSelector("ul[class='ab__ps-list'] i[class='ut2-icon ut2-icon-baseline-visibility']")).size();
        Assert.assertTrue(sizeOfQuickviewButtons > 1, "There is no buttons 'QuickView' at selected products!");
        //Проверяем, что кнопка "Удаления" товара присутствует у товаров из комплекта
        int sizeOfDeleteButtons = getDriver().findElements(By
                .cssSelector("ul[class='ab__ps-list'] span[class='ab__ps-item_quick-delete']")).size();
        Assert.assertTrue(sizeOfDeleteButtons > 1, "There is no buttons 'Delete' at selected products!");
        takeScreenShot("110 Product page with product set");
        productPage.clickButtonCloseForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sol-action-buttons")));

        //Покупаем выбранный товар из комплекта через кнопку "Быстрый просмотр"
        productPage.clickButtonQuickviewAtSelectedProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".object-container")));
        productPage.clickButtonAddToCart_QuickView();
        makePause();
        //Проверяем, что товар добавлен в корзину
        int actualCartValue = valueOf(DriverProvider.getDriver().findElement(By.cssSelector(".ty-minicart-count")).getText());
        Assert.assertTrue(actualCartValue >= 1, "The product has not been added to the cart!");

        //Выключаем товар из второй группы и проверяем, что группа без товаров не отображается на витрине.
        focusBrowserTab(0);
        productPage.clickProductWilsonStaff();
        productPage.clickSwitcherDisable();
        productPage.clickButtonSaveOnEditProductPage(); //Выключили товар "мячи"
        focusBrowserTab(1);
        DriverProvider.getDriver().navigate().refresh();
        productPage.clickFieldSelectProducts();
        //Проверяем, что в комплекте присутствует только одна группа
        int sizeOfGroups = DriverProvider.getDriver().findElements(By.cssSelector(".sol-optiongroup-label")).size();
        Assert.assertTrue(sizeOfGroups < 2);
        productPage.clickButtonCloseForSet();
        productPage.clickButtonAddToCart();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close.close")));
        CheckoutPage checkoutPage = productPage.navigateToCheckoutPage();   //Находимся на странице чекаута

/*        checkoutPage.clickSignIn();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar.ui-corner-all")));
        checkoutPage.clickSignInAtPopup();
        checkoutPage.clickHideAdminToolbar();*/

        //Добавить шаги: страна -- город -- Ентер


        checkoutPage.clickPaymentMethod();
        checkoutPage.choosePaymentMethod_PhoneOrdering();
        makePause();
        checkoutPage.checkAgreementTermsAndConditions();
        if(getDriver().findElements(By.xpath("//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")).size()>0){
        checkoutPage.checkAgreementPersonalData();
        }
        checkoutPage.clickPaymentMethod();
        checkoutPage.clickButtonPlaceOrder();   //Заказ оформлен!
        //Проверяем, что мы на странице завершения заказа
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ty-checkout-complete__buttons")));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout.complete&order_id="),
                "Process of placing the order has failed!");
        //Проверяем наличие всех товаров в заказе
        checkoutPage.clickButtonOrderDetails();
        List<WebElement> listOfProductsQuantity = checkoutPage.countQuantityOfProducts();
        int expectedQuantity = 6;
        int actualQuantity = listOfProductsQuantity.size();
        Assert.assertEquals(actualQuantity, expectedQuantity, "There is a wrong number of products at the order!");
    }

    public boolean isAbsent(By by){
        try {
            return DriverProvider.getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
