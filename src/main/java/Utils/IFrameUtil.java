package Utils;

import org.openqa.selenium.WebElement;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class IFrameUtil {

    public static void switchToFrame(WebElement iframeElement) {
        getBrowser().getDriver().switchTo().frame(iframeElement);
    }

    public static void switchToDefaultContent(){
        getBrowser().getDriver().switchTo().defaultContent();
    }
}
