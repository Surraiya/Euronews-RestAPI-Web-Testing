package PageObjectModel.Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AgreementForm extends Form {

    private final IButton continueWithoutAgreement = getElementFactory().getButton(By.xpath("//span[@class='didomi-continue-without-agreeing']"),"Continue without agreement");

    public AgreementForm(){
        super(By.xpath("//div[@class='didomi-popup-view']"),"Pop up Agreement");
    }

    public void clickContinueWithoutAgreement(){
        continueWithoutAgreement.click();
    }
}
