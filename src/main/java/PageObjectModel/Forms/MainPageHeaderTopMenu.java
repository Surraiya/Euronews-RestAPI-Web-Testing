package PageObjectModel.Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPageHeaderTopMenu extends Form {

    private final IButton newsLetterMenu = getElementFactory().getButton(By.xpath("//span[@data-event='newsletter-link-header']"),"Newsletters Menu");

    public MainPageHeaderTopMenu(){
        super(By.xpath("//div[@class='o-site-header__top']"), "Mainpage Header Top");
    }

    public void clickNewsLetterMenu(){
        newsLetterMenu.click();
    }
}
