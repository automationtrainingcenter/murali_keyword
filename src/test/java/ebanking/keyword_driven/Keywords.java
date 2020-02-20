package ebanking.keyword_driven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	WebDriver driver;

//	openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		if(testData.toLowerCase().equals("chrome")){
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}else if(testData.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			throw new RuntimeException("invalid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.get(testData);
	}

//	fillTextBox
	public void fillTextBox(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).sendKeys(testData);
	}

//	selectByText
	public void selectByText(String locType, String locValue, String testData) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		Select selectEle = new Select(ele);
		selectEle.selectByVisibleText(testData);
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		driver.switchTo().alert().accept();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		driver.close();
	}

}
