package testcases;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import org.testng.annotations.Test;
import pages.Login;

public class AddandDelete extends BaseClass {

   @Test(dataProvider = "addanddelete", dataProviderClass = CustomDataProvider.class)
    public void addAndDelete(String username, String password, String message,String coursename, String desc, String instructor,
                             String amount, String startdate, String enddate, String category, String month){
       Login loginTest = new Login(driver);
       loginTest.loginToApplication(username,password,message,"correct cred")
               .hoverOnManageandSelect()
               .clickManageCourses().addNewCorse(coursename,desc,instructor,amount,startdate,enddate,category,month);

    }


}
