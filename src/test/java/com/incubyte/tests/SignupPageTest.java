package com.incubyte.tests;

import com.incubyte.pages.SignupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.UUID;

/**
 * Test class for verifying Magento Signup functionality with explicit waits.
 */
public class SignupPageTest {
    WebDriver driver;
    SignupPage signupPage;
    WebDriverWait wait; // Explicit wait instance

    /**
     * Setup method to initialize WebDriver and navigate to signup page.
     */
    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=/tmp/profile-" + UUID.randomUUID());

        driver = new ChromeDriver(options);  // Launch Chrome with isolated profile
        driver.manage().window().maximize(); // Open browser in full-screen mode
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/"); // Open Magento signup page

        signupPage = new SignupPage(driver); // Initialize SignupPage object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds for elements
    }

    /**
     * Test case to verify successful signup submission.
     */
    @Test
    public void testSignupFormSubmission() {
        // Generate a unique email on each run
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";

        // Fill form using dynamic email
        signupPage.fillSignupForm("RR", "Test", uniqueEmail, "Password123", "Password123");
        signupPage.submitForm(); // Submit the form

        // Explicit wait for success message
        String successMessage = signupPage.getSuccessMessage();
        Assertions.assertEquals("Thank you for registering with Main Website Store.", successMessage,
                "Signup was unsuccessful - Success message does not match.");

        // Explicit wait to ensure "create" is removed from the URL
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("create")));
        Assertions.assertFalse(driver.getCurrentUrl().contains("create"),
                "Signup was unsuccessful - URL still contains 'create'.");
    }

    /**
     * Cleanup method to close the browser after test execution.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();  // Close browser and end session
    }
}
