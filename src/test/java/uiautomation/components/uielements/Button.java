package uiautomation.components.uielements;

import io.appium.java_client.MobileSelector;

public class Button extends ExtendedMobileElement {
    public Button(MobileSelector selector, String using){
        super(selector, using);
    }

    public Button(String selector, String using) {
        super(selector, using);
    }
}
