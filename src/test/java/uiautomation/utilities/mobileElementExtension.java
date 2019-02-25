package uiautomation.utilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.util.concurrent.TimeUnit;

public class mobileElementExtension {

    public static MobileElement waitForPresenceByAccesibilityId(IOSDriver driver, int timeLimitInSeconds, String targetAccessibilityId) throws Exception {
        return waitForPresenceByAccesibilityId(driver, timeLimitInSeconds, targetAccessibilityId, false);
    }

    public static MobileElement waitForPresenceByAccesibilityId(IOSDriver driver, int timeLimitInSeconds, String targetAccessibilityId, boolean failIfNotFound) throws Exception {
        MobileElement mobileElement = null;
        Exception ex = new Exception();
        try{
            driver.manage().timeouts().implicitlyWait(timeLimitInSeconds, TimeUnit.SECONDS);
            mobileElement = (MobileElement) driver.findElementByAccessibilityId(targetAccessibilityId);
        }catch(Exception e){
            ex = e;
            System.out.println(e.getMessage());
        } finally {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        if (mobileElement == null && failIfNotFound){
            throw ex;
        }

        return mobileElement;

    }

}
