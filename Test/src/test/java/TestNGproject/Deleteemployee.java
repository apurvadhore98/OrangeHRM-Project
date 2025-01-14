package TestNGproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Deleteemployee {
	
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
		public void Deleteemployee() throws InterruptedException
		{
			//Find USername And enter Invalid Username
			Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
			//Find password and enter password Invalid 
			Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
			//login button click
			Driver.findElement(By.xpath("//button[@type='submit']")).submit();
			//Find PIM Menu and click on PIM Menu
			Driver.findElement(By.xpath("//span[text()='PIM']")).click();
			//Select Employee List
			Driver.findElement(By.xpath("//a[text()='Employee List']")).click();
			//enter employee name
			Driver.findElements(By.tagName("input")).get(1).sendKeys("jennie");
			//Click the search button.
			Driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

			Thread.sleep(3000);
			//click on delete icon of the record
			Driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();


			//click on yes, delete messaage button
			Driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();

			//check for message "No Record Found"
			String msg = Driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();

			Assert.assertEquals(msg, "No Records Found");

			Thread.sleep(5000);

			
	}
		@AfterTest
		public void teardown() throws InterruptedException
		{
			Thread.sleep(3000);
			Driver.close();
			Driver.quit();
			
		}		
		
	}

}
