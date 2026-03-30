import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import taras.yanishevskyi.constants.DriverProvider;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import taras.yanishevskyi.storefront.Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import static taras.yanishevskyi.constants.Constants.BASIC_URL;

public class TestRunner {

    @BeforeMethod
    public void prepareBrowser() {
        DriverProvider.getDriver().get(BASIC_URL);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //Общая задержка
        DriverProvider.getDriver().manage().window().maximize();    //Размер браузера на весь экран
        AdmHomePage admHomePage = new AdmHomePage();
        admHomePage.clickButtonAuthorization();
        admHomePage.closeBottomAdminPanel();
        Utils.closeAllNotifications();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("myErrorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        DriverProvider.getDriver().quit();
        DriverProvider.destroyDriver();
    }

    public void takeScreenShot(String screenshotName) throws IOException {
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
    }
}