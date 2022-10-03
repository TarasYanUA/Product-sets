import org.testng.annotations.Test;
import taras.yanishevskyi.workPages.AdminPanel;

public class Test1 extends TestRunner{
    @Test
    public void createSeveralSetsForOneProduct(){
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickButtonOfAddon();
    }
}
