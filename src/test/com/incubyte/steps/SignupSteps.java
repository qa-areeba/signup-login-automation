package com.incubyte.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupSteps {

    WebDriver driver;

    @Given("I open the Magento signup page")
    public void openMagentoSignupPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
    }

    @Given("I open the Magento login page")
    public void openMagentoLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
    }

    @When("I enter valid signup details")
    public void enterValidSignupDetails() {
        driver.findElement(By.id("firstname")).sendKeys("Arrr");
        driver.findElement(By.id("lastname")).sendKeys("K");
        driver.findElement(By.id("email_address")).sendKeys("arrr" + System.currentTimeMillis() + "@example.com");
        driver.findElement(By.id("password")).sendKeys("Test@12345");
        driver.findElement(By.id("password-confirmation")).sendKeys("Test@12345");
    }

    @When("I submit the form")
    public void submitTheForm() {
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();
    }

    @Then("I should see a success message {string}")
    public void iShouldSeeASuccessMessage(String expectedMessage) {
        WebElement successBanner = driver.findElement(By.cssSelector("div.message-success div"));
        String actualMessage = successBanner.getText().trim();
        System.out.println("Success message displayed: " + actualMessage);
        Assertions.assertEquals(expectedMessage, actualMessage);
        driver.quit();
    }

    // 🔹 Shared reusable steps for negative scenarios

    @When("I enter valid first name, last name and email")
    public void enterValidFirstLastEmail() {
        driver.findElement(By.id("firstname")).sendKeys("Arrr");
        driver.findElement(By.id("lastname")).sendKeys("K");
        driver.findElement(By.id("email_address")).sendKeys("test" + System.currentTimeMillis() + "@example.com");
    }

    @When("I enter valid first name, last name, password and confirm password")
    public void enterValidFieldsExceptEmail() {
        driver.findElement(By.id("firstname")).sendKeys("Arrr");
        driver.findElement(By.id("lastname")).sendKeys("K");
        driver.findElement(By.id("password")).sendKeys("Test@12345");
        driver.findElement(By.id("password-confirmation")).sendKeys("Test@12345");
    }

    @When("I enter an invalid email {string}")
    public void enterInvalidEmail(String email) {
        driver.findElement(By.id("email_address")).sendKeys(email);
    }

    @When("I leave the email field blank")
    public void leaveEmailFieldBlank() {
        driver.findElement(By.id("email_address")).clear();
    }

    @When("I enter password {string} and confirm password {string}")
    public void enterCustomPasswords(String password, String confirmPassword) {
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(confirmPassword);
    }

    @Then("I should see the message {string}")
    public void verifyValidationMessage(String expectedMessage) {
        WebElement error = driver.findElement(By.cssSelector("div.mage-error"));
        String actualMessage = error.getText().trim();
        Assertions.assertEquals(expectedMessage, actualMessage);
        driver.quit();
    }

    // 🔹 Login flow steps for existing account

    @When("I enter email {string} and password {string}")
    public void enterLoginCredentials(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("I click the Sign In button")
    public void clickSignInButton() {
        driver.findElement(By.id("send2")).click();
    }

    @Then("I should see the account dashboard with {string}")
    public void verifyAccountDashboard(String expectedHeading) {
        WebElement heading = driver.findElement(By.cssSelector("h1.page-title > span"));
        String actualHeading = heading.getText().trim();
        Assertions.assertEquals(expectedHeading, actualHeading);
    }


}
