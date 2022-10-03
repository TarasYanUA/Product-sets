import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import java.util.List;

public class DisplayElementForSpecifiedCategoriesOnlyTest extends TestRunner{
    @Test
    public void checkCategoriesForMotivationElement(){
        AdminPanel adminPanel = new AdminPanel();
        MotivationBlock motivationBlock = new MotivationBlock();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickButtonOfAddon();
        adminPanel.navigateToDataManagementPage();  //я на странице "Управление данными"
        motivationBlock.clickItemOurAdvantages();
        //Добавляем категории для мотив. элемента
        motivationBlock.clickTabCategories();
        if(DriverProvider.getDriver().findElement(By.xpath("//p[text()='Все категории включены']")).isDisplayed()){
        motivationBlock.clickAddCategoriesButton();
        (new WebDriverWait((motivationBlock.driver), Duration.ofSeconds(4)))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-dialog-title")));
        adminPanel.chooseCategoryMenClothing();
        adminPanel.chooseCategoryPlayStation();
        adminPanel.clickSavePopup();
        adminPanel.clickSaveButtonOnTopRight();
        }
        //Проверяем, что обе категории находятся в таблице
        (new WebDriverWait((motivationBlock.driver), Duration.ofSeconds(4)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-dialog-title")));
        Assert.assertTrue(motivationBlock.getCategoryMenClothingExists().isDisplayed());
        Assert.assertTrue(motivationBlock.getCategoryPlayStation().isDisplayed());
        //Переходим на витрину
        adminPanel.clickStorefrontMainButton();
        ArrayList tabs = new ArrayList<String> (DriverProvider.getDriver().getWindowHandles());
        for(int ii = 0; ii <= 1; ii++) {
            DriverProvider.getDriver().switchTo().window(tabs.get(ii).toString());
        }
        ProductPage productPage = new ProductPage();
        productPage.chooseAnyProductOnStorefront();
        //Проверяем, что элемента "Наши примущемтва" нет на странице левого товара
        List<WebElement> listOfTabsOfBlock = productPage.getNumberOfTabsOfBlock();
        int expectedNumberOfTabs = 3;
        int actualNumberOfTabs = listOfTabsOfBlock.size();
        Assert.assertEquals(actualNumberOfTabs, expectedNumberOfTabs, "Motivation element is present for a wrong category!");
        //Проверяем, что элемент "Наши примущемтва" присутствует для нужной категорий
        productPage.clickMainMenuOnStorefront();
        productPage.navigateToApparelCategoryOnStorefront();
        productPage.navigateToMenClothCategoryOnStorefront();
        productPage.chooseAnyProductOnStorefront();
        Assert.assertTrue(productPage.getElementOnProductPage_OurAdvantages().isEnabled());
    }
}
