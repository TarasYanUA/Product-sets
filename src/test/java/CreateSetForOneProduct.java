import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.constants.DriverProvider;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import taras.yanishevskyi.storefront.CheckoutPage;
import taras.yanishevskyi.adminPanel.AdmProductPage;
import taras.yanishevskyi.storefront.StProductPage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static taras.yanishevskyi.constants.DriverProvider.getDriver;

/*
- Работаем с категорией "Гольф", где для товара создаём комплект товаров из двух групп.
- Проверяем наличие кнопок "Быстрый просмотр" и "Удалить" у выбранных товаров.
- Покупаем выбранный товар из комплекта через кнопку "Быстрый просмотр" и проверяем, что товар добавился в Корзину.
- Отключаем товар из второй группы и проверяем, что группа без товаров не отображается на витрине.
- Совершаем покупку всего комплекта и проверяем наличие товаров в заказе.
*/

public class CreateSetForOneProduct extends TestRunner{
    @Test
    public void createSetForOneProductAndCheckQuantityOfProducts() throws IOException {
        //Включаем Быстрый просмотр
        AdmHomePage admHomePage = new AdmHomePage();
        admHomePage.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = admHomePage.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            admHomePage.settingQuickView.click();
        }
        admHomePage.clickSaveButtonOfSettings();

        //Работаем с товаром
        AdmProductPage admProductPage = admHomePage.navigateToProductPage();
        admProductPage.chooseProductTourStaffBag();
        admProductPage.clickTabProductSets();
        admProductPage.clickAddNewSet();
        admProductPage.clickAndTypeTitleOfSet("Клюшки для гольфа");
        admProductPage.clickAddProductsToSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        admProductPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        admProductPage.clickCategoryGolfClubs();
        admProductPage.clickSearchButtonForProductsAtSet();
        makePause();
        admProductPage.clickCheckboxForAllProducts();
        admProductPage.clickButtonAddProductsAndClose();
        admProductPage.clickButtonSaveOnEditProductPage(); //Первый набор товаров готов
        admProductPage.clickAddNewSet();
        ((JavascriptExecutor) getDriver()).executeScript("scroll(0,500);");
        admProductPage.clickAndTypeTitleOfSet("Мячи для гольфа");
        admProductPage.clickAddProductsToSecondSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        admProductPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        admProductPage.clickCategoryBallsForGolf();
        admProductPage.clickSearchButtonForProductsAtSet();
        makePause();
        admProductPage.clickCheckboxForAllProducts();
        admProductPage.clickButtonAddProductsAndClose();
        admProductPage.clickButtonSaveOnEditProductPage(); //Второй набор товаров готов
        makePause();
        admProductPage.clickGearWheelOfProduct();
        admProductPage.clickPreviewButton();

        //Работаем с витриной на странице товара
        focusBrowserTab(1);
        StProductPage stProductPage = new StProductPage();
        stProductPage.clickFieldSelectProducts();
        stProductPage.clickButtonSelectAllProductsForSet();
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
        stProductPage.clickButtonCloseForSet();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sol-action-buttons")));

        //Покупаем выбранный товар из комплекта через кнопку "Быстрый просмотр"
        stProductPage.clickButtonQuickviewAtSelectedProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".object-container")));
        stProductPage.clickButtonAddToCart_QuickView();
        makePause();
        //Проверяем, что товар добавлен в корзину
        int actualCartValue = Integer.parseInt(DriverProvider.getDriver().findElement(By.cssSelector(".ty-minicart-count")).getText());
        Assert.assertTrue(actualCartValue >= 1, "The product has not been added to the cart!");

        //Выключаем товар из второй группы и проверяем, что группа без товаров не отображается на витрине.
        focusBrowserTab(0);
        admProductPage.clickProductWilsonStaff();
        admProductPage.clickSwitcherDisable();
        admProductPage.clickButtonSaveOnEditProductPage(); //Выключили товар "мячи"
        focusBrowserTab(1);
        DriverProvider.getDriver().navigate().refresh();
        stProductPage.clickFieldSelectProducts();
        //Проверяем, что в комплекте присутствует только одна группа
        int sizeOfGroups = DriverProvider.getDriver().findElements(By.cssSelector(".sol-optiongroup-label")).size();
        Assert.assertTrue(sizeOfGroups < 2);
        //Покупаем комплект товаров
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
        Assert.assertTrue(actualQuantity > 3, "There is a wrong number of products at the order!");
        //Эту проверку можно заменить на чёткое количество товаров в заказе после исправления ошибки https://abteam.planfix.com/task/37774
        takeScreenShot("120 Order has been purchased successfully");
    }
}