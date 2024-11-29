package utils;

import gui.CareersTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Starting: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Now we cast to CareersTest instead of InsiderCareerTest
        Object testClass = result.getInstance();
        WebDriver driver = ((CareersTest) testClass).getDriver();

        if (driver != null) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Path screenshotDir = Paths.get("/Users/emirinanc/Projects/test-automation-practice/practice", "screenshots");
            try {
                Files.createDirectories(screenshotDir);
            } catch (IOException e) {
                System.err.println("Failed to create screenshots directory: " + e.getMessage());
                return;
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destinationPath = screenshotDir.resolve(
                    String.format("%s_%s.png", result.getName(), timestamp)
            );

            try {
                Files.copy(screenshot.toPath(), destinationPath);
                System.out.println("Screenshot saved: " + destinationPath);
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}