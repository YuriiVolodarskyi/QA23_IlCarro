package tests;

import manager.DataProviderCar;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        app.getHelperCar().navigateByLogo();
    }


/*
    public void setApp() {
        app.init();
    }



    @AfterMethod
    public void tearDown() {
        app.stop();
    }
 */
    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel-Aviv, Israel", "1/30/2024", "1/31/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel-Aviv, Israel", "4/28/2024", "7/15/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test(dataProvider = "searchAnyPeriodSuccess", dataProviderClass = DataProviderCar.class)
    public void searchAnyPeriodSuccess(String city, String dataFrom, String dataTo) {
        logger.info("Test data city: " + city + " data from: " + dataFrom + "data to: " + dataTo);
        app.getHelperCar().searchAnyPeriod(city, dataFrom, dataTo);
        app.getHelperCar().getScreen("src/test/screenshots/Any1.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    @Test(dataProvider = "searchWrongPeriod", dataProviderClass = DataProviderCar.class)
    public void searchWrongPeriod(String city, String dataFrom, String dataTo) {
        logger.info("Test data city: " + city + " data from: " + dataFrom + "data to: " + dataTo);
        app.getHelperCar().searchWrongPeriod(city, dataFrom, dataTo);
        app.getHelperCar().getScreen("src/test/screenshots/Wrong1.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isElementPresent(By.className("ng-star-inserted")));
        Assert.assertTrue(app.getHelperCar().isElementPresent(By.xpath("//div[contains(text(),'pick date before today')]")));
        //Assert.assertTrue(app.getHelperCar().isElementPresent(By.xpath("//div[text() = ' You can\'t pick date before today ')]")));
    }


}
