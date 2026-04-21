package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;
import io.qameta.allure.*;

public class SearchTests extends BaseTest {
    @Test(description = "Test Case #1: Search with a valid keyword")
    @Feature("Search Functionality")
    @Story("Search Suggestions Navigation")
    @Description("Verifies that a user can search for a specific course and navigate to its details page via the search dropdown.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchAndNavigateToCourse() {
        HomePage homePage = new HomePage(driver);
        String searchKeyword = "كيف تنضم إلى البنك";
        String expectedUrlPart = "/ar/courses/how-to-join-a-bank";

        Allure.step("Enter search keyword: " + searchKeyword);
        homePage.searchForKeyword(searchKeyword);

        Allure.step("Click on the specific course result from the dropdown");
        homePage.clickCourseSearchResult();

        Allure.step("Verify the URL redirection to: " + expectedUrlPart);
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = longWait.until(ExpectedConditions.urlContains(expectedUrlPart));

        Assert.assertTrue(isUrlCorrect,
                "The browser did not navigate to the expected course page! Current URL: " + driver.getCurrentUrl());
    }
}