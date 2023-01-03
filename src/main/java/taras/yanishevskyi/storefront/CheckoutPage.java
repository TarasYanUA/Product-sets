package taras.yanishevskyi.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.yanishevskyi.constants.AbstractPage;
import java.util.List;

public class CheckoutPage extends AbstractPage {
    @FindBy(xpath = "//input[contains(@id, 'id_accept_terms')]")
    private WebElement agreementTermsAndConditions;
    @FindBy(xpath = "//input[contains(@id, 'gdpr_agreements_checkout_place_order')]")
    public WebElement agreementPersonalData;
    @FindBy(css = ".litecheckout__submit-btn")
    private WebElement buttonPlaceOrder;
    @FindBy(xpath = "//label[@class='b--pay-way__opted']//div[@class='b--pay-way__opted__text']")
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
    public WebElement cityField;


    public void checkAgreementTermsAndConditions(){
        agreementTermsAndConditions.click();
    }
    public void checkAgreementPersonalData(){
        agreementPersonalData.click();
    }
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
        cityField.click();
        cityField.sendKeys(value);
    }
}