package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Helper {
    public static String waitForTextPresent(WebDriver driver, int timeout, String text, By locator) {

            for (int i = 0; i < timeout; i++) {
                try {
                    WebElement ele = driver.findElement(locator);
                    if (ele.getText().contains(text)) {
                        return ele.getText();
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Finding the "+text);
                }
            }

        return null;
    }

    public static boolean takeScreenShot(WebDriver driver){
        try {
            FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File("./ScreenShot/screenshot_"+getCurrentTimeDate()+".png"));
        } catch (IOException e) {
            System.out.println("something went wrong"+e.getMessage());
        }
        return false;
    }

    public static Void takeScreenShotBase64(WebDriver driver){
        try {
            FileHandler.copy(new File(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)),new File(System.getProperty("user.dir")+"/screenshot/screenshot_"+getCurrentTimeDate()+".png"));
        } catch (IOException ex){
            System.out.println("something went wrong"+ex.getMessage());
        }

        return null;
    }

    public static String takeScreenShotInBase64(WebDriver driver) {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String base64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);

        return base64;
    }


    public static String getCurrentTimeDate(){
        String date = new SimpleDateFormat("HH_mm_ss_yyyy_MM_dd").format(new Date());
        return date;
    }

    public static void waitForSeconds(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException ex){
            ex.getMessage();
        }
    }

    public static void waitForAleart(WebDriver driver, int time) {
        Alert alert = null;
        for (int i = 0; i < time; i++) {

            try {
                alert = driver.switchTo().alert();
                System.out.println("Found Aleart and Switched");
            } catch (NoAlertPresentException ex) {
                System.out.println("No aleart Found Till Now");
                waitForSeconds(3);
            }
        }
    }

}
