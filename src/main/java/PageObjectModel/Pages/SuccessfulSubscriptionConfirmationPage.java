package PageObjectModel.Pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SuccessfulSubscriptionConfirmationPage extends Form {

    private final IButton backToSite = getElementFactory().getButton(By.xpath("//a[contains(@class,'enw-btn__confirmation')]"), "Back to site");

    public SuccessfulSubscriptionConfirmationPage(){
        super(By.xpath("//main[@id='enw-main-content']"), "Successfully Subscription Confirmation Page");
    }

    public void clickBackToSite(){
        backToSite.click();
    }
}
