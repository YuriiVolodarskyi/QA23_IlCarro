package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess1() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665!'");
        User user = new User().withEmail("alimych65@gmail.com").withPassword("Yv030665!");
//        user.setemail("margo@gmail.com");
//        user.setPassword("Mmar123456$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert checks is message 'Logged in success' present");

        //app.getHelperUser().clickOKButton();

    }

    @Test
    public void loginSuccess() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665!'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submit();

        //Assert
        //Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert checks is message 'Logged in success' present");


    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665!'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submit();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert checks is message 'Logged in success' present");

        //Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test data wrong email: 'mych65@gmail.com' and password: 'Yv030665!'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("mych65gmail.com", "Yv030665!");
        app.getHelperUser().submit();

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        //logger.info("Assert checks is message '\"Login or Password incorrect\"' present");

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test data email: 'alimych65@gmail.com' and wrong password: '030665!'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "030665!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checks is message '\"Login or Password incorrect\"' present");

    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data email that doesn't exist: 'imych65@gmail.com' and password: '030665!'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("imych65@gmail.com", "030665!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checks is message '\"Login or Password incorrect\"' present");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
