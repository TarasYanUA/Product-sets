package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;

public class AdmProductPage extends AbstractPage {
    public AdmProductPage() {super();}
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
    @FindBy(css = "div.table-responsive-wrapper a[href$='id=188']")
    private WebElement productWilsonStaff;
    @FindBy(css = "input#elm_product_status_0_d")
    private WebElement switcherDisable;
    @FindBy(css = ".sidebar-field input")
    private WebElement searchFieldAtProductPage;
    @FindBy(css = ".products-list__image--link img")
    private WebElement productInSearchList;
    @FindBy(css = "#sw_product_data_company_id_selector_wrap_")
    private WebElement productVendor;
    @FindBy(css = "a[title='Все продавцы (общий товар)']")
    private WebElement productBelongsToAllVendors;
    @FindBy(css = ".cs-icon.icon-thumbs-up")
    private WebElement iconThumbUp;


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
    public void clickAndTypeTitleOfSet(String value){
        titleOfSet.click();
        titleOfSet.sendKeys(value);
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
    public void clickProductWilsonStaff(){productWilsonStaff.click();}
    public void clickSwitcherDisable(){switcherDisable.click();}
    public void clickAndTypeSearchFieldAtProductPage(String value){
        searchFieldAtProductPage.click();
        searchFieldAtProductPage.sendKeys(value);
        searchFieldAtProductPage.sendKeys(Keys.ENTER);
    }
    public void clickProductInSearchList(){
        productInSearchList.click();
    }
    public void clickProductVendor(){
        productVendor.click();
    }
    public void selectProductBelongsToAllVendors(){
        productBelongsToAllVendors.click();
    }
    public void clickIconThumbUp(){iconThumbUp.click();}
}