package TestNGproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invalidlogin {
	
		public String baseurl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		public WebDriver Driver;
		
		@BeforeTest
		public void setup()
		{
			System.out.println("Before Test Executed");
			Driver=new ChromeDriver();
			//Maximize Window
			Driver.manage().window().maximize();
			//Open URL
			Driver.get(baseurl);
			//Timer
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		
		@Test
		public void loginTest()
		{
			//Find USername And enter Invalid Username
			Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
			//Find password and enter password Invalid 
			Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");
			//login button click
			Driver.findElement(By.xpath("//button[@type='submit']")).submit();
			
			String message_expected ="Invalid credentials";
			String message_actual = Driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
			Assert.assertEquals(message_expected,message_actual );
		}
		@AfterTest
		public void teardown() throws InterruptedException
		{
			Thread.sleep(3000);
			Driver.close();
			Driver.quit();
			
		}
		

	}



