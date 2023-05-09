package PageObjectModel.Pages;

import PageObjectModel.Forms.AgreementForm;
import PageObjectModel.Forms.MainPageHeaderTopMenu;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private static final Logger logger = Logger.getInstance();

    public MainPage(){
        super(By.xpath("//body[@data-website='euronews']"),"EuroNews Page");
    }

    public AgreementForm getPopUpAgreementForm(){
        logger.info("A pop up Agreement Form is displayed");
        return new AgreementForm();
    }

    public MainPageHeaderTopMenu getHeaderTopMenu(){
        logger.info("Go to Top Header menu of the mainpage");
        return new MainPageHeaderTopMenu();
    }
}
