package TestNGproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class validlogin {
	
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
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@Test
	public void loginTest()
	{
		//Find USername And enter Admin
		Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		
		//Find password and enter password admin123
		Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		
		//Login Button Click
		Driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		String pageTitle = Driver.getTitle();
		if(pageTitle.equals("OrangeHRM")) {
			System.out.println("Login Successful !");
		}
		else {
			System.out.println("Login Failed");
		}
		Assert.assertEquals("OrangeHRM", pageTitle);
	}
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(3000);
		Driver.close();
		Driver.quit();
		
	}
	

}
