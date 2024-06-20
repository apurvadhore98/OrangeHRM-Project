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

public class Addimage {
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
public void AddImage() throws IOException, InterruptedException
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
	
	//Find Add employee and click on Add Employee option
	Driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
			

	//Enter first name
	Driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Apurva");
			
	//Enter last name
	Driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Dhore");
			
	//Click on Add image
	Driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();
			
	//add image
	Driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();

	Thread.sleep(4000);//pause of 4 seconds

	Runtime.getRuntime().exec("E:\\selenium TestNG\\Test\\addimageautoit.exe");
	
    Thread.sleep(4000);

	//Click on save button
	Driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();			
		
		String confirmationMessage = Driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
			
		if(confirmationMessage.contains("Personal Details"))
		{
			System.out.println("Employee Added Sucessfully!");
		}
		else {
			System.out.println("Employee to add Failed!");
		}
			
		}


@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(3000);
	Driver.close();
	Driver.quit();
	
}		
	

}

