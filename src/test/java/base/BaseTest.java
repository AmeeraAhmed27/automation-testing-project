package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.CoursesPage;
import pages.LoginPage;
import pages.CartPage;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.io.File;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected CoursesPage coursesPage;
    protected LoginPage loginPage;
    protected CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        // 1. Setup Chrome Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Critical for GitHub deployment
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        // 2. Initialize WebDriver (ONLY ONCE)
        // Note: If you use WebDriverManager or have the driver in your PATH,
        // you don't need the System.setProperty line.
        driver = new ChromeDriver(options);

        // 3. Initialize Page Objects
        coursesPage = new CoursesPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);

        // 4. Configure Browser & Timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 100 is too long; 10 is standard
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 5. Navigate to URL
        driver.get("https://eyouthlearning.com/");
    }
    @AfterSuite
    public void generateAndOpenAllureReport() {
        try {
            String projectDir = System.getProperty("user.dir");
            System.out.println("Starting Allure Report Generation...");

            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "allure generate target/allure-results --clean -o target/allure-report"
            );
            builder.directory(new File(projectDir));
            builder.inheritIO();
            Process process = builder.start();
            process.waitFor();

            // Open the report automatically in your default browser
//            File reportFile = new File(projectDir + "/target/allure-report/index.html");
//            if (reportFile.exists()) {
//                System.out.println("Opening report: " + reportFile.getAbsolutePath());
//                Desktop.getDesktop().browse(reportFile.toURI());
//            } else {
//                System.err.println("Allure report was not generated. Check if 'allure-results' exists.");
//            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to generate Allure report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
