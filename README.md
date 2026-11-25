# Selenium BDD Automation: Signup and Login (Java)

![CI](https://github.com/qa-areeba/signup-login-automation/actions/workflows/ci.yml/badge.svg)

UI test automation for the signup and login flows of a Magento demo store ([magento.softwaretestingboard.com](https://magento.softwaretestingboard.com)), built with Java, Selenium WebDriver, Cucumber (BDD), JUnit 5 and Maven. Originally written as an automation assignment for Incubyte.

## What is tested

Six Cucumber scenarios in `src/test/resources/features/signup.feature`:

| # | Scenario | Type |
| --- | --- | --- |
| 1 | Successful account signup | Positive |
| 2 | Signup with a missing required field (email) | Negative |
| 3 | Signup with a weak password | Negative |
| 4 | Signup with mismatched password and confirm password | Negative |
| 5 | Signup with an invalid email format | Negative |
| 6 | Sign in with an existing account | Positive |

Plus one JUnit 5 test (`SignupPageTest`) that drives the signup form through a Page Object (`SignupPage`) using explicit waits.

The manual test cases (TC001 to TC006) are in `TestCases.xlsx`, and execution screenshots are in `proof/`.

## Tech stack

- Java 21, Maven
- Selenium WebDriver 4 (Selenium Manager downloads the matching ChromeDriver)
- Cucumber 7 with the JUnit Platform engine, JUnit 5
- Page Object Model (`SignupPage`), explicit waits
- GitHub Actions (CI)

## Project structure

```text
.
├── .github/workflows/ci.yml                  # CI: runs mvn clean test
├── src/main/java/com/incubyte/pages/         # Page Object (SignupPage)
├── src/test/java/com/incubyte/steps/         # Cucumber step definitions and runner
├── src/test/java/com/incubyte/tests/         # JUnit 5 test using the Page Object
├── src/test/resources/features/              # Gherkin feature files
├── TestCases.xlsx                            # Manual test cases
├── proof/                                    # Execution screenshots
└── pom.xml
```

## How to run

Prerequisites: JDK 21, Maven 3.9+ and Google Chrome.

```bash
git clone https://github.com/qa-areeba/signup-login-automation.git
cd signup-login-automation
mvn clean test
```

Tests run headless by default. To watch the browser, run:

```bash
mvn clean test -Dheadless=false
```

Reports are written to `target/surefire-reports/`.

## Continuous integration

The workflow `.github/workflows/ci.yml` runs `mvn clean test` on every push and pull request to `master` and uploads the Surefire reports as the `surefire-reports` artifact.

## Notes

- Each signup run uses a unique e-mail (`...<timestamp>@example.com`) so the test can be repeated.
- The sign-in scenario uses a demo account on a public practice site. If that account is removed, update the credentials in `signup.feature`.

## Author

Areeba Zakir, QA Automation Engineer
[LinkedIn](https://www.linkedin.com/in/areeba-zakir-38364328b) · [GitHub](https://github.com/qa-areeba)
