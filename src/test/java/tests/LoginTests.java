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
        User user = new User().withEmail("alimych65@gmail.com").withPassword("Yv030665!");
//        user.setemail("margo@gmail.com");
//        user.setPassword("Mmar123456$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();

    }



    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submitLogin();

        //Assert
        //Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        //Assert.assertTrue(app.getHelperUser().isLogged());
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
