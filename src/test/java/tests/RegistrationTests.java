package tests;

import manager.HelperBase;
import manager.HelperUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        logger.info("Test data wright");
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(System.currentTimeMillis());
        int z = (int)System.currentTimeMillis()/ 1000%3600;
        User user = new User()
                .withFirstName("Yurii")
                .withLastName("Volodarskyi")
                .withEmail("volodar" + z + "@gmail.com")
                .withPassword("Volodar12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
        logger.info("Assert checks is message 'You are logged in success' present");
    }

    @Test
    public void registrationEmptyName(){
        logger.info("Test data with empty field FirstName");
        User user = new User()
                .withFirstName("")
                .withLastName("Volodarskyi")
                .withEmail("alimych65@gmail.com")
                .withPassword("Volodar12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[text() = ' Name is required ']")));
        logger.info("Assert checks is element ' Name is required ' present");

        //app.getHelperUser().click(By.className("logo"));
    }

    @Test
    public void registrationEmptyLastName(){
        logger.info("Test data with empty field LastName");
        User user = new User()
                .withFirstName("Yurii")
                .withLastName("")
                .withEmail("alimych65@gmail.com")
                .withPassword("Volodar12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[text() = ' Last name is required ']")));
        logger.info("Assert checks is element ' Last name is required ' present");

        //app.getHelperUser().click(By.className("logo"));
    }

    @Test
    public void registrationWrongEmail(){
        logger.info("Test data with wrong email - without '@'");
        User user = new User()
                .withFirstName("Yurii")
                .withLastName("Volodarskyi")
                .withEmail("alimych65gmail.com")
                .withPassword("Volodar12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[text() = 'Wrong email format']")));
        logger.info("Assert checks is element 'Wrong email format' present");
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is Yalla button not active");

    }

    @Test
    public void registrationWrongPassword(){
        logger.info("Test data with wrong password - short");
        User user = new User()
                .withFirstName("Yurii")
                .withLastName("Volodarskyi")
                .withEmail("alimych65@gmail.com")
                .withPassword("12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent
                (By.xpath("//div[contains(text(), 'Password must contain')]")));
        logger.info("Assert checks is element with text 'Password must contain' present");

        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Password must contain minimum 8 symbols"));
        logger.info("Assert checks is error text 'Password must contain minimum 8 symbols' present");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checks is Yalla button not active");
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
