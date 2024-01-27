package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {

    @BeforeMethod
    public void setApp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel-Aviv, Israel", "1/27/2024", "1/30/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel-Aviv, Israel", "4/28/2024", "7/15/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess1() {
        app.getHelperCar().searchAnyPeriod("Tel-Aviv, Israel", "2/1/2024", "2/11/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess2() {
        app.getHelperCar().searchAnyPeriod("Tel-Aviv, Israel", "6/2/2024", "12/22/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess3() {
        app.getHelperCar().searchAnyPeriod("Tel-Aviv, Israel", "9/3/2024", "1/13/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess4() {
        app.getHelperCar().searchAnyPeriod("Tel-Aviv, Israel", "1/4/2025", "1/14/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
}
