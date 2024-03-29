package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class BrowserFactory {

    static WebDriver driver = null;

    public static WebDriver getDriverInstance(){
        return driver;
    }

    public static WebDriver selectBrowser(String browser, String applicationurl){
        System.out.println("LogInfo: Setting Up the Browser");
        ChromeOptions opt = new ChromeOptions();

        opt.addArguments("--headless");
        opt.addArguments("--no-sandbox");
        opt.addArguments("--remote-allow-origins=*");


        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver(opt);
        } else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        } else {
           driver = new ChromeDriver(opt);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(applicationurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }
}
