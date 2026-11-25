package com.incubyte.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class SignupSteps {

    private static final String BASE_URL = "https://magento.softwaretestingboard.com";

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        // Headless by default (needed in CI). Use -Dheadless=false to watch the browser.
        if (!"false".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/profile-" + UUID.randomUUID());
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------- Navigation ----------

    @Given("I open the Magento signup page")
    public void openMagentoSignupPage() {
        driver.get(BASE_URL + "/customer/account/create/");
    }

    @Given("I open the Magento login page")
    public void openMagentoLoginPage() {
        driver.get(BASE_URL + "/customer/account/login/");
    }

    // ---------- Signup steps ----------

    @When("I enter valid signup details")
    public void enterValidSignupDetails() {
        type("firstname", "Arrr");
        type("lastname", "K");
        type("email_address", "arrr" + System.currentTimeMillis() + "@example.com");
        type("password", "Test@12345");
        type("password-confirmation", "Test@12345");
    }

    @When("I enter valid first name, last name and email")
    public void enterValidFirstLastEmail() {
        type("firstname", "Arrr");
        type("lastname", "K");
        type("email_address", "test" + System.currentTimeMillis() + "@example.com");
    }

    @When("I enter valid first name, last name, password and confirm password")
    public void enterValidFieldsExceptEmail() {
        type("firstname", "Arrr");
        type("lastname", "K");
        type("password", "Test@12345");
        type("password-confirmation", "Test@12345");
    }

    @When("I enter an invalid email {string}")
    public void enterInvalidEmail(String email) {
        type("email_address", email);
    }

    @When("I leave the email field blank")
    public void leaveEmailFieldBlank() {
        driver.findElement(By.id("email_address")).clear();
    }

    @When("I enter password {string} and confirm password {string}")
    public void enterCustomPasswords(String password, String confirmPassword) {
        type("password", password);
        type("password-confirmation", confirmPassword);
    }

    @When("I submit the form")
    public void submitTheForm() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Create an Account']"))).click();
    }

    @Then("I should see a success message {string}")
    public void iShouldSeeASuccessMessage(String expectedMessage) {
        WebElement banner = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.message-success div")));
        Assertions.assertEquals(expectedMessage, banner.getText().trim());
    }

    @Then("I should see the message {string}")
    public void verifyValidationMessage(String expectedMessage) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mage-error")));
        Assertions.assertEquals(expectedMessage, error.getText().trim());
    }

    // ---------- Login steps ----------

    @When("I enter email {string} and password {string}")
    public void enterLoginCredentials(String email, String password) {
        type("email", email);
        type("pass", password);
    }

    @When("I click the Sign In button")
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("send2"))).click();
    }

    @Then("I should see the account dashboard with {string}")
    public void verifyAccountDashboard(String expectedHeading) {
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.page-title > span")));
        Assertions.assertEquals(expectedHeading, heading.getText().trim());
    }

    // ---------- Helper ----------

    private void type(String elementId, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId))).sendKeys(text);
    }
}
