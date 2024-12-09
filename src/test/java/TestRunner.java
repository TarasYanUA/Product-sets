import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import taras.yanishevskyi.constants.DriverProvider;
import taras.yanishevskyi.adminPanel.AdmHomePage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("myErrorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
        DriverProvider.getDriver().quit();
        DriverProvider.destroyDriver();
    }

    public void focusBrowserTab(int tabNum) {
        ArrayList tabs = new ArrayList<> (DriverProvider.getDriver().getWindowHandles());
        DriverProvider.getDriver().switchTo().window(tabs.get(tabNum).toString());
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".cm-btn-success")).isEmpty()){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-btn-success")).click();
        }
    }

    public void takeScreenShot(String screenshotName) throws IOException {
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
    }

    public void makePause(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}