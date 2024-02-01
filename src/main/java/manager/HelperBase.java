package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    Logger logger = LoggerFactory.getLogger(HelperBase.class);
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public String getMessage() {
        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
        String text = element.getText();
        //pause(2000);
        return text;

        // return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();

    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

    }

    public void submit() {
        click(By.cssSelector("[type='submit']"));
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextBox(By locator) {
        WebElement el = wd.findElement(locator);
        String os = System.getProperty("os.name");
        //System.out.println(os);
        if (os.startsWith("Win")) {
            el.sendKeys(Keys.CONTROL, "a");
            el.sendKeys(Keys.DELETE);
        } else {
            el.sendKeys(Keys.COMMAND, "a");
            el.sendKeys(Keys.DELETE);
        }
    }
}