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
import java.time.Duration;
import java.util.List;

public class CreateSetForOneProduct extends TestRunner{
    @Test
    public void createSetForOneProductAndCheckQuantityOfProducts(){
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.hoverToProductPage();
        ProductPage productPage = adminPanel.navigateToProductPage();
        productPage.chooseProductTourStaffBag();
        productPage.clickTabProductSets();
        productPage.clickAddNewSet();
        productPage.clickAndTypeTitleOfSet("Клюшки для гольфа");
        productPage.clickAddProductsToSet();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickCategoryGolfClubs();
        productPage.clickSearchButtonForProductsAtSet();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickCheckboxForAllProducts();
        productPage.clickButtonAddProductsAndClose();
        productPage.clickButtonSaveOnEditProductPage(); //Первый набор товаров готов
        productPage.clickAddNewSet();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,500);");
        productPage.clickAndTypeTitleOfSet("Мячи для гольфа");
        productPage.clickAddProductsToSecondSet();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickSearchInCategoriesForSet();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        productPage.clickCategoryBallsForGolf();
        productPage.clickSearchButtonForProductsAtSet();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickCheckboxForAllProducts();
        productPage.clickButtonAddProductsAndClose();
        productPage.clickButtonSaveOnEditProductPage(); //Второй набор товаров готов
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickGearWheelOfProduct();
        productPage.clickPreviewButton();   //Находимся на витрине на странице товара
        adminPanel.focusBrowserTab();
        productPage.clickFieldSelectProducts();
        productPage.clickButtonSelectAllProductsForSet();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickButtonCloseForSet();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sol-action-buttons")));
        productPage.clickButtonAddToCart();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close.close")));
        CheckoutPage checkoutPage = productPage.navigateToCheckoutPage();   //Находимся на странице чекаута

        checkoutPage.clickSignIn();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(2)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar.ui-corner-all")));
        checkoutPage.clickSignInAtPopup();
        checkoutPage.clickHideAdminToolbar();
        checkoutPage.clickPaymentMethod();
        checkoutPage.choosePaymentMethod_PhoneOrdering();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkoutPage.checkAgreementTermsAndConditions();
        if(DriverProvider.getDriver().findElements(By.xpath("//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")).size()>0){
        checkoutPage.checkAgreementPersonalData();
        }
        checkoutPage.clickPaymentMethod();
        checkoutPage.clickButtonPlaceOrder();   //Заказ оформлен!
        //Проверяем, что мы на странице завершения заказа
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ty-checkout-complete__buttons")));
        Assert.assertTrue(DriverProvider.getDriver().getCurrentUrl().contains("checkout.complete&order_id="),
                "Process of placing the order has failed!");
        //Проверяем наличие всех товаров в заказе
        checkoutPage.clickButtonOrderDetails();
        List<WebElement> listOfProductsQuantity = checkoutPage.countQuantityOfProducts();
        int expectedQuantity = 6;
        int actualQuantity = listOfProductsQuantity.size();
        Assert.assertEquals(actualQuantity, expectedQuantity, "There is a wrong number of products at the order!");
    }
}
