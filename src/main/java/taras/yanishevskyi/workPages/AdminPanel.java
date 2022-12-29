package taras.yanishevskyi.workPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.AbstractPage;
import taras.yanishevskyi.DriverProvider;

public class AdminPanel extends AbstractPage{
    public AdminPanel(){
        super();
    }

    public void navigateToAddonsPage(AdminPanel adminPanel) {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        navigateToAddonsManagementPage();
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
    public ProductPage navigateToProductPage(){
        productPage.click();
        return new ProductPage();
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
    @FindBy(xpath = "//button[@class=\"close cm-notification-close cm-notification-close-ajax\"]")
    private WebElement closeWarning;
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

    public WebElement hoverAddonsDropDown(){
        return addonsDropDown;
    }
    public void navigateToAddonsManagementPage(){
        addonsManagementPage.click();
    }
    public void clickCloseWarning(){
        closeWarning.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
}