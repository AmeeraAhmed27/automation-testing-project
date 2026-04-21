package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.LoginPage;

import java.time.Duration;
import io.qameta.allure.*;

public class E2ETest extends BaseTest {


    @Test(description = "Test Case #7: end-to-end")
    @Epic("Sales & Enrollment")
    @Feature("Course Purchase Flow")
    @Story("End-to-End Course Subscription")
    @Description("Verifies the full user journey: logging in with valid credentials, selecting a course from the catalog, subscribing, and confirming the course appears in the shopping cart.")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompletePurchaseFlow() {

        Allure.step("Navigate to Login and authenticate");
        driver.get("https://eyouthlearning.com/ar/auth/login");
        loginPage.login("ameeraahmed200027@gmail.com", "123456@Me");

        Allure.step("Navigate to 'All Courses' catalog");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement allCoursesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ar/courses']")));
        allCoursesLink.click();

        Allure.step("Confirm catalog page load and subscribe to the first course");
        wait.until(ExpectedConditions.titleContains("جميع الدورات"));
        coursesPage.subscribeToFirstCourse();

        Allure.step("Navigate to Cart and verify product presence");
        driver.get("https://eyouthlearning.com/ar/cart");

        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page did not load!");

        String expectedTitle = "إدارة التغيير والعمل الجماعي";
        Allure.addAttachment("Expected Course Title", expectedTitle);

        Assert.assertEquals(cartPage.getFirstCourseTitle(), expectedTitle, "The course in the cart does not match!");

        System.out.println("E2E Test Passed: Course " + expectedTitle + " is successfully in the cart.");
    }


    @Test(description = "Test Case #11: Verify course cards UI")
    @Story("Card Component Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test performs a deep audit of the Course Card DOM to ensure all required brand elements are rendered for the end user.")
    public void testCourseCardUIStructure() {

        Allure.step("Navigate to 'All Courses' catalog");
        coursesPage.clickAllCourses(); // Assuming you have this method

        Allure.step("Verify catalog page title is correct");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.titleContains("جميع الدورات"));

        Allure.step("Execute individual element assertions for the first card");
        coursesPage.verifyFirstCardStructure();
    }

}
