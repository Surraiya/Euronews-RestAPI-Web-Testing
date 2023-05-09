package PageObjectModel.Forms.NewslettersPageForm;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Utils.IFrameUtil.switchToFrame;

public class PreviewForm extends Form {

    private final IElement preview = getElementFactory().getLabel(By.xpath("//iframe[@class='iframe-preview']"),"Preview");
    private final IButton unsubscribe = getElementFactory().getButton(By.xpath("//a[contains(@href, 'unsubscribe')]"),"Unsubscribe");
    private static final String ATTRIBUTE = "href";

    public PreviewForm(){
        super(By.xpath("//div[contains(@class,'jquery-modal')]"),"Preview Form");
    }

    public String getUnsubscriptionLink(){
        WebElement iframeElement = preview.getElement();
        switchToFrame(iframeElement);
        unsubscribe.getJsActions().scrollIntoView();
        return unsubscribe.getAttribute(ATTRIBUTE);
    }
}
