package taras.yanishevskyi.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.yanishevskyi.constants.DriverProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean isElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementAndClick(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            return true; // кликнули успешно
        } catch (TimeoutException e) {
            return false; // элемент не появился за 8 секунд
        }
    }

    public static void focusBrowserTab(int tabNum) {
        ArrayList tabs = new ArrayList<>(DriverProvider.getDriver().getWindowHandles());
        DriverProvider.getDriver().switchTo().window(tabs.get(tabNum).toString());
        Utils.waitForElementAndClick(By.cssSelector(".cm-btn-success"));
    }

    public static void waitForSpinnerDisappear() {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style='display: block;']")));
        makePause(1500);
    }

    public static void waitForElementToBeClickableAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        makePause(1000);
    }

    public static void makePause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ignored) {}
    }

    public static void closeAllNotifications() {
        List<WebElement> alertNotifications = DriverProvider.getDriver().findElements(By.cssSelector(".close.cm-notification-close"));

        if (!alertNotifications.isEmpty()) {
            for (int i = 0; i < alertNotifications.size(); i++) {
                alertNotifications.getFirst().click();
                Utils.makePause(500);
            }
        }
    }

    public static void scrollIntoViewAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
        element.click();
    }
}