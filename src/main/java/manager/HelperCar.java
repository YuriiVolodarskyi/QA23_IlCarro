package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"), String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //select.selectByIndex(5);
        //select.selectByValue("Gas");
        //select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"Tel-Aviv, Israel", "1/27/2024", "1/30/2024"

        String[] from = dataFrom.split("/");
        String locatorFrom = "//div[text() = ' " + from[1] + " ']";
        click(By.xpath(locatorFrom));

        String[] to = dataTo.split("/");
        String locatorTo = "//div[text() = ' " + to[1] + " ']";
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dataFrom, String dataTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0)
            clickNextMonthButton(diffMonth);
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0)
            clickNextMonthButton(diffMonth);
        //click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonthButton(int diffMonth) {
        if (diffMonth > 0)
            for (int i = 0; i < diffMonth; i++) {
                click(By.cssSelector("button[aria-label='Next month']"));
            }
    }

    public void searchAnyPeriod(String city, String dataFrom, String dataTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        //click(By.id("dates"));
        //clearNew(wd.findElement(By.id("dates")));
        //pause(500);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth = from.getMonthValue() - month;
        int diffMonthToFrom = to.getMonthValue() - from.getMonthValue();

        if (from.getYear() - year > 0) {
            setDayThisYear(from);
            if (diffMonthToFrom > 0)
                clickNextMonthButton(diffMonth);
            click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));
        } else {
            if (to.getYear() - from.getYear() > 0) {
                if (diffMonth > 0)
                    clickNextMonthButton(diffMonth);
                click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
                setDayThisYear(to);

            } else {
                if (diffMonth > 0)
                    clickNextMonthButton(diffMonth);
                click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
                if (diffMonthToFrom > 0)
                    clickNextMonthButton(diffMonthToFrom);
                click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));
            }

        }
    }

    public void setDayThisYear(LocalDate data) {
        click(By.cssSelector("button[aria-label='Choose month and year']"));
        click(By.xpath("//div[text()=' " + data.getYear() + " ']"));
        click(By.xpath("//div[text()=' " + data.getMonth().toString().substring(0, 3) + " ']"));
        click(By.xpath("//div[text()=' " + data.getDayOfMonth() + " ']"));

    }


    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchWrongPeriod(String city, String dataFrom, String dataTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));

        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        type(By.id("dates"), from + " - " + to);
        click(By.className("cdk-overlay-container"));


    }
}


