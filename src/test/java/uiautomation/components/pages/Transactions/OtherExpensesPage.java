package uiautomation.components.pages.Transactions;
import io.appium.java_client.MobileSelector;
import io.qameta.allure.Step;
import org.testng.Assert;
import uiautomation.components.pages.ExpenseDetailsPage;
import uiautomation.components.pages.PageObject;
import uiautomation.components.uielements.Button;
import uiautomation.components.uielements.TextInput;
import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;

public class OtherExpensesPage extends PageObject {
    public OtherExpensesPage(){
        super();
    }

    private Button addExpense = new Button(MobileSelector.ACCESSIBILITY, "Add Expense");
    private Button searchButton = new Button(MobileSelector.ACCESSIBILITY, "search-button");
    private TextInput searchTextInput = new TextInput(MobileSelector.ACCESSIBILITY, "search-textinput");

    @Step("Start adding expense")
    public ExpenseDetailsPage addExpense(){
        addExpense.click();
        return new ExpenseDetailsPage();
    }

    @Step("Search expense by merchant '{merchant}'")
    public OtherExpensesPage searchExpense(String merchant) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        searchButton.click();
        searchTextInput.getElement().clear();
        searchTextInput.setTextValue(merchant);
        addScreenshotToAllure("Search results");
        return this;
    }

    @Step("Verify expense doesn't exist")
    public OtherExpensesPage verifyExpensesListIsEmpty(){
        Assert.assertTrue(false, "Expense list is not empty");
        return this;
    }

    @Step("Close search")
    public OtherExpensesPage closeSearch()throws Exception{
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsByClassName("XCUIElementTypeButton").get(0).click();
        return this;
    }

    @Step("Open expense with merchant '{value}'")
    public ExpenseDetailsPage openExpense(String value){
        driver.findElementsByAccessibilityId(value).get(1).click();
        return new ExpenseDetailsPage();
    }

    @Step("Update expenses list")
    public OtherExpensesPage updateList() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return this;
    }


}
