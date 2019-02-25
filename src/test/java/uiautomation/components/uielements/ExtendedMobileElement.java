package uiautomation.components.uielements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import uiautomation.utilities.GlobalContext;

import java.util.concurrent.TimeUnit;

import static uiautomation.utilities.GlobalContext.DEF_IMPLICIT_WAIT;

public class ExtendedMobileElement extends MobileElement {
    protected String selector;
    protected String using;
    protected IOSDriver<IOSElement> driver;
    protected MobileElement element = null;

    public MobileElement getElement(){
        if (element == null)
            find();
        return this.element;
    }

    protected void find(){
        this.element = driver.findElement(selector, using);
    }

    protected void reset(){
        this.element = null;
    }

    @Override
    public boolean isDisplayed(){
        return isDisplayed(false);
    }

    public boolean isDisplayed(boolean reset){
        if (reset)
            reset();
        if (element == null)
            find();
        return element.isDisplayed();
    }

    @Override
    public boolean isEnabled(){
        if (element == null)
            find();
        return element.isEnabled();
    }

    public boolean exist() {
        return exist(DEF_IMPLICIT_WAIT);
    }

    public boolean exist(int lookTimeout){
        driver.manage().timeouts().implicitlyWait(lookTimeout, TimeUnit.SECONDS);
        try{
            System.out.println(String.format("Trying to find element by '%s', using '%s' for '%s' seconds",
                    selector, using, lookTimeout));
            find();
            return true;
        } catch (Exception ex){
            System.out.println("Failed to find element");
            System.out.println(ex.getMessage());
        }finally {
            driver.manage().timeouts().implicitlyWait(DEF_IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
        return false;
    }


    public ExtendedMobileElement(MobileSelector selector, String using){
        this.selector = selector.toString();
        this.using = using;
        this.driver = GlobalContext.driver;
    }

    public ExtendedMobileElement(String selector, String using){
        this.selector = selector;
        this.using = using;
        this.driver = GlobalContext.driver;
    }

    public void click(){
        if (element == null)
            find();
        click(true);
    }

    public void click(boolean throwExceptionIfFailed){
        if (element == null)
            find();

        try{
            driver.findElement(selector, using).click();

        } catch (Exception ex){
            if (throwExceptionIfFailed)
                System.out.println(String.format("Failed to click on element found by '%s' using '%s'. Got exception '%s'", selector, using, ex.getMessage()));
        }
    }

    public String getTextValue(){
        if (element == null)
            find();

        try{
//            return driver.findElement(selector.toString(), using).getText();
            return element.getText();
        } catch (Exception ex){
            System.out.println(String.format("Failed to get text from element by '%s' using '%s'. Got exception '%s'", selector, using, ex.getMessage()));
            return "";
        }
    }
}
