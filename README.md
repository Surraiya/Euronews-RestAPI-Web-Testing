# Euronews(Rest Api + Web)
## Overview

### Enabling Gmail Api:

After enabling Gmail Api for this project, created authorization credentials which include OAuth2.0 client id and client secret. Selected the application type as desktop, added gmail scopes (ReadOnly) and a testing email and redirect URIs as localhost. 
Added these values in credentials.json file.

### OAuth2.0 Access Token
First created authorization request then copy and pasted the request in the Chrome browser and authorized the testing email which responded a authorization code.
Lated used the authorization code along with other parameters where access_type is set to offline and made a request using Postman which returned both access_token and refresh_token.
Added these tokens in the tokens.json file.
Used refresh_token to renew access token in HttpRequests.java class.

### Euronews UI
Performed UI testing of the given euronews website using Aquality selenium. To Subscribe and Unsubscribe newsletter, An email is sent to the testing email used before.

## Gmail
By sending Https request and using Gmail Api, Got all the messages which include message_id from testing email. 
Then using the first message_id, got the latest message then got the message came from Euronews for subscription. 
Then extract the url link to complete the subscription. 
When unsubscribed newsletter, no new email was sent. The message id of latest euronews mail before subscription 
is equal to the latest message id after subscription, which means no new message has arrived.

## Technologies
Project is created with:
- Java 17
- Aquality Selenium 4.0.0
- Rest Assured 5.3.0
- TestNG 7.7.0
- Postman app

## Resources
https://www.euronews.com/
https://developers.google.com/identity/protocols/oauth2/web-server#httprest
https://developers.google.com/gmail/api/guides
