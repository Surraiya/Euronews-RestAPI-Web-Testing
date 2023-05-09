package tests;

import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static Utils.JsonFileReader.getStringValue;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public abstract class BaseTest {

    public static final Logger logger = Logger.getInstance();

    @BeforeMethod
    public void initializeBrowser(){
        getBrowser().goTo(getStringValue("configData","EuroNewsUrl"));
        getBrowser().waitForPageToLoad();
        getBrowser().maximize();
    }

    @AfterMethod
    public void closeBrowser(){
        getBrowser().quit();
    }
}
