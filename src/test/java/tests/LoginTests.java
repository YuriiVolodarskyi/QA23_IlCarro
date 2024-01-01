package tests;

import org.testng.Assert;
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
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alimych65@gmail.com", "Yv030665!");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
