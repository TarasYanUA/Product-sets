package taras.yanishevskyi.workPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.AbstractPage;
import java.util.List;

public class ProductPage extends AbstractPage {
    public ProductPage(){
        super();
    }
    @FindBy(xpath = "(//div[@class='sidebar-field']//input[@type='text'])[1]")
    private WebElement searchFieldForProduct;
    @FindBy(className = "products-list__image")
    private WebElement productGoPro;
    @FindBy(css = ".cs-icon.icon-reorder")
    private WebElement listOfCategories;
    @FindBy(xpath = "//div[@class=\" btn-bar btn-toolbar nav__actions-bar dropleft\"]//div[@class=\"btn-group dropleft\"]")
    private WebElement gearwheelOfProduct;
    @FindBy(xpath = "//a[@href[substring(.,string-length(.) - string-length('preview') + 1) = 'preview']]")
    private WebElement previewButton;

    
    public void clickAndTypeToSearchField(){
        searchFieldForProduct.click();
        searchFieldForProduct.sendKeys("GoPro");
        searchFieldForProduct.sendKeys(Keys.ENTER);
    }
    
    public void chooseProductGoPro(){
        productGoPro.click();
    }
    
    public void clickAtListOfCategories(){
        listOfCategories.click();
    }
    
    public void clickGearWheelOfProduct(){
        gearwheelOfProduct.click();
    }
    
    public void clickPreviewButton(){
        previewButton.click();
    }
}