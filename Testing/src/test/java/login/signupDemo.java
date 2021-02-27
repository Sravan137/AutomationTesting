package login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import config.PropertiesFileDemo;
import pages.GooglePageElements;
import utils.ExcelUtils;


public class signupDemo {

	static WebDriver driver = null;
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	public static String browserName = null;

	public static void main(String[] args) {	
		setUp();
		signUpTest("","","","");
		tearDown();
	}

	@DataProvider(name="test1data")
	public Object[][] getData(){
		String ProjectPath = System.getProperty("user.dir");
		String excelPath = ProjectPath+"/excel/loginCredentials.xlsx";
		Object data[][] = loginData(excelPath, "Sheet1");
		return data;
	}

	public static Object[][] loginData(String excelPath, String sheetName) {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount-1][colCount];

		for(int i = 1;i < rowCount;i++) {
			for(int j = 0; j < colCount;j++) {
				String cellData = excel.getCellDataString(i, j);
				//String cellData1 = excel.getCellDataNumber(i, j+1);
				//System.out.print(cellData+" | ");
				data[i-1][j] = cellData;
			}
			//System.out.println();
		}
		return data;
	}


	@BeforeClass(alwaysRun = true)
	public static void setUp() {

		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);


		ExtentTest test = extent.createTest("Google Sign Up page","validate signup page functionality");
		test.log(Status.INFO, "Starting Test case");

		String ProjectPath = System.getProperty("user.dir");
		PropertiesFileDemo.getProperties();

		if(browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", ProjectPath+"/driver/chrome/chromedriver.exe");
			driver = new ChromeDriver();
			test.pass("Opened Chrome browser");
			driver.manage().window().maximize();
			test.pass("Chrome browser maximized to full-screen");
			driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");
			test.pass("Navigated to gmail signup page");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.geckodriver", ProjectPath+"/driver/firefox/firefoxdriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		test.info("Opened signup page successfully");
	}

	@SuppressWarnings("static-access")	
	@Test(dataProvider="test1data")
	public static void signUpTest(String firstname, String lastname, String username, String password) {
		System.out.println(username+" | "+password);
		
		ExtentTest test = extent.createTest("Google Sign Up page","validate signup page functionality");
		GooglePageElements searchObj = new GooglePageElements(driver);
		
		try {
			searchObj.firstName(firstname);
			test.pass("Entered first name");
			searchObj.lastName(lastname);
			test.pass("Entered last name");
			//searchObj.signInButton();
			test.pass("clicked on use old signin button");
			searchObj.userName(username);
			test.pass("Entered user name");
			searchObj.passWord(password);
			test.pass("Entered password");
			searchObj.confirmPassword(password);
			test.pass("Re-entered password");
			searchObj.checkBox();
			test.pass("clicked checkbox");
			searchObj.nextButton();
			test.pass("clicked next button");
			test.info("Entered all the details in first-page of gmail signup");
		}catch(Exception exp) {
			System.out.println("message is "+exp.getMessage());
			System.out.println("Cause is "+exp.getCause());
			exp.getStackTrace();
		}
		DesiredCapabilities caps = new DesiredCapabilities();
		driver = new ChromeDriver();
		caps.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);
	}

	@AfterTest
	public static void tearDown() {
		ExtentTest test = extent.createTest("Google Sign Up page","validate signup page functionality");
		driver.close();
		test.pass("closed bowser");
		//driver.quit();
		test.pass("closed everything");
		PropertiesFileDemo.setProperties();

	}

	@AfterSuite
	public static void tearDownData() {
		extent.flush();
	}
}
