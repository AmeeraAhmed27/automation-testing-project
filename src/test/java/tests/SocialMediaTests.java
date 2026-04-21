package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;
import java.util.Set;

@Epic("UI Regression")
@Feature("Social Media Links")
public class SocialMediaTests extends BaseTest {
    private FooterPage footer;

    @Test(description = "Test Case #8: Verify Facebook link")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Footer Navigation")
    @Description("Verifies that the Facebook icon correctly redirects to the official Facebook page in a new tab.")
    public void testFacebookLink() {
        footer = new FooterPage(driver);

        Allure.step("Scroll to footer and click Facebook icon");
        footer.scrollToFooter();
        footer.clickFacebookIcon();

        Allure.step("Switch to the new browser tab");
        footer.switchToNewTab();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("facebook.com"),
                "The URL does not contain facebook.com! Actual: " + currentUrl);
    }

    @Test(description = "Test Case #G: Verify LinkedIn link")
    @Severity(SeverityLevel.NORMAL)
    @Story("Footer Navigation")
    @Description("Verifies that the LinkedIn icon correctly redirects to the official LinkedIn profile.")
    public void testLinkedInLink() {
        String originalWindow = driver.getWindowHandle();
        footer = new FooterPage(driver);

        Allure.step("Click the LinkedIn icon");
        footer.clickLinkedinIcon();

        Allure.step("Detect and switch to the LinkedIn tab");
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("linkedin.com"),
                "The URL does not contain linkedin.com! Actual: " + currentUrl);
    }

    @Test(description = "Test Case #10: Verify YouTube link")
    @Severity(SeverityLevel.MINOR)
    @Story("Footer Navigation")
    @Description("Verifies visibility and redirection of the YouTube icon. Note: This test is designed to catch the missing link bug.")
    public void testYouTubeLink() {
        String originalWindow = driver.getWindowHandle();
        footer = new FooterPage(driver);

        Allure.step("Check if YouTube icon exists in the DOM");
        boolean isDisplayed = footer.isYouTubeLinkDisplayed();

        if (!isDisplayed) {
            Allure.addAttachment("Bug Report", "YouTube icon not found in footer on page: " + driver.getCurrentUrl());
        }

        Assert.assertTrue(isDisplayed, "BUG FOUND: YouTube link is missing from the website!");

        Allure.step("Click YouTube icon and verify redirection");
        footer.clickYouTubeIcon();

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("youtube.com"),
                "The URL does not lead to YouTube! Actual: " + currentUrl);
    }
}