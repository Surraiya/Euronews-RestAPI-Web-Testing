package PageObjectModel.Forms.NewslettersPageForm;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CompleteSubscriptionForm extends Form {
    public CompleteSubscriptionForm() {
        super(By.xpath("//div[@id='additional-data-modal']"), "Complete Subscription Form");
    }
}
