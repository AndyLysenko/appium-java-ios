package uiautomation.tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import uiautomation.components.pages.ExpenseDetailsPage;
import uiautomation.components.pages.LoginPage;
import uiautomation.components.pages.Transactions.CorporateCardPage;
import uiautomation.components.pages.Transactions.OtherExpensesPage;
import uiautomation.utilities.GlobalContext;
import uiautomation.utilities.testRailHelper;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Epic("Demo project")
@Feature("Other Expenses")
public class DemoTests {
    public static IOSDriver<IOSElement> driver;
    public static LoginPage loginPage;

    @Test(enabled = true, description = "Demo tests")
    public void addOtherExpensePositiveTest() throws Exception {
        String testMerchant = "TA-Merch-" + new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

        CorporateCardPage corporateCardPage = loginPage.loginAs("Fake User");
        OtherExpensesPage otherExpensesPage = corporateCardPage.openOtherExpense();

        ExpenseDetailsPage expenseDetailsPage = otherExpensesPage.addExpense();
        expenseDetailsPage
            .addDefaultReceiptImage()
            .setComment("TA Exp " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()))
            .setMerchant(testMerchant)
            .setPlace("Test place automation")
            .setCurrency("DKK (Danish Krone)")
            .setAmount("100")
            .setVat("Ukraine", "13%")
            .setPurchaseDate("January", "18", "2018")
            .setCategory("Other")
            .setClient("TestClient_automation")
            .save();

        otherExpensesPage.searchExpense(testMerchant);
        otherExpensesPage.openExpense(testMerchant);

        expenseDetailsPage.verifyValues();
        expenseDetailsPage.delete();

        otherExpensesPage.closeSearch();
        otherExpensesPage.updateList();
        otherExpensesPage.verifyExpensesListIsEmpty();
    }

    @BeforeSuite(description = "Set up and rum Appium server")
    public void setupAppium() throws MalformedURLException {
        driver = GlobalContext.getDriver();
        driver.resetApp();
        loginPage = new LoginPage();
    }

    @AfterSuite(description = "Cleanup after test")
    public void afterSuite() throws Exception {
        System.out.println("This is AfterSuite method");
        driver.quit();

        //create test run in TestRail
        String runId = testRailHelper.publishToTestRail("258", "1");
    }
}