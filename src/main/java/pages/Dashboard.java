package pages;

import dataprovider.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Helper;

import java.time.Duration;

public class Dashboard {
    WebDriver driver;
    Actions actions;

    public Dashboard(WebDriver driver){
        this.driver = driver;
    }

    By mangeButton = By.xpath("//span[text()='Manage']");
    By manageCourse = By.xpath("//div[contains(@class,'hover')]//a[contains(text(),'Course')]");

    By addNew = By.xpath("//button[contains(text(),'Add New Course ')]");
    By courseName = By.xpath("//input[@name='name']");
    By description = By.xpath("//textarea[@name='description']");

    By instructorName = By.xpath("//input[@name='instructorName']");
    By price = By.xpath("//input[@name='price']");
    By startDate = By.xpath("//input[@name='startDate']");
    By endDate = By.xpath("//input[@name='endDate']");
    By selectCategory = By.xpath("//div[contains(text(),'Select Category')]");

    By saveBtn = By.xpath("//button[contains(text(),'Save')]");


    public Dashboard hoverOnManageandSelect(){
        WebElement manageBtn = driver.findElement(mangeButton);
        actions = new Actions(driver);
        actions.moveToElement(manageBtn).build().perform();
        return new Dashboard(driver);
    }

    public Dashboard clickManageCourses(){
        driver.findElement(manageCourse).click();
        return new Dashboard(driver);
    }

    public Dashboard addNewCorse(String coursename, String desc, String Instructor,
                                 String amount, String startdate, String enddate, String category, String month) {

        if (driver.getCurrentUrl().contains("manage")){
            driver.findElement(addNew).click();
            Helper.uploadFile(driver);
            driver.findElement(courseName).sendKeys(coursename);
            driver.findElement(description).sendKeys(desc);
            driver.findElement(instructorName).sendKeys(Instructor);
            driver.findElement(price).clear();
            driver.findElement(price).sendKeys(amount);
            driver.findElement(selectCategory).click();
            driver.findElement(By.xpath("//button[contains(text(),'"+category+"')]")).click();
            Helper.datePicker(driver,startdate,enddate,month,startDate,endDate);
            driver.findElement(saveBtn).click();
            deleteCourse(driver,coursename);
        }

        return new Dashboard(driver);
    }

    public Dashboard deleteCourse(WebDriver driver,String coursename) {
        WebElement course = driver.findElement(By.xpath("//td[contains(text(),'"+coursename+"')]"));
        Helper.scrollInToVeiw(driver,course);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(String.valueOf(ConfigReader.getProperty("explicitwait")))));
        if (driver.findElement(By.xpath("//td[contains(text(),'"+coursename+"')]")).isDisplayed()){
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//table[contains(@class,'courses')]//following::tr//td[text()='"+coursename+"']")),coursename));
            driver.findElement(By.xpath("//table[contains(@class,'courses')]//following::tr//td[text()='"+coursename+"']//..//input")).click();
           driver.findElement(By.xpath("//table[contains(@class,'courses')]//following::tr//td[text()='"+coursename+"']//..//button")).click();
           Helper.waitForSeconds(4);
        }
        if (wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//td[contains(text(),'"+coursename+"')]"),coursename))){
            System.out.println("INFO LOG:"+coursename +" "+"Course is deleted");
        }
        return new Dashboard(driver);
    }

}
