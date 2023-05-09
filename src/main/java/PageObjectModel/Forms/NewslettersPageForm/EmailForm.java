package PageObjectModel.Forms.NewslettersPageForm;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EmailForm extends Form {

    private final ITextBox email = getElementFactory().getTextBox(By.xpath("//input[@type='email']"),"Email");
    private final IButton submit = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"),"Submit");

    public EmailForm(){
        super(By.xpath("//form[@id='register-newsletters-form']"), "Email Form");
    }

    public void enterEmailAndSubmit(String emailData){
        email.type(emailData);
        submit.clickAndWait();
    }
}
