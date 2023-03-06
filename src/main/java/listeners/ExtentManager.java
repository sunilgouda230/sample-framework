package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utility.Helper;

public class ExtentManager {

    public static ExtentReports extentReports;

    public static ExtentReports getExtentReportInstance(){
        if (extentReports == null){
            ExtentReports curExtentInstance = createInstance();
            return curExtentInstance;
        } else {
            return extentReports;
        }
    }

    public static ExtentReports createInstance(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/Automation_"+Helper.getCurrentTimeDate()+".html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("Automation Report");
        sparkReporter.config().setDocumentTitle("Sprint Report");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        return extentReports;
    }
}
