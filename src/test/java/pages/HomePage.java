package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInput = By.cssSelector("input[type='search'][placeholder*='بحث']");

    private By courseSearchResult = By.cssSelector("a[href*='/ar/courses/how-to-join-a-bank']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchForKeyword(String keyword) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        element.clear();
        element.sendKeys(keyword);
    }

    public void clickCourseSearchResult() {
        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(courseSearchResult));
        result.click();
    }

    private By allCoursesLink = By.xpath("//a[@href='/ar/courses']");

    public void clickAllCourses() {
        wait.until(ExpectedConditions.elementToBeClickable(allCoursesLink)).click();
    }
}