package uiautomation.components.uielements;

import io.appium.java_client.MobileSelector;

public class TextButton extends TextInput {
    public TextButton(MobileSelector selector, String using){
        super(selector, using);
    }

    @Override
    public void setTextValue(String value){
        setTextValue(value, true);
    }

    @Override
    public void setTextValue(String value, boolean throwExceptionIfFailed){
        if (element == null)
            find();

        try{
            element.click();
            driver.findElementByAccessibilityId(value).click();
        } catch (Exception ex){
            if (throwExceptionIfFailed)
                System.out.println(String.format("Failed to Set value to element by '%s' using '%s'. Got exception '%s'", selector, using, ex.getMessage()));
        }
    }

}
