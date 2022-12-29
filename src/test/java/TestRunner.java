import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import taras.yanishevskyi.DriverProvider;
import taras.yanishevskyi.workPages.AdminPanel;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import static taras.yanishevskyi.Constants.BASIC_URL;

/*
Модуль "Product sets" (Комплекты для товара (Up-Sell)) + тема Юни2(MVRu).

Тест можно запустить двумя способами:
1) Сам тест
2) Через Surefire отчёт: перейти в "Терминал" и ввести "mvn clean test". После этого в папке "target -> surefire reports"
 открыть файл "index.html" с помощью браузера.

*/

public class TestRunner {

    @BeforeMethod
    public void prepareBrowser() {
        DriverProvider.getDriver().get(BASIC_URL);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4)); //Общая задержка
        DriverProvider.getDriver().manage().window().maximize();    //Размер браузера на весь экран
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.clickButtonAuthorization();
        adminPanel.closeBottomAdminPanel();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
        DriverProvider.getDriver().quit();
        DriverProvider.destroyDriver();
    }

    public void focusBrowserTab(int tabNum) {
        ArrayList tabs = new ArrayList<String> (DriverProvider.getDriver().getWindowHandles());
        DriverProvider.getDriver().switchTo().window(tabs.get(tabNum).toString());
    }

    public void takeScreenShot(String testName) throws IOException {
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + testName + ".jpg"));
    }

    public void makePause(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}