package uiautomation.components.pages;

import io.appium.java_client.MobileSelector;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import uiautomation.components.pages.Transactions.OtherExpensesPage;
import uiautomation.components.uielements.Button;
import uiautomation.components.uielements.Image;
import uiautomation.components.uielements.TextButton;
import uiautomation.components.uielements.TextInput;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExpenseDetailsPage extends PageObject {
    public ExpenseDetailsPage(){
        super();
    }

    private TextInput commentTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "comment-textinput");
    private TextInput merchantTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "merchant-textinput");
    private TextInput placeTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "place-textinput");
    private TextButton currencyTextButton = new TextButton(MobileSelector.ACCESSIBILITY, "currency-button");
    private TextInput amountTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "amount-textinput");
    private TextButton vatCountryTextButton = new TextButton(MobileSelector.ACCESSIBILITY, "vat-button");
    private TextButton vatRateTextButton = new TextButton(MobileSelector.ACCESSIBILITY, "vat-rate-button");
    private TextButton dateTextButton = new TextButton(MobileSelector.ACCESSIBILITY, "date-button");
    private TextButton categoryTextButton = new TextButton(MobileSelector.ACCESSIBILITY, "category-button");
    private TextInput clientTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "client-textinput");
    private Button saveButton = new Button("name", "Save");
    private Button deleteButton = new Button(MobileSelector.ACCESSIBILITY, "delete-button");
    private Button closeButton = new Button(MobileSelector.ACCESSIBILITY, "close-button");

    private Button addReceiptButton = new Button(MobileSelector.ACCESSIBILITY, "add-receipt-button");
    private Button addReceiptConfirmButton = new Button(MobileSelector.ACCESSIBILITY, "confirm-button");
    private Image receiptImage = new Image(MobileSelector.ACCESSIBILITY, "receipt-image");


    @Step("Add receipt from image library")
    public ExpenseDetailsPage addDefaultReceiptImage() throws Exception{

        // add image   accessibility id   add-receipt-button   visible  enabled
        addReceiptButton.click();

        // allow access - accept alert
        acceptAlert();

        // use existing     accessibility id
        driver.findElementByAccessibilityId("album-button").click();

        try{
            String ps = driver.getPageSource();
            System.out.println(String.format("Page source= '%s'", ps));

            new TouchAction(driver)
                    .tap(PointOption.point(90, 130))
                    .perform();
        } catch (Exception ex){
            System.out.println(String.format("Failed to click on Image. Exeption = '%s'", ex.toString()));
        }

        addReceiptConfirmButton.click();

        Assert.assertTrue(receiptImage.isDisplayed(), "Receipt image is missing");
        Assert.assertFalse(addReceiptButton.exist(3));

        addScreenshotToAllure("Attached receipt");

        return this;
    }

    @Step("Delete expense")
    public OtherExpensesPage delete(){
        deleteButton.click();

        try{
            TimeUnit.SECONDS.sleep(1);

            Alert alert = driver.switchTo().alert();
//            System.out.println("try to dismiss alert " + alert.getText());
            alert.accept();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new OtherExpensesPage();
    }

    public OtherExpensesPage close(){
        closeButton.click();
        return new OtherExpensesPage();
    }

    @Step("Verify all fields have correct values")
    public ExpenseDetailsPage verifyValues(){
        return this;
    }

    @Step("Save expense")
    public OtherExpensesPage save() throws Exception{
        saveButton.click();
        TimeUnit.SECONDS.sleep(5);
        return new OtherExpensesPage();
    }

    @Step("Set comment '{value}'")
    public ExpenseDetailsPage setComment(String value){
        return setTextValue(commentTextInput, value);
    }

    @Step("Set merchant '{value}'")
    public ExpenseDetailsPage setMerchant(String value){
        return setTextValue(merchantTextInput, value);
    }

    @Step("Set place '{value}'")
    public ExpenseDetailsPage setPlace(String value){
        return setTextValue(placeTextInput, value);
    }

    @Step("Set currency '{value}'")
    public ExpenseDetailsPage setCurrency(String value){
        return setTextValue(currencyTextButton, value);
    }

    public String getCurrency(){
        return currencyTextButton.getTextValue();
    }

    @Step("Set amount '{value}'")
    public ExpenseDetailsPage setAmount(String value){
        return setTextValue(amountTextInput, value);
    }

    @Step("Set VAT: county '{countryValue}', value '{vatValue}'")
    public ExpenseDetailsPage setVat(String countryValue, String vatValue) throws Exception{
        setTextValue(vatCountryTextButton, countryValue);
        addScreenshotToAllure("Available Countries");
        if (!vatValue.equals("Default")){
            addScreenshotToAllure("Available VAT values");
            driver.findElementByAccessibilityId(vatValue).click();
        }
        return this;
    }

    public ExpenseDetailsPage setVat(String countryValue) throws Exception{
        return setVat(countryValue, "Default");
    }

    public ExpenseDetailsPage setVatCountry(String value){
        return setTextValue(vatCountryTextButton, value);
    }

    public ExpenseDetailsPage setVatRate(String value){
        return setTextValue(vatRateTextButton, value);
    }

    @Step("Set purchase date '{month}-{day}-{year}'")
    public ExpenseDetailsPage setPurchaseDate(String month, String day, String year){
        this.swipeUpUntilDisplayed(dateTextButton, 6).click();
        List<IOSElement> pickers = driver.findElementsByClassName("XCUIElementTypePickerWheel");
        pickers.get(0).setValue(month);
        pickers.get(1).setValue(day);
        pickers.get(2).setValue(year);

        new TouchAction(driver)
                .tap(PointOption.point(100, 100))
                .perform();
        return this;
    }

    @Step("Set category '{value}'")
    public ExpenseDetailsPage setCategory(String value){
        return setTextValue(categoryTextButton, value);
    }

    @Step("Set client '{value}'")
    public ExpenseDetailsPage setClient(String value){
        return setTextValue(clientTextInput, value);
    }

    private ExpenseDetailsPage setTextValue(TextInput el, String value){
        this.swipeUpUntilDisplayed(el, 3).setTextValue(value);
        return this;
    }

    public String getComment(){
        return commentTextInput.getTextValue();
    }

    @Override
    public void swipeUp(){
        swipeScreenPercent(0.5, 0.6, 0.5, 0.4, 500);
    }


}
