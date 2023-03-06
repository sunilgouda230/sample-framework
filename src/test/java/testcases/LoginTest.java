package testcases;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import org.testng.annotations.Test;
import pages.Login;

public class LoginTest extends BaseClass {

    @Test(dataProvider = "logincred", dataProviderClass = CustomDataProvider.class)
    public void loginToApplication(String username, String password) {
        Login login = new Login(driver);
        login.loginToApplication(username,password);
        System.out.println("hello");
    }
}
