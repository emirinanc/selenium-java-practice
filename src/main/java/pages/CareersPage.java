package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CareersPage extends BasePage {
    private final By locationsBlock = By.id("career-our-location");
    private final By teamsBlock = By.id("career-find-our-calling");

    private final By lifeAtInsiderBlock = By.xpath("//h2[normalize-space()='Life at Insider']");

    private final By qaJobsLink = By.xpath("//a[contains(@href, '/careers/quality-assurance')]");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLocationsBlockVisible() {
        return isDisplayed(locationsBlock);
    }

    public boolean isTeamsBlockVisible() {
        return isDisplayed(teamsBlock);
    }

    public boolean isLifeAtInsiderBlockVisible() {
        return isDisplayed(lifeAtInsiderBlock);
    }


    public void clickQAJobs() {
        click(qaJobsLink);
    }
}