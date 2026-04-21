package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CoursesPage;
import io.qameta.allure.*;

public class CourseTests extends BaseTest {


    @Test(description = "Test Case #2: Open course details")
    @Feature("Course Catalog")
    @Story("Course Details Navigation")
    @Description("Verifies that a user can navigate from the 'All Courses' list to a specific course's detail page and see the 'About' section.")
    @Severity(SeverityLevel.NORMAL)
    public void testNavigationToCourseDetails() {
        CoursesPage coursesPage = new CoursesPage(driver);

        Allure.step("Click 'All Courses' from the Home Page");
        coursesPage.clickAllCourses();

        Allure.step("Wait for navigation to the list page");
        wait.until(ExpectedConditions.urlContains("/ar/courses"));

        Allure.step("Click the first available course card");
        coursesPage.clickFirstCourseCard();

        Allure.step("Verify redirection to the specific course details page");
        wait.until(ExpectedConditions.not(ExpectedConditions.urlMatches(".*/ar/courses$")));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/courses/"), "Failed to reach details page. URL: " + currentUrl);

        Allure.step("Check if the 'About Course' section is displayed");
        Assert.assertTrue(coursesPage.isAboutSectionDisplayed(), "Section 'حول الدورة التدريبية' not found!");
    }
}