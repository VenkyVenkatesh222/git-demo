package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import Base.Base;

public class MovieLang extends Base{
	By movies=By.xpath("//a[text()='Movies']");
	By name=By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div/div/div/div/div[2]/a/div/div[3]/div[1]/div");
	By lang=By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div/div/div/div[2]/a/div/div/div[3]/div");

	public void movie() throws InterruptedException {
		exttest = report.createTest("Extract languages for movies. ");
		wait(30, movies);
		driver.findElement(movies).click();
		List<WebElement> langs=driver.findElements(lang);
		List<WebElement> names=driver.findElements(name);
		for(int j=0;j<names.size();++j){
			System.out.println(names.get(j).getText()+" - "+langs.get(j).getText());
		}
		exttest.log(Status.PASS, "Languages are extracted Successfully for different movies");
		Thread.sleep(3000);
	}
}
