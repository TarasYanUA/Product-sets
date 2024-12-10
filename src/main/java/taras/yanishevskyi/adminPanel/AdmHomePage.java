package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;

import java.util.List;

public class AdmHomePage extends AbstractPage implements CheckMenuToBeActive {
    public AdmHomePage(){
        super();
    }

    @FindBy(css = ".btn.btn-primary")
    WebElement buttonAuthorization;

    @FindBy(css = "#bp_off_bottom_panel")
    WebElement bottomAdminPanel;

    @FindBy(css = ".btn.btn-primary.cm-submit")
    WebElement saveButtonOfSettings;

    @FindBy(css = ".cm-notification-close")
    public List<WebElement> closeNotification;

    public void closeNotificationIfPresent() {
        if (!closeNotification.isEmpty()) {
            closeNotification.get(0).click();
        }
    }

    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public void closeBottomAdminPanel(){
        bottomAdminPanel.click();
    }
    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }
    public void clickButtonInstallAddon(){
        button_InstallAddon.click();
    }


    //Меню "Товары --Товары"
    @FindBy(xpath = "//span[text()='Товары']")
    WebElement menu_Products;

    @FindBy(id = "products_products")
    WebElement section_Products;

    public AdmProductPage navigateToSection_Products() {
        checkMenuToBeActive("dispatch=products.manage", menu_Products);
        section_Products.click();
        return new AdmProductPage();
    }


    //Меню "Продавцы -- Продавцы"
    @FindBy(xpath = "//span[text()='Продавцы']")
    WebElement menu_Customers;
    @FindBy(id = "vendors_vendors")
    WebElement section_Customers;


    public AdmCustomersPage navigateToSection_Customers(){
        checkMenuToBeActive("dispatch=companies.manage", menu_Customers);
        section_Customers.click();
        return new AdmCustomersPage();
    }


    //Меню "Модули -- Скачанные модули"
    @FindBy(xpath = "//span[text()='Модули']")
    WebElement menu_Addons;

    @FindBy(id = "addons_downloaded_add_ons")
    WebElement section_DownloadedAddons;

    @FindBy(css = "#elm_addon")
    WebElement searchFieldOnPage_DownloadedAddons;

    @FindBy(css = "td.nowrap.right a[href*='addon=master_products']")
    public WebElement button_InstallAddon;


    public void navigateTo_DownloadedAddonsPage() {
        checkMenuToBeActive("dispatch=addons.manage", menu_Addons);
        section_DownloadedAddons.click();
    }

    public void clickAndTypeSearchFieldAtManagementPage(String value){
        searchFieldOnPage_DownloadedAddons.click();
        searchFieldOnPage_DownloadedAddons.sendKeys(value);
        searchFieldOnPage_DownloadedAddons.sendKeys(Keys.ENTER);
    }


    //Меню "Настройки -- Общие настройки -- Внешний вид"
    @FindBy(id = "administration")
    private WebElement menu_Settings;

    @FindBy(css = "a[href$='section_id=General']")
    private WebElement section_GeneralSettings;

    @FindBy(css = "a[href*='section_id=Appearance']")
    private WebElement section_Appearance;

    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement settingQuickView;


    public void navigateTo_AppearanceSettings() {
        menu_Settings.click();
        section_GeneralSettings.click();
        section_Appearance.click();
    }
}