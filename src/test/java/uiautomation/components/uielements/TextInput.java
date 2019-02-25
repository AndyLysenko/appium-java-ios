package uiautomation.components.uielements;

import io.appium.java_client.MobileSelector;
import io.appium.java_client.ios.IOSDriver;

public class TextInput extends ExtendedMobileElement {
    public TextInput(MobileSelector selector, String using){
        super(selector, using);
    }

    public void setTextValue(String value){
        setTextValue(value, true);
    }

    public void setTextValue(String value, boolean throwExceptionIfFailed){
        if (element == null)
            find();

        try{
            element.sendKeys(value);
        } catch (Exception ex){
            if (throwExceptionIfFailed)
                System.out.println(String.format("Failed to Set value to element by '%s' using '%s'. Got exception '%s'", selector, using, ex.getMessage()));
        }
    }
}
