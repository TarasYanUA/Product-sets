import org.testng.Assert;
import org.testng.annotations.Test;
import taras.yanishevskyi.WorkPages.AdminPanel;
import taras.yanishevskyi.WorkPages.MotivationBlock;
import taras.yanishevskyi.WorkPages.ProductPage;

public class VerticalAppearanceWithFramesTest extends TestRunner{

    @Test(description = "Проверяем общие настройки модуля - вертикальный вид с обрамлением", priority = 2)
    public void verticalBlockAppearance() {
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickButtonOfAddon();
        adminPanel.navigateToGeneralSettings();

        MotivationBlock motivationBlock = new MotivationBlock();
        motivationBlock.clickTabSettings();
        motivationBlock.clickTabAppearance();
        motivationBlock.selectSettingTemplateVariant("vertical_tabs");
        motivationBlock.selectSettingBlockStyle("fill");
        motivationBlock.clicksettingBlockColor();
        motivationBlock.chooseBlueColorForBlock();
        motivationBlock.clickSubmitColorForBlock();
        motivationBlock.clickSaveButtonForSettings();
        //Переходим на страницу товара
        adminPanel.hoverToProductPage();
        ProductPage productPage = adminPanel.navigateToProductPage();
        productPage.clickAndTypeToSearchField();
        productPage.chooseProductGoPro();
        productPage.clickGearWheelOfProduct();
        productPage.clickPreviewButton();   //Находимся на витрине на странице товара
        adminPanel.focusBrowserTab();
        //Проверяем, что блок вертикальный
        String actualResult = String.valueOf(productPage.getVerticalBlock());
        Assert.assertTrue(actualResult.contains("ab__vertical_tabs"),"Block is not vertical or missed on the product page.");
    }
}