package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {

            throw new TimeoutException("Element " + locator + " was not visible after timeout");
        }
    }
    protected void clearAndSelectFromCombobox(WebDriver driver, By remove, By comboboxId, By containerId, By optionId) {

        WebElement clearX = wait.until(ExpectedConditions.elementToBeClickable(remove));
        moveTo(clearX);
        actions.click(clearX).perform();

        WebElement combobox = wait.until(ExpectedConditions.elementToBeClickable(comboboxId));
        moveTo(combobox);
        actions.click(combobox).perform();

        WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(containerId));
        moveTo(list);
        actions.click(list).perform();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionId));
        moveTo(option);
        option.click();
    }

    public void handleCookieBanner(WebDriver driver) {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("wt-cli-accept-all-btn")  // or whatever the accept button's ID is
            ));
            acceptButton.click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.id("wt-cli-cookie-banner-title")
            ));
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already handled");
        }
    }

    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    protected boolean isDisplayed(By locator) {
        try {
            WebElement element = findElement(locator);
            moveTo(element);
            waitForElementVisible(locator);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected void moveTo (WebElement element) {
        actions.moveToElement(element).perform();
    }
}
