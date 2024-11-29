package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By companyMenuLocator = By.linkText("Company");
    private final By careersSubMenuLocator = By.linkText("Careers");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCareers() {
        click(companyMenuLocator);
        click(careersSubMenuLocator);
    }
}