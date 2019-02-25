package uiautomation.components.uielements;

import io.appium.java_client.MobileSelector;

public class Image extends ExtendedMobileElement {
    public Image(MobileSelector selector, String using){
        super(selector, using);
    }

    public Image(String selector, String using) {
        super(selector, using);
    }
}
