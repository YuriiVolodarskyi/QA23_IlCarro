package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase{
    WebDriver wd;
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#password"), password);
        //type(By.xpath("//input[@placeholder='Email']"), email);
    }

    public void submitLogin() {
        //click(By.xpath("//button[text()='Y’alla!']")); DOESN'T WORK! SYMBOL ’ CHANGED FOR вЂ™
        click(By.cssSelector("[type='submit']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }
}
