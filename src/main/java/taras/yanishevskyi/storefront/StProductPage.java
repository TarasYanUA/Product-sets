package taras.yanishevskyi.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StProductPage {
    public StProductPage() {super();}
    @FindBy(css = ".ty-btn.ty-btn__primary.cm-notification-close")
    private WebElement checkoutPage;
    @FindBy(css = "div[class='sol-inner-container'] div")
    private WebElement fieldSelectProducts;
    @FindBy(className = "sol-select-all")
    private WebElement buttonSelectAllProductsForSet;
    @FindBy(className = "sol-close")
    private WebElement buttonCloseForSet;
    @FindBy(id = "button_cart_187")
    private WebElement buttonAddToCart;
    @FindBy(css = "ul[class='ab__ps-list'] i[class='ut2-icon ut2-icon-baseline-visibility']")
    private WebElement buttonQuickviewAtSelectedProduct;
    @FindBy(css = "div.object-container button[id*='button_cart_ajax'] span")
    private WebElement buttonAddToCart_QuickView;

    public CheckoutPage navigateToCheckoutPage(){
        checkoutPage.click();
        return new CheckoutPage();
    }
    public void clickFieldSelectProducts(){
        fieldSelectProducts.click();
    }
    public void clickButtonSelectAllProductsForSet(){
        buttonSelectAllProductsForSet.click();
    }
    public void clickButtonCloseForSet(){
        buttonCloseForSet.click();
    }
    public void clickButtonAddToCart(){
        buttonAddToCart.click();
    }
    public void clickButtonQuickviewAtSelectedProduct(){buttonQuickviewAtSelectedProduct.click();}
    public void clickButtonAddToCart_QuickView(){buttonAddToCart_QuickView.click();}
}
