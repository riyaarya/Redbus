package Redbus;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Bus_Search
{
	@Test(dataProvider = "Bus_Search")
	
	public void Bus_Search_del(String source,String dest,String odate,String rdate) 
	{
		WebDriverWait wait;
		int currentdate;
		Config_Reader cr = new Config_Reader();
		int i=0;
		System.setProperty("webdriver.chrome.driver", cr.get_gecko_path());
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(Options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(cr.get_url());
		//String firstLetters = String.valueOf(source.charAt(0));
		String firstLetters=source.substring(0,2);
		driver.findElement(By.xpath("//*[@id=\"src\"]")).sendKeys(firstLetters);
		System.out.println("suggest key "+firstLetters);
		try 
		{
			wait = new WebDriverWait(driver, 10);
			WebElement autooptions = driver.findElement(By.className("autoFill"));
			wait.until(ExpectedConditions.visibilityOf(autooptions));
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			//String testtoselect = "Delhi";// there? yes agr hm isko method me dal denge to fr function call jb krnge to kaise 
			// ye na data provder dega na jasie signup em hota hau usme driver k ander hm cl krte h ha ti=o karo pagele bad eme 
		// mai karu ek bar ni i will good
			// dekho karna kuch nai jai bus ye jo hum static likh ahe the na wo data provider dega bus yahi diff hau ha
			 //krne do ruko ek bar dekh omujhe karnnie d o aa tugmayraa  dimmjaeg  wainahti chal raha hai abhu ru kru ya ho gya?
			// yaha kuch cod ekarna hai nahi bus method me jaise pass karte hai bus wahi karna hai bus aur kuch karan hi anhai hau 
			//ha to phle excel ka object bnake jaise niche kia h wasie 
			// are yaha par kyu jab data provider data de raah aahyw 
			//uski eed kyu hai data provider sheetse data de  raa hau na wo recieve karo and use karo bus mai karu 
			
			
			
			List<WebElement> optionstoselect = autooptions.findElements(By.tagName("li"));
			for (WebElement option : optionstoselect)
			{
				if (option.getText().equals(source)) 
				{
					System.out.println("Trying to select: " + source);
					option.click();
					break;
				}
			}
		
			System.out.println("Trying to select destination: "+dest);
			//String firstLetterd = String.valueOf(dest.charAt(0));
			String firstLetterd=dest.substring(0,3);
			driver.findElement(By.xpath("//*[@id=\"dest\"]")).sendKeys(firstLetterd);
			System.out.println("suggest key "+firstLetterd);
			wait = new WebDriverWait(driver, 10);
			WebElement autooptiondest = driver.findElement(By.className("autoFill"));
			wait.until(ExpectedConditions.visibilityOf(autooptiondest));
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			//String textforselectdest = "Dehradun";// nahi likhene tab ye function se aayega ok
			List<WebElement> optiontoselectdest = autooptiondest.findElements(By.tagName("li"));
			for(WebElement optionnew : optiontoselectdest) 
			{
				System.out.println("its selecting:"+ optionnew.getText());
				if(optionnew.getText().equals(dest)) 
				{
					System.out.println("its selecting:"+ dest);
					optionnew.click();
					break;
					
				}
			}
			driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[3]/span")).click();
			List<WebElement> allDates=driver.findElements(By.xpath("//*[@id=\"rb-calendar_onward_cal\"]/table/tbody/tr/td"));
			
			for(WebElement ele:allDates)
			{
				
				System.out.println("its selecting:"+ ele.getText());
				String ondate = ele.getText();
				
				// we assugmed date ki ye aisa date aayega excel se okay
				//String dateteststr="03/18/2018";// assumeing date ye aa raha hai from excel okay
			    Date datetest=new SimpleDateFormat("MM/dd/yyyy").parse(odate);  // thsi from excel okay
			    Calendar cal = Calendar.getInstance();// ye kya kia ye calaner ka instance kiya to get date from date
				cal.setTime(datetest);// yhs time kyu meaning settime kyu?
				//ha kyoki valender me time set kar rahe hai jo mila hai and then uske methid use karke we get what we want
				//meaning we are initializing with a date ok
				// we get the date part because we jave only tis value in date picker like 17 ,18 aise 
				int day = cal.get(Calendar.DAY_OF_MONTH);
				String daystr = Integer.toString(day);
				
				// we are getting date from system and and formatting
				Date currentDate = new Date();
				String currentDateString = new SimpleDateFormat("MM/dd/yyyy").format(currentDate);
				Date datetoday=new SimpleDateFormat("MM/dd/yyyy").parse(currentDateString); 
				
				if(datetest.compareTo(datetoday)>=0)//meaning date coming from test case should be more than current day or equal
				{
					if(ondate.equals(daystr))
					{
					ele.click();
					break;
					}
				}
				/*System.out.println("its selecting:"+ ele.getText());
				String ondate = ele.getText();
				String datetest="03/11/2018";// assumeing date ye aa raha hai from excel okay
				//int intday = Integer.parseInt(datetest);
				 Date d1 = new Date();
				 System.out.println("Current date riya" + d1);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(d1);
				 int year = cal.get(Calendar.YEAR);
				 int month = cal.get(Calendar.MONTH);
				 int day = cal.get(Calendar.DAY_OF_MONTH);
				 String daystr = Integer.toString(day);
			        System.out.println("Current date is we git it" + daystr);
				if(intday>=day)//meaning date coming from test case should be more than current day or equal
				{
					if(ondate.equals(daystr))
					{
					ele.click();
					break;
					}
				}*/
				else
				{
					System.out.println("Test Failed for datae");
					break;
				}
			}
		
			/*driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[4]/div/label")).click();
			List<WebElement> allreturndates = driver.findElements(By.xpath("//*[@id=\"rb-calendar_return_cal\"]/table/tbody/tr/td"));
			for(WebElement eleret:allreturndates) 
			{
				System.out.println("its selecting return date:"+ eleret.getText());
				String retdate = eleret.getText();
				if(retdate.equals("23"))
				{
					eleret.click();
					break;
				}
		    }*/
		
			driver.findElement(By.xpath("//*[@id=\"search_btn\"]")).click();
		}
		catch (NoSuchElementException e)
		{
		System.out.println(e.getStackTrace());
		} 
		catch (Exception e) 
		{
		System.out.println(e.getStackTrace());
		}
	}
	
	@DataProvider(name = "Bus_Search")
	public Object[][] pass_Data(){
		Config_Reader cr = new Config_Reader();
		//System.out.println(cr.get_epath());
		Redbus_excel rbe = new Redbus_excel(cr.get_epath());
		int row = rbe.getrowCount();
		System.out.println("no of rows"+row);
		Object[][] arr = new Object[row-1][4];//here row is 2 na means size yes
		for(int i=0; i<row-1; i++) // here i=1 means less to will getdata then i++ means 2 2<2 to it wont work it will give no data in fields na?yes so remaining
			//array me kuch hai nai o null hi dega na..ni have u noticed ki first br chlta h to no data fr dobara open hua to data nd fr s hua to null. yes kyoki we 
			//set arr[1][0] but we dont set arr[0][..]] to first arr[0] ke sare na then arra[1] thats why fist attemot is blank if its 0 then will it work with data?
			//means like this but gaian data null to ayega na and 0 par to header hai na excel me ha to 1 p data h to blnk kyu ara h let me fx this
		    {
			
			arr[i][0] = rbe.getdata(0, i+1, 0);//wait suno, here i=0, less yes then first row na yesthen i++ means 1 yes and loop dekho row-1 means 1<1 false loop over
			//ye zero p hi print krega na bs 1 p bahar yes ok wat happn? nohing mujhe laga tum dekh rahi ho mor sehe eatd d krmte ea uhr  damta  klda lo ofc me dkhungi isko  ek br
			//matlab are kl ofc me dkhungi acche s ok ok abhi sheet me aur data dalo an d lets check ok
			
			arr[i][1] = rbe.getdata(0, i+1, 1);
			arr[i][2] = rbe.getdata(0, i+1, 2);
			arr[i][3] = rbe.getdata(0, i+1, 3);
		}
		
		return arr;
		// you git what i did? ha o now lets put more info to sheet ok
	}
	
	
}


