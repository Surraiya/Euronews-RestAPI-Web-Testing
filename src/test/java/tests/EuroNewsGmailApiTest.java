package tests;

import GmailApi.Endpoints.MessageEndpoint;
import GmailApi.Model.MailSchema.Message;
import PageObjectModel.Pages.MainPage;
import PageObjectModel.Pages.NewslettersPage;
import PageObjectModel.Pages.SuccessfulSubscriptionConfirmationPage;
import PageObjectModel.Pages.UnsubscriptionPage;
import aquality.selenium.elements.interfaces.ITextBox;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.JsonFileReader.getStringValue;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public class EuroNewsGmailApiTest extends BaseTest{

    @Test
    public void testGmailApiWeb(){

        logger.info("1. Follow the Euronews Page link");
        MainPage mainPage = new MainPage();
        mainPage.getPopUpAgreementForm().clickContinueWithoutAgreement();
        Assert.assertTrue(mainPage.state().isDisplayed(),"Main Page should be open");


        logger.info("2. Follow the link 'Newsletters' in the header");
        mainPage.getHeaderTopMenu().clickNewsLetterMenu();
        NewslettersPage newslettersPage= new NewslettersPage();
        Assert.assertTrue(newslettersPage.state().isDisplayed(),"Newsletter Page should be open");


        logger.info("3. Choose a random newsletter subscription plan");
        ITextBox subscriptionPlan = newslettersPage.getNewsletterForm().chooseRandomSubscriptionPlan();
        newslettersPage.getNewsletterForm().clickSubscriptionPlan(subscriptionPlan);
        Assert.assertTrue(newslettersPage.getEmailForm().state().isDisplayed(),"An email form should appear");


        logger.info("4. Enter email, click 'Submit' button");
        newslettersPage.getEmailForm().enterEmailAndSubmit(getStringValue("testData","email"));
        newslettersPage.getCompleteSubscriptionForm().state().waitForDisplayed();
        Assert.assertTrue(newslettersPage.getCompleteSubscriptionForm().state().isDisplayed(),"Complete Subscription Form should be displayed");

        Message euronewsMail = MessageEndpoint.getLatestEuronewsMessageById();
        String euronewsLatestMsgIdBeforeUnsubscription = MessageEndpoint.getLatestEuronewsMessageId();
        Assert.assertTrue(MessageEndpoint.isConfirmSubscriptionEuronewsMail(euronewsMail),"An EMail from euronews with a request to confirm subscription should be received");
        String redirectURL = MessageEndpoint.extractLinkFromMail(euronewsMail);


        logger.info("5. Folow the link received from the letter");
        getBrowser().goTo(redirectURL);
        SuccessfulSubscriptionConfirmationPage successfulSubscriptionConfirmationPage = new SuccessfulSubscriptionConfirmationPage();
        Assert.assertTrue(successfulSubscriptionConfirmationPage.state().isDisplayed(),"Successful Subscription Page should be displayed.");


        logger.info("6. Click 'Back to the site'");
        successfulSubscriptionConfirmationPage.clickBackToSite();
        mainPage.state().waitForDisplayed();
        Assert.assertTrue(mainPage.state().isDisplayed(),"Main Page should Open");


        logger.info("7. Follow the link 'Newsletters' in the header, choose the same newsletter subscription plan as in the step 3, click 'See preview'");
        mainPage.getHeaderTopMenu().clickNewsLetterMenu();
        newslettersPage.state().waitForDisplayed();
        newslettersPage.getNewsletterForm().clickSeePreviewOfPreviouslySelectedSubscriptionPlan(subscriptionPlan);
        Assert.assertTrue(newslettersPage.getNewsletterForm().getPreviewForm().state().isDisplayed(),"Preview Form should be displayed");


        logger.info("8. On preview find and get a link to unsubscribe from the mailing list , follow this link in the browser");
        String unsubcribeLink = newslettersPage.getNewsletterForm().getPreviewForm().getUnsubscriptionLink();
        getBrowser().goTo(unsubcribeLink);
        UnsubscriptionPage unsubscriptionPage = new UnsubscriptionPage();
        Assert.assertTrue(unsubscriptionPage.state().isDisplayed(),"Unsubcription page should be open");


        logger.info("9. Enter email, click 'Submit' button");
        unsubscriptionPage.setEmail(getStringValue("testData","email"));
        unsubscriptionPage.clickSubmit();
        Assert.assertTrue(unsubscriptionPage.getUnsubscriptionText(),"A message that the subscription was canceled should appear");


        logger.info("10. Make sure that you haven't received an email with a message about canceling your subscription");
        String euronewsLatestMsgIdAfterUnubscription = MessageEndpoint.getLatestEuronewsMessageId();
        Assert.assertEquals(euronewsLatestMsgIdBeforeUnsubscription, euronewsLatestMsgIdAfterUnubscription,"An email with a message about canceling subscription should not arrived");
    }
}
