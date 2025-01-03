package taras.yanishevskyi.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.yanishevskyi.constants.AbstractPage;

public class StProductPage extends AbstractPage {
    public StProductPage() {
        super();
    }

    @FindBy(css = ".ty-btn.ty-btn__primary.cm-notification-close")
    private WebElement checkoutPage;

    @FindBy(css = "div[class='sol-inner-container'] input")
    private WebElement fieldSelectProducts;

    @FindBy(className = "sol-select-all")
    private WebElement buttonSelectAllProductsForSet;

    @FindBy(className = "sol-close")
    private WebElement buttonCloseForSet;

    @FindBy(xpath = "(//button[contains(@id, 'button_cart_')])[1]")
    private WebElement buttonAddToCart;

    @FindBy(css = "ul[class='ab__ps-list'] .ut2-quick-view-button")
    private WebElement buttonQuickViewAtSelectedProduct;

    @FindBy(css = "div.object-container button[id*='button_cart_ajax'] span")
    private WebElement buttonAddToCart_QuickView;


    public CheckoutPage navigateToCheckoutPage() {
        checkoutPage.click();
        return new CheckoutPage();
    }

    public void clickFieldSelectProducts() {
        fieldSelectProducts.click();
    }

    public void clickButtonSelectAllProductsForSet() {
        buttonSelectAllProductsForSet.click();
    }

    public void clickButtonCloseForSet() {
        buttonCloseForSet.click();
    }

    public void clickButtonAddToCart() {
        buttonAddToCart.click();
    }

    public void clickButtonQuickViewAtSelectedProduct() {
        buttonQuickViewAtSelectedProduct.click();
    }

    public void clickButtonAddToCart_QuickView() {
        buttonAddToCart_QuickView.click();
    }
}