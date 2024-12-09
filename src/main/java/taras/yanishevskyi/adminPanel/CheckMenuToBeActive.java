package taras.yanishevskyi.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import taras.yanishevskyi.constants.DriverProvider;

import java.util.Collection;
import java.util.List;


public interface CheckMenuToBeActive {
    default void checkMenuToBeActive(String menuDispatch, WebElement menu) {
        checkMenu_Addons_ToBeActive();

        String selector = "a[href$='" + menuDispatch + "'].main-menu-1__link";
        Collection<WebElement> elements = DriverProvider.getDriver().findElements(By.cssSelector(selector + " ~ a[class*='main-menu-1__toggle--active']"));
        try {
            if (elements.isEmpty())
                menu.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchElementException e) {
        }
    }


    static void checkMenu_Addons_ToBeActive() {
        // Проверяем наличие хотя бы одного из элементов
        List<WebElement> firstElement = DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../../..//div[contains(@class, 'in collapse')]"));
        List<WebElement> secondElement = DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../..//a[contains(@class, 'main-menu-1__toggle--active')]"));

        if (!firstElement.isEmpty() || !secondElement.isEmpty()) {
            // Если хотя бы один элемент присутствует, выполняем действие
            DriverProvider.getDriver().findElement(By.xpath("//span[text()='_ab__addons']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}