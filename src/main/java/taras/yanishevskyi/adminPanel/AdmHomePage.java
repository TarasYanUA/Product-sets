package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;
import taras.yanishevskyi.constants.DriverProvider;

public class AdmHomePage extends AbstractPage{
    public AdmHomePage(){
        super();
    }

    public WebElement hoverAddonsDropDown(){
        return addonsDropDown;
    }
    public void clickAddonsManagementPage(){
        addonsManagementPage.click();
    }
    public void navigateToAddonsManagementPage() {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        clickAddonsManagementPage();
    }
    public void hoverToProductPage(){
        WebElement elementOfMenuProducts = hoverMenuProducts();
        Actions hoverMenuProducts = new Actions(DriverProvider.getDriver());
        hoverMenuProducts.moveToElement(elementOfMenuProducts);
        hoverMenuProducts.perform();
    }
    public void navigateToAppearanceSettingsOfCsCart(){
        WebElement mainRightNavBar = hoverSettingsOfCsCart();
        Actions hoverSettingsOfCsCart = new Actions(DriverProvider.getDriver());
        hoverSettingsOfCsCart.moveToElement(mainRightNavBar);
        hoverSettingsOfCsCart.perform();
        clickAppearanceSettingsOfCsCart();
    }
    public AdmProductPage navigateToProductPage(){
        productPage.click();
        return new AdmProductPage();
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement buttonAuthorization;
    @FindBy(css = "#bp_off_bottom_panel")
    private WebElement bottomAdminPanel;

    //Настройки CS-Cart
    @FindBy(css = "#mainrightnavbar")
    private WebElement settingsOfCsCart;
    @FindBy(css = "#elm_menu_settings_Appearance")
    private WebElement appearanceSettingsOfCsCart;
    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement settingQuickView;
    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;

    @FindBy(xpath = "(//a[@class=\"dropdown-toggle addons\"])[1]")
    private WebElement addonsDropDown;
    @FindBy(id = "elm_menu_addons_manage_addons")
    private WebElement addonsManagementPage;
    @FindBy(css = "#elm_addon")
    private WebElement searchFieldAtManagementPage;
    @FindBy(css = "tr#addon_master_products td:nth-of-type(8) div div:nth-of-type(2) a")
    public WebElement buttonInstallAddon;
    @FindBy(xpath = "//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(xpath = "//span[text()='Товары']")
    private WebElement productPage;


    
    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public void closeBottomAdminPanel(){
        bottomAdminPanel.click();
    }
    public WebElement hoverSettingsOfCsCart(){
        return settingsOfCsCart;
    }
    public void clickAppearanceSettingsOfCsCart(){
        appearanceSettingsOfCsCart.click();
    }
    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }

    public void clickAndTypeSearchFieldAtManagementPage(String value){
        searchFieldAtManagementPage.click();
        searchFieldAtManagementPage.sendKeys(value);
        searchFieldAtManagementPage.sendKeys(Keys.ENTER);
    }
    public void clickButtonInstallAddon(){
        buttonInstallAddon.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
}