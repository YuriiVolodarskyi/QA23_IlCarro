package tests;

import models.User;
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
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(System.currentTimeMillis());
        int z = (int)System.currentTimeMillis()/ 1000%3600;
        User user = new User()
                .withFirstName("Lora")
                .withLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Ssnow12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        

    }
    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
