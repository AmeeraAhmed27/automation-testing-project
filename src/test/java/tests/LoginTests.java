package tests;

import base.BaseTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import io.qameta.allure.*;
public class LoginTests extends BaseTest {

    @Test(description = "Test Case #5: Login with invalid credentials")
    @Feature("Authentication")
    @Story("Login Validation")
    @Description("Verifies that the system displays a 'Account not found' error message when entering unregistered or incorrect credentials.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidData() {
        Allure.step("Navigate to Login page");
        loginPage.navigateToLogin();

        Allure.step("Enter invalid credentials");
        loginPage.login("invalid_user@test.com", "WrongPassword123!");

        Allure.step("Validate the 'Account not found' error message");
        String expectedError = "لم يتم العثور على حساب نشط للبيانات المقدمة";
        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(actualError, expectedError, "The error message for invalid login is incorrect!");
    }

    @Test(description = "Test Case #6: Login with empty fields")
    @Feature("Authentication")
    @Story("Login Validation")
    @Description("Verifies that mandatory field validations are triggered when both email and password fields are left empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithEmptyData() {
        Allure.step("Navigate to Login page");
        loginPage.navigateToLogin();

        Allure.step("Submit login form with empty fields");
        loginPage.login("", "");

        Allure.step("Validate Arabic error messages for required fields");
        String expectedEmailMsg = "البريد الإلكتروني مطلوب";
        String expectedPasswordMsg = "كلمة المرور مطلوبة";

        Assert.assertEquals(loginPage.getEmailErrorText(), expectedEmailMsg, "Email validation message is incorrect!");
        Assert.assertEquals(loginPage.getPasswordErrorText(), expectedPasswordMsg, "Password validation message is incorrect!");
    }
}
