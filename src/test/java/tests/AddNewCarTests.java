package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("alimych65@gmail.com").withPassword("Yv030665!"));
    }

    @Test
    public void addNewCarSuccess() {
        logger.info("Test data valid");
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel-Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900" + i)
                .price(50)
                .about("Very nice car!")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\QA_Projects\\QA23_IlCarro\\02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        logger.info("Assert checks is message with text 'added successful' present");
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture() + " " + car.getModel() + " added successful");
        logger.info("Assert checks is car with entered manufacture and model added");


    }

    @Test
    public void addNewCarSuccessAll() {
        logger.info("Test data valid - all fields");
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel-Aviv, Israel")
                .manufacture("KIA")
                .model("M3")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("677-900" + i)
                .price(50)
                .about("Very nice car!")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        logger.info("Assert checks is message with text 'added successful' present");
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture() + " " + car.getModel() + " added successful");
        logger.info("Assert checks is car with entered manufacture and model added");

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHome();
    }
}
