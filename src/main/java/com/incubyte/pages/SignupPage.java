package com.incubyte.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model for Magento Signup Page with explicit waits.
 */
public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;  // Explicit Wait instance

    // Locators using label-based XPath
    @FindBy(xpath = "//label[@for='firstname']//span[contains(text(),'First Name')]/following::input[1]") private WebElement firstName;
    @FindBy(xpath = "//label[@for='lastname']//span[contains(text(),'Last Name')]/following::input[1]") private WebElement lastName;
    @FindBy(xpath = "//label[@for='email_address']//span[contains(text(),'Email')]/following::input[1]") private WebElement email;
    @FindBy(xpath = "//label[@for='password']//span[contains(text(),'Password')]/following::input[1]") private WebElement password;
    @FindBy(xpath = "//label[@for='password-confirmation']//span[contains(text(),'Confirm Password')]/following::input[1]") private WebElement confirmPassword;
    @FindBy(css = "button.action.submit.primary") private WebElement submitButton;
    @FindBy(xpath = "//div[@class='message-success success message']") private WebElement successMessage;

    /**
     * Constructor to initialize WebDriver, WebDriverWait, and PageFactory elements.
     */
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds for elements
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to fill the signup form with explicit waits.
     */
    public void fillSignupForm(String fName, String lName, String mail, String pass, String confirmPass) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lName);
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(mail);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
        wait.until(ExpectedConditions.visibilityOf(confirmPassword)).sendKeys(confirmPass);
    }

    /**
     * Method to click the submit button after waiting for it to be clickable.
     */
    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Method to retrieve the success message after signup.
     */
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }
}
