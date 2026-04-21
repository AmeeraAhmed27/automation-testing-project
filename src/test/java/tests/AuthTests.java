package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import io.qameta.allure.*;

public class AuthTests extends BaseTest {

    @Test(description = "Test Case #3: Open registration page")
    @Feature("Authentication")
    @Story("User Registration")
    @Description("Verifies that clicking the registration link from the Home Page correctly redirects the user to the Signup page with the proper URL and Title.")
    @Severity(SeverityLevel.BLOCKER)
    public void testOpenRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        Allure.step("Navigate to the Registration page from Home");
        registrationPage.navigateToRegistrationFromHome();

        Allure.step("Verify page title contains 'تسجيل الدخول'");
        wait.until(ExpectedConditions.titleContains("تسجيل الدخول"));

        Allure.step("Assert that the URL contains '/signup'");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/signup"),
                "The URL does not contain '/signup'. Current URL: " + currentUrl);
    }


    @Test(description = "Test Case #4: Register with an empty username field")
    @Feature("Authentication")
    @Story("User Registration Validation")
    @Description("Ensures the system prevents registration and displays the correct Arabic error message when the username field is left empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegisterWithEmptyUsername() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        Allure.step("Navigate to the Registration page");
        registrationPage.navigateToRegisterPage();

        Allure.step("Fill registration form while leaving the username field empty");
        registrationPage.fillFormExceptUsername("ameera.test@example.com", "770000000");

        Allure.step("Click the 'Create Account' button");
        registrationPage.clickCreateAccount();

        Allure.step("Validate the error message for empty username");
        String actualError = registrationPage.getValidationErrorMessage();
        String expectedError = "اسم المستخدم مطلوب";

        Assert.assertEquals(actualError, expectedError,
                "Validation message mismatch! Expected: " + expectedError + " but found: " + actualError);
    }
}