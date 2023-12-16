package Pages;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import Base.Base;

public class SignIn extends Base {

	By sign = By.xpath("//div[text()='Sign in']");
	By google = By.xpath("//div[text()='Continue with Google']");
	By email = By.xpath("//*[@id=\"identifierId\"]");
	By next = By.xpath("//span[text()='Next']");
	By error1 = By.xpath("//div[@class='o6cuMc']");

	public void signin() throws InterruptedException, IOException {
		exttest = report.createTest("Display error during invalid Signup. ");
		wait(30, sign);
		driver.findElement(sign).click();
		Thread.sleep(2000);
		String mainWindow = driver.getWindowHandle();
		driver.findElement(google).click();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);
				exttest.log(Status.PASS, "Child window is accessed Successfully");
				driver.findElement(email).sendKeys(prop.getProperty("email"));
				driver.findElement(next).click();
				String error = driver.findElement(error1).getText();
				System.out.println("Error is: " + error);
				exttest.log(Status.PASS, "Error is obtained Successfully");
				TakesScreenshot capture = (TakesScreenshot) driver;
				File srcFile = capture.getScreenshotAs(OutputType.FILE);
				File destFile = new File(System.getProperty("user.dir") + "/Screenshot/Error.png");
				Files.copy(srcFile, destFile);
				exttest.log(Status.PASS, "Screenshot taken Successfully");
				driver.close();
			}
		}
		Thread.sleep(3000);
	}
}
