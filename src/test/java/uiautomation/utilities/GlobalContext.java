package uiautomation.utilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GlobalContext {
    public static IOSDriver<IOSElement> driver;
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static final int DEF_IMPLICIT_WAIT = 15;
    public static String accessKey = "<browserstack access key>";
    public static String userName = "browserstack user name";

    public static IOSDriver<IOSElement> getDriver() throws MalformedURLException {
        if (driver == null){

//            //Browserstack
//            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability("device", "iPhone 8 Plus");
//            caps.setCapability("app", "bs://8a5ae9aa5ebfe999c5fd257399d8c91f49130b04");
////            caps.setCapability("browserstack.uploadMedia", "media://4568f102329992a826d08b1a09d776405be6fd76");
//            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
//            driver = new IOSDriver<IOSElement>(new URL("http://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
//            driver.manage().timeouts().implicitlyWait(DEF_IMPLICIT_WAIT, TimeUnit.SECONDS);

            // Local
            final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
            final String APP_PATH = "<application path>";

            url = new URL(URL_STRING);
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5s");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.UDID, "1166ee28538570f4ee999dbaa24879a32747afe0");
            capabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability("wdaLaunchTimeout", 120000);
            capabilities.setCapability("wdaConnectionTimeout", 120000);

            driver = new IOSDriver(url, capabilities);
            driver.manage().timeouts().implicitlyWait(DEF_IMPLICIT_WAIT, TimeUnit.SECONDS);
        }

        return driver;
    }
}
