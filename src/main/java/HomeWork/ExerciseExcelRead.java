package HomeWork;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExcelREAD.ExcelRead;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import Utility.*;
import ExtentReport.*;



public class ExerciseExcelRead {
	WebDriver driver;
	ExcelRead excelFile= new ExcelRead();
	ExtentReport extentreport = new ExtentReport();
	 
@BeforeTest
	public void setup() throws IOException, ParseException
	{
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(excelFile.readExcel(1,0,".\\Data\\DataDriven.xlsx","Sheet1"));
		extentreport.setUpReport();
		
	}
@Test
public void testAdd() throws IOException, ParseException, InterruptedException
	{
	extentreport.startTestCase("New Insurance User");
	
		driver.findElement(By.xpath("/html/body/div[3]/a")).click();
		
		Select title = new Select(driver.findElement(By.id("user_title")));
		title.selectByValue(excelFile.readExcel(1, 1,".\\data\\DataDriven.xlsx", "Sheet1"));
	
		driver.findElement(By.id("user_firstname")).sendKeys(excelFile.readExcel(1,2,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_surname")).sendKeys(excelFile.readExcel(1,3,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_phone")).sendKeys(excelFile.readExcel(1,4,".\\Data\\DataDriven.xlsx","Sheet1"));
		
		Select drpyear = new Select (driver.findElement(By.id("user_dateofbirth_1i")));
		drpyear.selectByVisibleText(excelFile.readExcel(1, 5,".\\data\\DataDriven.xlsx", "Sheet1"));
		
		Select drpmonth = new Select (driver.findElement(By.id("user_dateofbirth_2i")));
		drpmonth.selectByVisibleText(excelFile.readExcel(1, 6,".\\data\\DataDriven.xlsx", "Sheet1"));
		
		Select drpdate = new Select (driver.findElement(By.id("user_dateofbirth_3i")));
		drpdate.selectByVisibleText(excelFile.readExcel(1, 7,".\\data\\DataDriven.xlsx", "Sheet1"));
		
		WebElement radio1 = driver.findElement(By.id("licencetype_t"));
		WebElement radio2 = driver.findElement(By.id("licencetype_f"));
		String Status = excelFile.readExcel(1, 8,".\\data\\DataDriven.xlsx", "Sheet1");
		
		if (Status.equals(radio1))
				{
			radio1.click();
				}else
				{
					radio2.click();
				}
		
		Select drplicencePeriod = new Select (driver.findElement(By.id("user_licenceperiod")));
		drplicencePeriod.selectByVisibleText(excelFile.readExcel(1, 9,".\\data\\DataDriven.xlsx", "Sheet1"));
		
		Select drpoccupation = new Select (driver.findElement(By.id("user_occupation_id")));
		drpoccupation.selectByVisibleText(excelFile.readExcel(1, 10,".\\data\\DataDriven.xlsx", "Sheet1"));
		
		driver.findElement(By.id("user_address_attributes_street")).sendKeys(excelFile.readExcel(1,11,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_address_attributes_city")).sendKeys(excelFile.readExcel(1,12,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_address_attributes_county")).sendKeys(excelFile.readExcel(1,13,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_address_attributes_postcode")).sendKeys(excelFile.readExcel(1,14,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_user_detail_attributes_email")).sendKeys(excelFile.readExcel(1,15,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_user_detail_attributes_password")).sendKeys(excelFile.readExcel(1,16,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.id("user_user_detail_attributes_password_confirmation")).sendKeys(excelFile.readExcel(1,17,".\\Data\\DataDriven.xlsx","Sheet1"));
		driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/input[2]")).click();
		//driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		Thread.sleep(2000);
		String webTitle = driver.getTitle();
		
	if (webTitle.equals("Insurance Broker System - Login"))
		{
			extentreport.logEventsPass("My titile is passing");			
		//Assert.assertEquals(webTitle, "Guru99 Bank New Customer Entry Page");
	
		}
		else 
		{
			extentreport.logEventsFail("This is failing");
			//Assert.assertEquals(webTitle, "Guru99 Bank Customer Registration Page");
		}
		
		extentreport.createFinalReport();
	}


@AfterTest
	public void tearDown() throws IOException
	{
	driver.close();
	driver.quit();
	}

}
