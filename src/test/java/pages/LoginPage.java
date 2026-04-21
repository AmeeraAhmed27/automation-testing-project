package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginLink = By.xpath("//a[contains(@href, '/auth/login') and contains(text(), 'تسجيل الدخول')]");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    private By invalidCredentialsError = By.xpath("//p[contains(@class, 'text-[red]') and contains(text(), 'لم يتم العثور')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);

        WebElement btn = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsError));
        return error.getText().trim();
    }

    private By emailError = By.xpath("//input[@id='email']/../../p[contains(@class, 'text-[red]')]");
    private By passwordError = By.xpath("//input[@id='password']/../../p[contains(@class, 'text-[red]')]");

    public String getEmailErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText().trim();
    }

    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText().trim();
    }

}
