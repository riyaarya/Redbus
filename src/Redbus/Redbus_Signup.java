package Redbus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Redbus_Signup {
	
	@Test(dataProvider = "Redbus_Signup")
	
	public void redbus_signup(String email, String pass) 
	{
		
		Config_Reader cr = new Config_Reader() ;
		
			
			
			//	 new code here
			
			/*FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.gecko.driver", cr.get_gecko_path());
			WebDriver driver =new FirefoxDriver(options);*/
				
			//new code ends here 
		System.setProperty("webdriver.chrome.driver", cr.get_gecko_path());
			
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
			//WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		    driver.get(cr.get_url());
		    driver.findElement(By.id("i-icon-profile")).click();
		    driver.findElement(By.xpath("//*[@id=\"signInLink\"]")).click();
		   // WebDriverWait wait = new WebDriverWait(driver,20);
		    //WebElement ;
		    //aboutMe= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signInView\"]/div[2]/div/div[3]/button")));
		   // driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);// ye bhi to wait hi karega naha
		    WebElement frameElement=driver.findElement(By.className("modalIframe"));
		    driver.switchTo().frame(frameElement);
		    driver.findElement(By.className("signup-btn")).click();
		    driver.findElement(By.id("emailID")).sendKeys(email);
		    boolean submitbuttonPresence= driver.findElement(By.xpath("//*[@id=\"password\"]")).isDisplayed();
		    System.out.println(submitbuttonPresence);
		    WebElement searchTextBox = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		    searchTextBox.clear();
		    searchTextBox.sendKeys("123ggyhjd");

		    //driver.findElement(By.id("password")).sendKeys("hshshsh");
		   // driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		    driver.findElement(By.id("createAccountLink")).click();
		   // driver.findElement(By.xpath("//*[@id=\"createAccountLink\"]")).click();
	}
	
	
}



