package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By joinUsSection = By.xpath("//section[contains(., 'شارك خبرتك')]");
    private By joinUsNowButton = By.xpath("//a[contains(text(), 'أنضم لنا الان')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    public void navigateToRegistrationFromHome() {
        WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(joinUsSection));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", section);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(joinUsNowButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }


        private By newAccountBtn = By.xpath("//a[contains(@href, '/auth/register') and contains(text(), 'حساب جديد')]");
        private By emailField = By.id("email");
        private By phoneField = By.cssSelector("input.PhoneInputInput");
        private By submitButton = By.cssSelector("button[type='submit']");
        private By usernameError = By.xpath("//p[contains(@class, 'text-[red]') and contains(text(), 'الاسم مطلوب')]");

        public void navigateToRegisterPage() {
            wait.until(ExpectedConditions.elementToBeClickable(newAccountBtn)).click();
        }

        public void fillFormExceptUsername(String email, String phone) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);

            driver.findElement(phoneField).sendKeys(phone);

        }

        public void clickCreateAccount() {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        public String getValidationErrorMessage() {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameError));
            return error.getText().trim();
        }

}