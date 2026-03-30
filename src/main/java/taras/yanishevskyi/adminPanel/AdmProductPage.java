package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;
import taras.yanishevskyi.storefront.Utils;

import java.util.List;

public class AdmProductPage extends AbstractPage {
    public AdmProductPage() {
        super();
    }

    @FindBy(css = ".nav__actions-bar .dropdown-icon--tools")
    private WebElement gearwheelOfProduct;

    @FindBy(css = ".dropdown-menu a[target='_blank']")
    private WebElement previewButton;

    @FindBy(xpath = "//a[@class=\"products-list__image--link\"][contains(@href, 'product_id=187')]")
    private WebElement productTourStaffBag;

    @FindBy(xpath = "//li[@id='ab__product_sets']")
    private WebElement tabProductSets;

    @FindBy(xpath = "//div[@id=\"content_ab__product_sets\"]//a[@class='btn cm-tooltip']")
    private WebElement addNewSet;

    @FindBy(css = "input[name^='product_data[ab__product_sets]'][name$='[set]']")
    private List<WebElement> titleOfSet;

    @FindBy(css = ".buttons-container .cm-external-click")
    private List<WebElement> addProductsToSet;

    @FindBy(css = ".ui-dialog-content .object-categories-add__picker")
    private WebElement searchInCategoriesForSet;

    @FindBy(css = "span[id*='off_comp'][class='hand cm-combination-cat cm-uncheck hidden']")
    public List<WebElement> collapsedCategoryList;

    @FindBy(xpath = "//span[text()='Магазин: CS-Cart']/..//span[contains(@class, 'icon-caret-right')]")
    public WebElement expandCategoryList;

    @FindBy(xpath = "//tr[contains(@id, 'cat_203')]/..//span[contains(@class, 'icon-caret-right')]")
    public WebElement popup_category_Sport;

    @FindBy(xpath = "//tr[contains(@id, 'cat_211')]/..//span[contains(@class, 'icon-caret-right')]")
    public WebElement popup_category_Golf;

    @FindBy(id = "category_212")
    public WebElement categoryGolfClubs;

    @FindBy(id = "category_213")
    public WebElement categoryBallsForGolf;

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

    @FindBy(css = ".context-search__input")
    private WebElement searchFieldAtProductPage;

    @FindBy(css = ".products-list__image--link img")
    private WebElement productInSearchList;

    @FindBy(css = "#sw_product_data_company_id_selector_wrap_")
    private WebElement productVendor;

    @FindBy(css = "a[title='Все продавцы (общий товар)']")
    private WebElement productBelongsToAllVendors;

    @FindBy(css = "a[id*='premoderation_approve_']")
    private List<WebElement> iconThumbUp;


    public void clickGearWheelAndPreviewButton() {
        gearwheelOfProduct.click();
        previewButton.click();
    }

    public void chooseProductTourStaffBag() {
        productTourStaffBag.click();
        Utils.closeAllNotifications();
    }

    public void clickTabProductSets() {
        tabProductSets.click();
    }

    public void addNewSet() {
        addNewSet.click();
        Utils.makePause(1000);
    }

    public void clickAndTypeTitleOfSet(String value) {
        titleOfSet.getLast().click();
        titleOfSet.getLast().sendKeys(value);
    }

    public void clickAddProductsToSet() {
        addProductsToSet.getLast().click();
        Utils.isElementPresent(By.className("ui-dialog-title"));
    }

    public void searchCategoryForProduct(WebElement category) {
        searchInCategoriesForSet.click();
        Utils.isElementPresent(By.className("ui-dialog-title"));
        if (!collapsedCategoryList.isEmpty()) {
            Utils.waitForElementToBeClickableAndClick(expandCategoryList);
            Utils.waitForElementToBeClickableAndClick(popup_category_Sport);
            Utils.waitForElementToBeClickableAndClick(popup_category_Golf);
            Utils.waitForElementToBeClickableAndClick(category);
        } else {
            Utils.waitForElementToBeClickableAndClick(category);
        }
        searchButtonForProductsAtSet.click();
        Utils.makePause(2000);
    }

    public void clickCheckboxForAllProducts() {
        checkboxForAllProducts.click();
    }

    public void clickButtonAddProductsAndClose() {
        buttonAddProductsAndClose.click();
    }

    public void clickButtonSaveOnEditProductPage() {
        buttonSaveOnEditProductPage.click();
        Utils.makePause(2000);
    }

    public void clickProductWilsonStaff() {
        productWilsonStaff.click();
    }

    public void clickSwitcherDisable() {
        switcherDisable.click();
    }

    public void clickAndTypeSearchFieldAtProductPage(String value) {
        searchFieldAtProductPage.click();
        searchFieldAtProductPage.sendKeys(value);
        Utils.makePause(3000);
    }

    public void clickProductInSearchList() {
        productInSearchList.click();
    }

    public void clickProductVendor() {
        productVendor.click();
    }

    public void selectProductBelongsToAllVendors() {
        productBelongsToAllVendors.click();
    }

    public void clickIconThumbUp() {
        if (!iconThumbUp.isEmpty())
            iconThumbUp.getFirst().click();
    }
}