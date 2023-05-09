package PageObjectModel.Pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UnsubscriptionPage extends Form {

    private final ITextBox email = getElementFactory().getTextBox(By.xpath("//input[@id='email']"),"Email");
    private final IButton submit = getElementFactory().getButton(By.xpath("//button[@type='submit']"),"Submit");
    private final ILabel unsubscriptionConfirmationText = getElementFactory().getLabel(By.xpath("//strong"),"Unsubscription Confirmation Text");

    public UnsubscriptionPage(){
        super(By.xpath("//div[@class='form-group']"), "Unscription Page");
    }

    public void setEmail(String emailData){
        email.clearAndType(emailData);
    }

    public void clickSubmit(){
        submit.clickAndWait();
    }

    public boolean getUnsubscriptionText(){
        unsubscriptionConfirmationText.state().waitForDisplayed();
        return unsubscriptionConfirmationText.state().isDisplayed();
    }
}
