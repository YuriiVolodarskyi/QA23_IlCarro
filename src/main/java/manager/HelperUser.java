package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.cssSelector("#email"), email);
        //type(By.id("email"), email); - можно и так!
        type(By.cssSelector("#password"), password);
        //type(By.xpath("//input[@placeholder='Email']"), email);
    }

    public void fillLoginForm(User user) {
        type(By.cssSelector("#email"), user.getEmail());
        //type(By.id("email"), email); - можно и так!
        type(By.cssSelector("#password"), user.getPassword());
        //type(By.xpath("//input[@placeholder='Email']"), email);
    }

    public void clickOkButton() {
        if (isElementPresent(By.xpath("//button[text() = 'Ok']")))
            click(By.xpath("//button[text() = 'Ok']"));

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }


//**************Registration********************************

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"),user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.id("checkbox-label terms-label"));/0x0
        // click(By.cssSelector("label[for='terms-of-use']"));

        //variant 2
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public void checkPolicyXY() {
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {

            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            int xOffSet = -w / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
}
