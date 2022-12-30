package taras.yanishevskyi.workPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.AbstractPage;

public class ProductPage extends AbstractPage {
    public ProductPage(){
        super();
    }
    @FindBy(xpath = "(//div[@class='sidebar-field']//input[@type='text'])[1]")
    private WebElement searchFieldForProduct;
    @FindBy(className = "products-list__image")
    private WebElement productOnStorefront;
    @FindBy(css = ".cs-icon.icon-reorder")
    private WebElement listOfCategories;
    @FindBy(xpath = "//div[@class=\" btn-bar btn-toolbar nav__actions-bar dropleft\"]//div[@class=\"btn-group dropleft\"]")
    private WebElement gearwheelOfProduct;
    @FindBy(xpath = "//a[contains(@href, 'preview')][@id=\"187\"]")
    private WebElement previewButton;
    @FindBy(xpath = "//a[@class=\"products-list__image--link\"][contains(@href, 'product_id=187')]")
    private WebElement productTourStaffBag;
    @FindBy(xpath = "//li[@id='ab__product_sets']")
    private WebElement tabProductSets;
    @FindBy(xpath = "//div[@id=\"content_ab__product_sets\"]//a[@class='btn cm-tooltip']")
    private WebElement addNewSet;
    @FindBy(xpath = "//input[@name=\"product_data[ab__product_sets][1][set]\"]")
    private WebElement titleOfSet;
    @FindBy(css = "a.cm-external-click.btn")
    private WebElement addProductsToSet;
    @FindBy(xpath = "//a[contains(@data-ca-external-click-id, 'opener_picker_ab__ps_set__products_')]")
    private WebElement addProductsToSecondSet;
    @FindBy(xpath = "//div[@class='sidebar-field']//span[@class='cs-icon icon-reorder']")
    private WebElement searchInCategoriesForSet;
    @FindBy(id = "category_212")
    private WebElement categoryGolfClubs;
    @FindBy(id = "category_213")
    private WebElement categoryBallsForGolf;
    @FindBy(xpath = "//input[@name='dispatch[products.picker]']")
    private WebElement searchButtonForProductsAtSet;
    @FindBy(css = "input.cm-check-items")
    private WebElement checkboxForAllProducts;
    @FindBy(xpath = "//input[@class=\"btn cm-process-items cm-dialog-closer btn-primary\"]")
    private WebElement buttonAddProductsAndClose;
    @FindBy(xpath = "//a[@class='btn btn-primary cm-submit btn-primary cm-product-save-buttons']")
    private WebElement buttonSaveOnEditProductPage;
    @FindBy(css = "div[class='sol-inner-container'] div")
    private WebElement fieldSelectProducts;
    @FindBy(className = "sol-select-all")
    private WebElement buttonSelectAllProductsForSet;
    @FindBy(className = "sol-close")
    private WebElement buttonCloseForSet;
    @FindBy(id = "button_cart_187")
    private WebElement buttonAddToCart;
    @FindBy(css = ".ty-btn.ty-btn__primary.cm-notification-close")
    private WebElement checkoutPage;
    @FindBy(css = "ul[class='ab__ps-list'] i[class='ut2-icon ut2-icon-baseline-visibility']")
    private WebElement buttonQuickviewAtSelectedProduct;
    @FindBy(css = "div.object-container button[id*='button_cart_ajax'] span")
    private WebElement buttonAddToCart_QuickView;
    @FindBy(css = "a[href$='id=188']")
    private WebElement productWilsonStaff;
    @FindBy(css = "input#elm_product_status_0_d")
    private WebElement switcherDisable;
    
    public void clickAndTypeToSearchField(){
        searchFieldForProduct.click();
        searchFieldForProduct.sendKeys("GoPro");
        searchFieldForProduct.sendKeys(Keys.ENTER);
    }
    public CheckoutPage navigateToCheckoutPage(){
        checkoutPage.click();
        return new CheckoutPage();
    }

    public void chooseProductGoPro(){
        productOnStorefront.click();
    }
    public void clickAtListOfCategories(){
        listOfCategories.click();
    }
    public void clickGearWheelOfProduct(){
        gearwheelOfProduct.click();
    }
    public void clickPreviewButton(){
        previewButton.click();
    }
    public void chooseProductTourStaffBag(){
        productTourStaffBag.click();
    }
    public void clickTabProductSets(){
        tabProductSets.click();
    }
    public void clickAddNewSet(){
        addNewSet.click();
    }
    public String clickAndTypeTitleOfSet(String value){
        titleOfSet.click();
        titleOfSet.sendKeys(value);
        return value;
    }
    public void clickAddProductsToSet(){
        addProductsToSet.click();
    }
    public void clickAddProductsToSecondSet(){
        addProductsToSecondSet.click();
    }
    public void clickSearchInCategoriesForSet(){
        searchInCategoriesForSet.click();
    }
    public void clickCategoryGolfClubs(){
        categoryGolfClubs.click();
    }
    public void clickCategoryBallsForGolf(){
        categoryBallsForGolf.click();
    }
    public void clickSearchButtonForProductsAtSet(){
        searchButtonForProductsAtSet.click();
    }
    public void clickCheckboxForAllProducts(){
        checkboxForAllProducts.click();
    }
    public void clickButtonAddProductsAndClose(){
        buttonAddProductsAndClose.click();
    }
    public void clickButtonSaveOnEditProductPage(){
        buttonSaveOnEditProductPage.click();
    }
    public void clickFieldSelectProducts(){
        fieldSelectProducts.click();
    }
    public void clickButtonSelectAllProductsForSet(){
        buttonSelectAllProductsForSet.click();
    }
    public void clickButtonCloseForSet(){
        buttonCloseForSet.click();
    }
    public void clickButtonAddToCart(){
        buttonAddToCart.click();
    }
    public void clickButtonQuickviewAtSelectedProduct(){buttonQuickviewAtSelectedProduct.click();}
    public void clickButtonAddToCart_QuickView(){buttonAddToCart_QuickView.click();}
    public void clickProductWilsonStaff(){productWilsonStaff.click();}
    public void clickSwitcherDisable(){switcherDisable.click();}
}