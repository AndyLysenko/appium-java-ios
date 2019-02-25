package uiautomation.components.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileSelector;
import io.qameta.allure.Step;
import uiautomation.components.pages.Transactions.CorporateCardPage;
import uiautomation.components.uielements.Button;
import uiautomation.utilities.mobileElementExtension;

public class LoginPage extends PageObject {
    public LoginPage(){
        super();
    }

    private Button fake = new Button(MobileSelector.ACCESSIBILITY, "Fake");

    @Step("Log in as '{userName}'")
    public CorporateCardPage loginAs(String userName) throws Exception{
        fake.click();
        Button fakeUser = new Button(MobileSelector.ACCESSIBILITY, userName);
        fakeUser.click();

        //TODO wait until not displayed

//        skip intro for the first time
        MobileElement skipIntro = mobileElementExtension.waitForPresenceByAccesibilityId(driver, 15, "Skip");
        if (skipIntro != null){
            skipIntro.click();
        }

        MobileElement settings = mobileElementExtension.waitForPresenceByAccesibilityId(driver, 30, "Settings", true);
//        settings.click();

        return new CorporateCardPage();
    }
}
