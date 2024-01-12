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
    }

    @Test
    public void registrationEmptyName(){
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
        app.getHelperUser().click(By.className("logo"));
    }

    @Test
    public void registrationEmptyLastName(){
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
        app.getHelperUser().click(By.className("logo"));
    }

    @Test
    public void registrationWrongEmail(){
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
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .withFirstName("Yurii")
                .withLastName("Volodarskyi")
                .withEmail("alimych65gmail.com")
                .withPassword("12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent
                (By.xpath("//div[contains(text(), 'Password must contain')]")));
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
