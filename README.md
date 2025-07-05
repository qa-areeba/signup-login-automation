# Signup/Login Automation Assignment - Incubyte
![CI](https://github.com/qa-areeba/signup-login-automation/actions/workflows/ci.yml/badge.svg)

This project demonstrates automated testing of the signup and login flows on the Magento website using:
- **Java + Selenium**
- **Cucumber (BDD)**
- **Page Object Model (POM)**
- **JUnit 5**

## 📂 Project Structure

- `src/test/java` – Step definitions, page classes, and test logic
- `src/test/resources/features/` – Gherkin feature files
- `TestCases.xlsx` – Manual test cases
- `/proof` – Execution proof (e.g., `junit_success.png`)
- `README.md` – Project overview

## 🚀 How to Run

1. Open the project in IntelliJ
2. Ensure dependencies are installed
3. Run tests via:
   - `SignupPageTest.java` (JUnit + POM)
   - Or the Cucumber `.feature` file using the runner class

## ✅ Deliverables Included

- [x] Automation using BDD + POM
- [x] Dynamic email handling
- [x] Excel with test cases
- [x] Execution proof (screenshot)
- [x] Clear project structure

## 🧪 Sample Scenario

```gherkin
Scenario: Successful user signup
  Given the user is on the signup page
  When they enter valid account details
  And submit the form
  Then the account should be created successfully

Notes
Test runs generate unique email addresses to avoid duplication. Explicit waits are used for reliability. Proof of execution is attached.

Thank you for reviewing this submission!