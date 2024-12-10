package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;
import taras.yanishevskyi.constants.DriverProvider;
import java.util.List;

public class AdmCustomersPage extends AbstractPage {
    public AdmCustomersPage() {
        super();
    }

    @FindBy(css = "tr[data-ca-id='1'] .btn__icon--caret")
    WebElement gearwheelOfVendor_CsCart;

    @FindBy(xpath = "//div[@class='btn-group dropleft open']//a[contains(text(), 'Просмотреть администраторов продавца')]")
    WebElement button_viewVendorAdmins;

    @FindBy(css = "tr[data-ca-id='10'] .btn__icon--caret")
    WebElement gearwheelOfVendorAdmin_Bill;

    @FindBy(css = "a[href*='act_as_user&user_id=10']")
    WebElement button_actAsUser;


    //Работаем с админкой Продавца
    @FindBy(css = "a[href$='dispatch=products.master_products']")
    public WebElement button_AddProductFromCatalog;

    @FindBy(css = "a[href*='dispatch=products.sell_master_product']")
    public List<WebElement> buttons_SellThis;

    @FindBy(css = "#elm_in_stock")
    WebElement inStockField;

    @FindBy(css = "#ab__product_sets")
    public WebElement tabProductSets;


    public void navigateToVendor_CsCart() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(gearwheelOfVendor_CsCart).perform();
        gearwheelOfVendor_CsCart.click();
        button_viewVendorAdmins.click();
    }

    public void logInAsUser() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(gearwheelOfVendorAdmin_Bill).perform();
        gearwheelOfVendorAdmin_Bill.click();
        button_actAsUser.click();
    }

    public void clickAndTypeInStockField(String value) {
        inStockField.click();
        inStockField.click();
        inStockField.sendKeys(value);
    }

    public void clickTabProductSets() {
        tabProductSets.click();
    }

    public void selectAProductForSet(By by) {
        DriverProvider.getDriver().findElement(by).click();
    }
}