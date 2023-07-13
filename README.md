# Euronews(Rest Api + Web)
## Overview
Main aim of the project was to create set of auto tests for Gmail API functionalities along with UI of Euronews service. It focuses mostly on proper usage of web elements, creation of rest api requests and maintaining correct project structure.

Running auto test is dependent on authorization token required to access Gmail API services. Token is generated and refreshed via Postman and manually put to credentials.json file under authorization/access_token value.

This Project was part of A1QA Automation testing internship.

## TestCase
The test steps and expected result of the Autotest is given here: [euronews.JPG](euronews.jpg)

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
