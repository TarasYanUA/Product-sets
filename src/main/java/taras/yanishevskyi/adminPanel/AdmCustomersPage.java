package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;
import taras.yanishevskyi.constants.DriverProvider;

public class AdmCustomersPage extends AbstractPage {
    public AdmCustomersPage() {super();}

    @FindBy(css = "tr[data-ca-id='1'] .btn__icon--caret")
    private WebElement gearwheelOfVendor_CsCart;

    @FindBy(css = "a[href*='act_as_user&user_id=10']")
    private WebElement actAsUser;

    //Работаем с админкой Продавца
    @FindBy(css = "a[href='#products']")
    private WebElement vendorProductMenu;

    @FindBy(css = "a[href*='dispatch=products.manage'][class*='nav__menu-subitem']")
    private WebElement vendorProductPage;

    @FindBy(xpath = "//a[text()='Товары, которые можно продавать']")
    private WebElement sidebar_ProductsThatCanBeSold;

    @FindBy(css = "a[href*='product_id=248'][class*='btn']")
    private WebElement productButtonSell;

    @FindBy(css = "#elm_in_stock")
    private WebElement inStockField;

    @FindBy(css = "#ab__product_sets")
    public WebElement tabProductSets;


    public void navigateToVendor_CsCart() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(gearwheelOfVendor_CsCart).perform();
        gearwheelOfVendor_CsCart.click();
    }

    public void clickActAsUser(){actAsUser.click();}
    public void clickVendorProductMenu(){vendorProductMenu.click();}
    public void clickVendorProductPage(){vendorProductPage.click();}
    public void clickSidebar_ProductsThatCanBeSold(){sidebar_ProductsThatCanBeSold.click();}
    public void clickProductButtonSell(){productButtonSell.click();}
    public void clickAndTypeInStockField(String value){
        inStockField.click();
        inStockField.click();
        inStockField.sendKeys(value);
    }
    public void clickTabProductSets(){tabProductSets.click();}
    public void selectAProductForSet(By by){
        DriverProvider.getDriver().findElement(by).click();
    }
}