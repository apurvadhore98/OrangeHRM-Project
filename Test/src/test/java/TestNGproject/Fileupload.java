package TestNGproject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fileupload {
	
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
public void Logintest() throws IOException, InterruptedException
{   
	//Find username and enter username "Admin"
	Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	
	//Find password and enter password admin123
	Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	
	//login button click
	Driver.findElement(By.xpath("//button[@type='submit']")).submit();
	
	// Verify if the login was successful by checking the page title
	String pageTitle = Driver.getTitle();
	Assert.assertEquals("OrangeHRM",pageTitle);
	
	//Find PIM Menu and click on PIM Menu
	Driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
	//Find configuration and click on Configuration
	Driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();
	
	//click on Data import
	Driver.findElement(By.partialLinkText("Data ")).click();
	
	//click on browse button
	Driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();
	
	Thread.sleep(2000);//pause of 5 seconds
	
	Runtime.getRuntime().exec("E:\\selenium TestNG\\Test\\autoitupload.exe");
	
	Thread.sleep(2000);
	
	Driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
}

@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(3000);
	Driver.close();
	Driver.quit();
	
}
}
