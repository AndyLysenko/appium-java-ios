package uiautomation.components.pages.Transactions;

import io.qameta.allure.Step;
import uiautomation.components.pages.PageObject;

public class CorporateCardPage extends PageObject {
    public CorporateCardPage(){
        super();
    }

    @Step("Open other expenses")
    public OtherExpensesPage openOtherExpense() throws Exception{
        swipeLeft();
        addScreenshotToAllure("Other Expenses View");
        return new OtherExpensesPage();
    }
}
