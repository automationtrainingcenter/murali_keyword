package ebanking.keyword_driven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

public class Keywords extends BaseClass{
	WebDriver driver;

//	openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		
		if (testData.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (testData.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			throw new RuntimeException("invalid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.get(testData);
		test.log(Status.INFO, "navigated to "+testData);
	}
	
	// click
	public void click(String locType, String locValue, String testData) {
		test.log(Status.INFO, "locating an elment using "+locType+" --- "+locValue);
		driver.findElement(LocatorHelper.locate(locType, locValue)).click();
		test.log(Status.INFO, "clicked on that element");
		
	}

//	fillTextBox
	public void fillTextBox(String locType, String locValue, String testData) {
		test.log(Status.INFO, "locating an element using "+locType+" --- "+locValue);
		driver.findElement(LocatorHelper.locate(locType, locValue)).sendKeys(testData);
		test.log(Status.INFO, "typed "+testData+ " in above element");
	}

//	selectByText
	public void selectByText(String locType, String locValue, String testData) {
		test.log(Status.INFO, "locating an elment using "+locType+" --- "+locValue);
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		Select selectEle = new Select(ele);
		selectEle.selectByVisibleText(testData);
		test.log(Status.INFO, "selected "+testData+ " option");
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		Alert alert = driver.switchTo().alert();
		test.log(Status.INFO, "alert came with text as "+alert.getText());
		alert.accept();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		driver.close();
	}

	
//	public static void main(String[] args) {
//		Keywords keywords = new Keywords();
//		// retrieve all the methods of the Keywords class
//		Method[] methods = keywords.getClass().getMethods();
//		String methodName = "openBrowser";
//		for(Method method : methods) {
//			if(method.getName().equals(methodName)) {
//				try {
//					System.out.println("calling "+methodName);
//					method.invoke(keywords, "", "", "chrome");
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		}
//	}
}
