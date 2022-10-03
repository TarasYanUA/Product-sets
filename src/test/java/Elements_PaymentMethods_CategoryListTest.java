import org.openqa.selenium.By;;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Elements_PaymentMethods_CategoryListTest extends TestRunner {
    @Test(description = "Проверяем шаблон элемента мотивации 'Варианты оплаты'")
    public void manageTwoMotivationElements(){
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickButtonOfAddon();
        adminPanel.navigateToDataManagementPage();  //я на странице "Управление данными"
        MotivationBlock motivationBlock = new MotivationBlock();
        motivationBlock.chooseElementPaymentMethods();
        motivationBlock.selectElementPage_Template("addons/ab__motivation_block/blocks/components/item_templates/payment_methods.tpl");
        adminPanel.clickSaveButtonOnTopRight();
        motivationBlock.clickABMenuDropdown();
        motivationBlock.chooseSectionDataManagementAtABMenu();

        if(DriverProvider.getDriver().findElement(By.xpath("//a[@id=\"sw_select_4_wrap\"]")).getText().contains("Выкл.")){
            motivationBlock.clickStatusButton();
            motivationBlock.clickStatusActive();
        }
        (new WebDriverWait((motivationBlock.driver), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cs-icon.icon-shopping-cart")));
        adminPanel.clickStorefrontMainButton();
        ArrayList tabs = new ArrayList<String> (DriverProvider.getDriver().getWindowHandles());
        for(int ii = 0; ii <= 1; ii++) {
            DriverProvider.getDriver().switchTo().window(tabs.get(ii).toString());
        }
        ProductPage productPage = new ProductPage();
        productPage.chooseAnyProductOnStorefront();
        //scroll to block
        scrollToMotivationBlock(productPage);
        productPage.clickElementOnProductPage_PaymentMethods();
        productPage.clickElementOnProductPage_FindSimilar();
        //Проверяем, что у элемента "Найдите похожие" отображается список категорий вместо обычного текста
        Assert.assertTrue(productPage.getCategoryListAtElement().isEnabled());
    }
    private static WebElement scrollToMotivationBlock(ProductPage productPage) {
        WebElement elementOfMotivationBlock = productPage.getMotivationBlockOnProductPage();
        Actions hoverMotivationBlock = new Actions(DriverProvider.getDriver());
        hoverMotivationBlock.moveToElement(elementOfMotivationBlock).scrollByAmount(0,500);
        hoverMotivationBlock.perform();
        return elementOfMotivationBlock;
    }
}