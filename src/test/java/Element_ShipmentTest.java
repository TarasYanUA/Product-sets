import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.DriverProvider;
import taras.yanishevskyi.WorkPages.AdminPanel;
import taras.yanishevskyi.WorkPages.MotivationBlock;
import taras.yanishevskyi.WorkPages.ProductPage;
import java.time.Duration;
import java.util.ArrayList;

public class Element_ShipmentTest extends TestRunner{
    @Test(description = "Проверяем шаблон элемента мотивации 'Доставка'")
    public void elementShipmentOnStorefront(){
        AdminPanel adminPanel = new AdminPanel();
        MotivationBlock motivationBlock = new MotivationBlock();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickAndTypeSearchFieldOfAddons();
        adminPanel.chooseGeolocationAddon();
        motivationBlock.clickTabSettings();
        adminPanel.selectDropboxValueForGeolocation_Service("google");
        if(! DriverProvider.getDriver().findElement(By.xpath("//input[contains(@id, 'addon_option_geo_maps_show_shippings_on_product')]")).isSelected()){
            adminPanel.clickGeolocationCheckbox_ShowShippingCost();
        }
        adminPanel.clickGeolocationTabGoogle();
        adminPanel.clickAndTypeGeolocation_ApiKey();
        adminPanel.clickSaveButtonForAddonSettings();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickButtonOfAddon();
        adminPanel.navigateToDataManagementPage();
        motivationBlock.chooseElementDelivery();
        motivationBlock.selectElementPage_Template("addons/ab__motivation_block/blocks/components/item_templates/geo_maps.tpl");
        adminPanel.clickSaveButtonOnTopRight();
        (new WebDriverWait((motivationBlock.driver), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cs-icon.icon-shopping-cart")));
        adminPanel.clickStorefrontMainButton();
        ArrayList tabs = new ArrayList<String> (DriverProvider.getDriver().getWindowHandles());
        for(int ii = 0; ii <= 1; ii++) {
            DriverProvider.getDriver().switchTo().window(tabs.get(ii).toString());
        }
        ProductPage productPage = new ProductPage();
        productPage.chooseAnyProductOnStorefront();
        //Проверяем, что элемент "Доставка" присутствует на странице товара
        Assert.assertTrue(motivationBlock.getElementDeliveryOnStorefront().isEnabled(), "Element \"Delivery\" is not present on the product page");
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        motivationBlock.clickElementDeliveryOnStorefront();
    }
}