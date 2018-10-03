package org.fundacionjala.pivotal;
import org.fundacionjala.pivotal.model.AbstractPage;
import org.fundacionjala.pivotal.model.PageObjectFactory;
import org.fundacionjala.pivotal.model.pageObject.dashboard.PageDashboard;
import org.testng.annotations.Test;
/**
 * @author KevinHerrera - AWT-[01].
 * @version 0.1
 */
public class DashboardTest {

    /**
     * .
     */
    @Test
    public void pressCreateProjectButtonTest() {
        PageDashboard dashboard = new PageDashboard(AbstractPage.instanceFirefoxDriver(), PageDashboard.PAGE_URL);
        PageObjectFactory.initElements(dashboard.getDriver(), dashboard);
        dashboard.clickCreateNewProject();
    }
}
