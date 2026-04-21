package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CoursesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By allCoursesLink = By.xpath("//a[contains(@href, '/ar/courses') and contains(text(), 'الدورات')]");
    private By firstCourseCard = By.cssSelector("a[href*='/ar/courses/']");
    private By aboutSection = By.xpath("//*[contains(text(), 'التدريبية الدورة حول')]");

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAllCourses() {
        try {
            WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(allCoursesLink));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        } catch (Exception e) {
            driver.navigate().to("https://eyouthlearning.com/ar/courses");
        }
    }

    public void clickFirstCourseCard() {
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(firstCourseCard));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
        card.click();
    }

    public boolean isAboutSectionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(aboutSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private By firstSubscribeButton = By.xpath("(//button[contains(text(), 'اشترك الآن')])[3]");

    public void subscribeToFirstCourse() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(firstSubscribeButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    private By CourseCard = By.xpath("//div[contains(@class, 'border-light-blue') and contains(@class, 'flex-col')]");

    @Step("Performing individual UI component assertions for the first course card")
    public void verifyFirstCardStructure() {
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(CourseCard));

        Assert.assertFalse(card.findElements(By.xpath(".//img")).isEmpty(),
                "UI Error: Course Image (<img>) is missing!");

        Assert.assertFalse(card.findElements(By.xpath(".//h3")).isEmpty(),
                "UI Error: Course Title (<h3>) is missing!");

        Assert.assertFalse(card.findElements(By.xpath(".//h6[contains(text(), 'مع')]")).isEmpty(),
                "UI Error: Instructor label (<h6>) containing 'مع' was not found!");

        Assert.assertFalse(card.findElements(By.xpath(".//button[contains(text(), 'اشترك الآن')]")).isEmpty(),
                "UI Error: 'Subscribe' button is missing or text has changed!");
    }
}