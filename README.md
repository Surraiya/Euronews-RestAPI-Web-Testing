# Automated Testing Project - Euronews(Rest Api + Web)

## Overview
Main aim of the project was to create set of auto tests for Gmail API functionalities along with UI of Euronews service. It focuses mostly on proper usage of web elements, creation of rest api requests and maintaining correct project structure.

Running auto test is dependent on authorization token required to access Gmail API services. Token is generated and refreshed via Postman and manually put to credentials.json file under authorization/access_token value.

This Project was part of A1QA Automation testing internship.

...

## Project Structure
- **tests**: Contains the test scripts.
  - `BaseTest.java`: Base class for test setup.
  - `EuroNewsGmailApiTest.java`: Main test class implementing various subscription and unsubscription scenarios.
- **GmailApi**: Package containing classes related to Gmail API integration.
  - `Endpoints`: Classes for interacting with Gmail API endpoints.
  - `Model`: Data model classes, such as `MailSchema` for defining email schemas.
- **PageObjectModel**: Classes representing pages on the EuroNews website.
  - `Pages`: Package containing page classes.
  - `Forms`: Package containing form classes representing user interactions.

...

## Design Patterns and Best Practices
- **Singleton Pattern**: Implemented for managing browser instances to ensure one instance per test execution.
- **Page Object Model (POM) Design**: Pages are organized into a structured Page Object Model to improve code maintainability and reusability.
- **Best Practices for Locators**: Utilized best practices for identifying locators to enhance stability and reliability of tests.

## Test Scenario Steps
1. Open the EuroNews main page, agreeing to terms if required.
2. Navigate to the 'Newsletters' section.
3. Select a random newsletter subscription plan.
4. Enter an email address and submit the subscription form.
5. Confirm the subscription by following the link received in the confirmation email.
6. Verify successful subscription and return to the main site.
7. Revisit the 'Newsletters' section, preview the selected subscription plan, and retrieve the unsubscription link.
8. Navigate to the unsubscription page via the obtained link.
9. Enter the email and submit the unsubscription form.
10. Verify the unsubscription message and confirm that no email about cancellation is received.

The test steps and expected result of the Autotest is given here: [euronews.JPG](euronews.JPG)

## Form Interaction
- **Email Subscription Form:**
  - Located in the 'Newsletters' section.
  - Allows users to choose a subscription plan and submit their email address.

- **Unsubscription Form:**
  - Accessed through the obtained unsubscription link.
  - Requires users to enter their email address and submit the form.

- **Preview Form:**
  - Located in the 'Newsletters' section.
  - Utilizes iframes for certain elements.
  - Provides methods for interacting with elements within iframes.

## How to Run Tests
1. Ensure you have the necessary dependencies installed (TestNG, Selenium/Aquality Selenium, RestAssured).
2. Configure the test data in `testData.json`.
3. Execute the test suite using your preferred test runner or IDE.

...


## Technologies
Project is created with:
- Java 17
- Aquality Selenium 4.0.0
- Rest Assured 5.3.0
- TestNG 7.7.0
- Postman app

## Resources
- https://www.euronews.com/
- https://developers.google.com/identity/protocols/oauth2/web-server#httprest
- https://developers.google.com/gmail/api/guides
