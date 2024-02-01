package tests;

import manager.DataProviderUser;
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

    @Test(dataProvider = "loginSuccess", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        logger.info("Test data email: " + email + " and password: " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        //Assert
        //Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert checks is message 'Logged in success' present");
    }

    @Test(dataProvider = "loginModelSuccess", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(String email, String password) {
        logger.info("Test data email " + email + " and password " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert checks is message 'Logged in success' present");

        //Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(dataProvider = "loginWrongEmail", dataProviderClass = DataProviderUser.class)
    public void loginWrongEmail(String email, String password) {
        logger.info("Test data email " + email + " and password " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        //logger.info("Assert checks is message '\"Login or Password incorrect\"' present");

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test(dataProvider = "loginWrongPassword", dataProviderClass = DataProviderUser.class)
    public void loginWrongPassword(String email, String password){
        logger.info("Test data email " + email + " and password " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checks is message '\"Login or Password incorrect\"' present");

    }

    @Test(dataProvider = "loginUnregisteredUser", dataProviderClass = DataProviderUser.class)
    public void loginUnregisteredUser(String email, String password){
        logger.info("Test data email " + email + " and password " + password);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checks is message '\"Login or Password incorrect\"' present");
    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
