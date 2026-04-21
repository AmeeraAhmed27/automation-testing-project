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
        // Path to the driver you just placed in your project
        String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Helps bypass some network-related startup lags
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        coursesPage = new CoursesPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        wait = new WebDriverWait(driver, Duration.ofSeconds(100));

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
