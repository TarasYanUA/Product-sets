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
    public void navigateToAddonsManagementPage() {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        addonsManagementPage.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
    public AdmProductPage navigateToProductPage(){
        WebElement elementOfMenuProducts = hoverMenuProducts();
        Actions hoverMenuProducts = new Actions(DriverProvider.getDriver());
        hoverMenuProducts.moveToElement(elementOfMenuProducts);
        hoverMenuProducts.perform();
        productPage.click();
        return new AdmProductPage();
    }
    public WebElement hoverMenuCustomers(){
        return menuCustomers;
    }
    public AdmCustomersPage navigateToCustomersPage(){
        WebElement elementOfMenuCustomers = hoverMenuCustomers();
        Actions hoverMenuCustomers = new Actions(DriverProvider.getDriver());
        hoverMenuCustomers.moveToElement(elementOfMenuCustomers);
        hoverMenuCustomers.perform();
        customersPage.click();
        return new AdmCustomersPage();
    }

    public WebElement hoverSettingsOfCsCart(){
        return settingsOfCsCart;
    }
    public void navigateToAppearanceSettingsOfCsCart(){
        WebElement mainRightNavBar = hoverSettingsOfCsCart();
        Actions hoverSettingsOfCsCart = new Actions(DriverProvider.getDriver());
        hoverSettingsOfCsCart.moveToElement(mainRightNavBar);
        hoverSettingsOfCsCart.perform();
        clickAppearanceSettingsOfCsCart();
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
    @FindBy(css = "td.nowrap.right a[href*='addon=master_products']")
    public WebElement buttonInstallAddon;
    @FindBy(xpath = "//li[contains(@class, 'dropdown nav__header-main-menu-item')]//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(xpath = "//span[text()='Товары']")
    private WebElement productPage;
    @FindBy(xpath = "//li[contains(@class, 'dropdown nav__header-main-menu-item')]//a[@href='#customers']")
    private WebElement menuCustomers;
    @FindBy(xpath = "//span[text()='Администраторы продавца']")
    private WebElement customersPage;


    
    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public void closeBottomAdminPanel(){
        bottomAdminPanel.click();
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
}