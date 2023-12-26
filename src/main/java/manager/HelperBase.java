package manager;

import org.openqa.selenium.WebDriver;

public class HelperBase {
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    WebDriver wd;

}
