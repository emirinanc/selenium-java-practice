package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JobsPage extends BasePage {

    private final By qaJobs = By.linkText("See all QA jobs");
    private final By clearX = By.xpath("//span[@id='select2-filter-by-location-container']//span[@title='Remove all items'][normalize-space()='×']");
    private final By filterLocator = By.id("select2-filter-by-location-container");
    private final By jobList = By.id("select2-filter-by-location-result-bxwr-All");
    private final By searchIstanbul = By.id("select2-filter-by-location-result-40kg-Istanbul, Turkey");
    private final By viewRoleButton = By.xpath("//a[contains(text(), 'View Role')]");

    public JobsPage(WebDriver driver) {
        super(driver);
    }

    public void displayAllQaJobs() {
        click(qaJobs);
    }

    public void filterJob() {
        handleCookieBanner(driver);
        clearAndSelectFromCombobox(driver, clearX, filterLocator, jobList, searchIstanbul);
    }

    public void clickViewRole() {
        WebElement roleButton = findElement(viewRoleButton);
        moveTo(roleButton);
        roleButton.click();
    }
}
