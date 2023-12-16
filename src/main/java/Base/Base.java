package Base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ExtentHtmlReporter exthtml;
	public static ExtentTest exttest;
	public static ExtentReports report;

	public void driverSetup() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties")); // Loading the properties
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (prop.getProperty("browserName").matches("chrome")) {
			driver = new ChromeDriver(); // Initializing the new chrome driver
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			driver = new FirefoxDriver(); // Initializing the new firefox driver
		}
		driver.manage().window().maximize(); // To maximize the window
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // Waiting time to page the load completely
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Adding driver waits to timeouts

		exthtml = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "/Report/ExtentReport.html");
		report = new ExtentReports();
		report.attachReporter(exthtml);

	}

	public void openUrl() // Method to open URL for smoke test
	{
		driver.get(prop.getProperty("url"));
	}

	// Function to Put Wait
	public void wait(int sec, By locator) {
		wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void closeBrowser() // method to close the browser
	{
		report.flush();
		driver.quit(); // To close the browser
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}

}
