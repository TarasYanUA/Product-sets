package taras.yanishevskyi.storefront;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.yanishevskyi.constants.AbstractPage;
import taras.yanishevskyi.constants.DriverProvider;
import java.util.List;

public class CheckoutPage extends AbstractPage {
    public CheckoutPage() {super();}
    @FindBy(xpath = "//input[contains(@id, 'id_accept_terms')]")
    private WebElement agreementTermsAndConditions;

    @FindBy(xpath = "//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")
    public WebElement agreementPersonalData;

    @FindBy(xpath = "//input[contains(@id, 'product_agreements')]")
    private WebElement agreementSimtech;

    @FindBy(css = ".litecheckout__submit-btn")
    private WebElement buttonPlaceOrder;

    @FindBy(css = ".b--pay-ship__opted__text__title")
    private WebElement paymentMethod;

    @FindBy(css = "#payments_2")
    private WebElement paymentMethod_PhoneOrdering;

    @FindBy(id = "bp_off_bottom_panel")
    private WebElement hideAdminToolbar;

    @FindBy(xpath = "//a[contains(@href, 'orders.details&order_id=')]")
    private WebElement buttonOrderDetails;

    @FindBy(css = ".ty-float-left.ty-orders-detail__table-image")
    private List<WebElement> quantityOfProducts;

    @FindBy(css = "select#litecheckout_country")
    private WebElement countryField;

    @FindBy(css = "input#litecheckout_city_state")
    private WebElement cityField;

    @FindBy(xpath = "(//div[@class='ty-float-left ty-orders-detail__table-image'])[4]")
    private WebElement blockProductInformation;


    public void checkAgreementTermsAndConditions(){
        agreementTermsAndConditions.click();
    }
    public void checkAgreementPersonalData(){
        agreementPersonalData.click();
    }
    public void checkAgreementSimtech(){agreementSimtech.click();}
    public void clickButtonPlaceOrder(){
        buttonPlaceOrder.click();
    }
    public void clickPaymentMethod(){
        paymentMethod.click();
    }
    public void choosePaymentMethod_PhoneOrdering(){
        paymentMethod_PhoneOrdering.click();
    }
    public void clickHideAdminToolbar(){
        hideAdminToolbar.click();
    }
    public void clickButtonOrderDetails(){
        buttonOrderDetails.click();
    }
    public List<WebElement> countQuantityOfProducts(){
        return quantityOfProducts;
    }
    public void clickCountryField(){countryField.click();}

    public Select getCountryField(){
        return new Select(countryField);
    }
    public void selectCountryField(String value){
        getCountryField().selectByValue(value);
    }

    public void clickAndTypeCityField(String value){
        cityField.click();
        cityField.clear();
        cityField.sendKeys(value);
        cityField.sendKeys(Keys.ENTER);
    }
    public WebElement hoverBlockProductInformation(){
        return blockProductInformation;
    }
    public void scrollToBlockProductInformation(){
        WebElement elementForScroll = hoverBlockProductInformation();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.scrollToElement(elementForScroll);
        scrollToBlock.perform();
    }
}