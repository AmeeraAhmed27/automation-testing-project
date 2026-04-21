package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartHeader = By.xpath("//h1[contains(text(), 'عربة التسوق')]");
    private By courseTitleInCart = By.xpath("//ul//a[contains(@class, 'text-lg font-semibold')]");
    private By courseCountText = By.xpath("//p[contains(text(), 'دورة تدريبية')]");
    private By totalAmount = By.xpath("//span[contains(text(), 'الاجمالى')]/following-sibling::span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getFirstCourseTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitleInCart)).getText().trim();
    }

    public String getCartCourseCount() {
        return driver.findElement(courseCountText).getText().trim();
    }

    public String getTotalPrice() {
        return driver.findElement(totalAmount).getText().trim();
    }

    public boolean isCartPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeader)).isDisplayed();
    }
}