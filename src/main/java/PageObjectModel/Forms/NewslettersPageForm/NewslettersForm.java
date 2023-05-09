package PageObjectModel.Forms.NewslettersPageForm;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class NewslettersForm extends Form {

    private static final Logger logger = Logger.getInstance();
    private final ITextBox newsletterSubscriptionPlanContainer = getElementFactory().getTextBox(By.xpath("//form[@id='newsletters-form']"), "Newsletter Subscription Plans Container");
    private final List<TextBox> subscriptionPlans = newsletterSubscriptionPlanContainer.findChildElements(By.xpath("//div[contains(@class,'bg-white')]"), ElementType.TEXTBOX);
    private final String planLocator = "//label[contains(@class,'block') and contains(@class,'w-full')]";
    private final String priviewLocator = "//a[contains(@class,'text-primary') and contains(@class,'inline-block')]";

    public NewslettersForm(){
        super(By.xpath("//form[@id='newsletters-form']"),"News letter Container Form");
    }

    public int getRandomSubscriptionPlanNumber(){
        Random random = new Random();
        return random.nextInt(subscriptionPlans.size()-1);
    }

    public ITextBox chooseRandomSubscriptionPlan(){
        int subscriptionPlanNumber = getRandomSubscriptionPlanNumber();
        ITextBox chosenSubscriptionPlan = subscriptionPlans.get(subscriptionPlanNumber);
        logger.info(String.format("Randomly chosen Subscription Plan to subscribe is: %s", chosenSubscriptionPlan.getValue()));
        return chosenSubscriptionPlan;
    }

    public void clickSubscriptionPlan(ITextBox subscriptionPlan){
        ICheckBox plan = subscriptionPlan.findChildElement(By.xpath(String.format("%s",planLocator)), ElementType.CHECKBOX);
        plan.clickAndWait();
    }

    public void clickSeePreviewOfPreviouslySelectedSubscriptionPlan(ITextBox subscriptionPlan){
        logger.info(String.format("Previously Chosen Subscription plan to see preview is: %s", subscriptionPlan.getValue()));
        subscriptionPlan.getJsActions().scrollIntoView();
        IButton seePreviewButton = subscriptionPlan.findChildElement(By.xpath(String.format("%s",priviewLocator)), ElementType.BUTTON);
        seePreviewButton.clickAndWait();
    }

    public PreviewForm getPreviewForm(){
        return new PreviewForm();
    }
}