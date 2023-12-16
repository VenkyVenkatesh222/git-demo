package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.FindSports;
import Pages.MovieLang;
import Pages.SignIn;


public class AllTest {
	
	@Test
	public void testing() throws InterruptedException, IOException
	{
		FindSports ti= new FindSports();
		MovieLang b= new MovieLang();
		SignIn s= new SignIn();
		b.driverSetup();
	    b.openUrl();
		ti.sports();
		b.movie();
		s.signin();
		b.closeBrowser();
	}

}
