package uiautomation.components.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import uiautomation.utilities.GlobalContext;
import uiautomation.components.uielements.ExtendedMobileElement;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static uiautomation.utilities.GlobalContext.DEF_IMPLICIT_WAIT;


public class PageObject {
    protected IOSDriver<IOSElement> driver;
    int screenWidth;
    int screenHeight;

    public PageObject(){
        this.driver = GlobalContext.driver;
        screenWidth = driver.manage().window().getSize().width;
        screenHeight = driver.manage().window().getSize().height;
        System.out.println(String.format("Screen width= %d", screenWidth));
        System.out.println(String.format("Screen height= %d", screenHeight));
    }

    public void swipeUp(){
        swipeScreenPercent(0.1, 0.9, 0.1, 0.1, 500);
    }

    public void swipeLeft(){
        swipeScreenPercent(0.9, 0.1, 0.1, 0.1, 1000);
    }

    public void swipeScreenPercent(double xStatPercent, double yStartPercent, double xEndPercent, double yEndPercent, int paceMillis){
        swipe((int) (xStatPercent * screenWidth), (int) (yStartPercent * screenHeight), (int) (xEndPercent * screenWidth), (int) (yEndPercent * screenHeight), paceMillis);
    }

    public void swipe(int xStat, int yStart, int xEnd, int yEnd, int paceMillis){
        try{
            new TouchAction(driver)
                    .press(PointOption.point(xStat, yStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(paceMillis)))
                    .moveTo((PointOption.point(xEnd, yEnd)))
                    .release()
                    .perform();
        } catch (Exception ex){
            System.out.println(String.format("Failed to swipe from [%d,%d] to [%d,%d] with pace %d millisecons", xStat, yStart, xEnd, yEnd, paceMillis));
        }
    }

    protected <T extends ExtendedMobileElement> T swipeUpUntilDisplayed(T element, int maxSwipes){
        int i = 0;
        while (!element.isDisplayed() && i <= maxSwipes){
           swipeUp();
           i++;
        }
        return element;
    }

    public void addScreenshotToAllure(String name) throws Exception{
        TimeUnit.SECONDS.sleep(1);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("<screenshot full file name>"));
//
        Path content = Paths.get("<screenshot full file name>");
        try {
            InputStream is = Files.newInputStream(content);
            Allure.addAttachment(name, is);}
        catch (Exception ex){}

    }

    public void acceptAlert(){
        handleAlert(true, 3);
    }

    public void dismissAlert(){
        handleAlert(false, 3);
    }

    public void handleAlert(boolean accept, int waitTimeout){
        try{
            driver.manage().timeouts().implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            TimeUnit.MILLISECONDS.sleep(500);

            Alert alert = driver.switchTo().alert();
            System.out.println(String.format("Found alert '%s'", alert.getText()));
            if (accept){
                System.out.println("Trying to accept");
                alert.accept();
            }
            else{
                System.out.println("Trying to dismiss");
                alert.dismiss();
            }
        } catch (Exception ex){
            System.out.println("Failed to handle alert");
            System.out.println(ex.getMessage());
        }finally {
            driver.manage().timeouts().implicitlyWait(DEF_IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
    }

}
