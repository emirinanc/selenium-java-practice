package gui;

import config.Properties;
import pages.HomePage;
import pages.CareersPage;
import pages.JobsPage;
import config.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CareersTest {
    private WebDriver driver;
    private String browser;


    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browserName) {
        this.browser = browserName;
        driver = WebDriverFactory.createDriver(browser);
        driver.get(Properties.URL_HOME.getString());
    }

    @Test(priority = 1)
    public void testHomePageLoaded() {
        Assert.assertEquals(driver.getCurrentUrl(), Properties.URL_HOME.getString());
    }

    @Test(priority = 2)
    public void testCareersPage() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToCareers();

        CareersPage careersPage = new CareersPage(driver);

        Assert.assertTrue(careersPage.isLifeAtInsiderBlockVisible(),
                "Life at Insider block is not visible");

        Assert.assertTrue(careersPage.isLocationsBlockVisible(),
                "Locations is not visible");

        Assert.assertTrue(careersPage.isTeamsBlockVisible(),
                "Teams is not visible");
    }

    @Test(priority = 3)
    public void testQAJobsFiltering() {
        driver.get(Properties.URL_QA_JOBS.getString());

        JobsPage jobsPage = new JobsPage(driver);
        jobsPage.displayAllQaJobs();
        jobsPage.filterJob();
        jobsPage.clickViewRole();

    }

    @Test(priority = 4)
    public void testJobApplicationRedirection() {
        JobsPage jobsPage = new JobsPage(driver);
        jobsPage.clickViewRole();

        // Add assertion for Lever Application form page
        Assert.assertTrue(driver.getCurrentUrl().contains("lever.co"));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}