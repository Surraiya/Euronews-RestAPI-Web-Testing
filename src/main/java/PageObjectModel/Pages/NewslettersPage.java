package PageObjectModel.Pages;

import PageObjectModel.Forms.NewslettersPageForm.EmailForm;
import PageObjectModel.Forms.NewslettersPageForm.NewslettersForm;
import PageObjectModel.Forms.NewslettersPageForm.CompleteSubscriptionForm;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewslettersPage extends Form {

    public NewslettersPage(){
        super(By.xpath("//div[@class='wp-site-blocks']"),"NewsLetters Page");
    }

    public NewslettersForm getNewsletterForm(){
        return new NewslettersForm();
    }

    public EmailForm getEmailForm(){
        return new EmailForm();
    }

    public CompleteSubscriptionForm getCompleteSubscriptionForm(){
        return new CompleteSubscriptionForm();
    }
}
