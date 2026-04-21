package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class FooterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By facebookIcon = By.xpath("//a[contains(@href, 'facebook.com/EYouthLearning')]");

    public FooterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void scrollToFooter() {
        WebElement element = driver.findElement(facebookIcon);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void clickFacebookIcon() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(facebookIcon));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    private By linkedinIcon = By.xpath("//a[contains(@href, 'linkedin.com')]");

    public void clickLinkedinIcon() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(linkedinIcon));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private By youtubeIcon = By.xpath("//a[contains(@href, 'youtube.com')]");

    public void clickYouTubeIcon() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(youtubeIcon));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isYouTubeLinkDisplayed() {
        try {
            return driver.findElements(By.xpath("//a[contains(@href, 'youtube.com')]")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
