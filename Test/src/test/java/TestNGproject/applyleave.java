package TestNGproject;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class applyleave {
	public String baseurl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver Driver;
	
	@BeforeTest
	public void setup()
	{
		System.out.println("Before Test Executed");
		Driver = new ChromeDriver();
		//Maximize Window
		Driver.manage().window().maximize();
		//Open URL
		Driver.get(baseurl);
		//Timer
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	}
@Test
	public void Login() throws IOException, InterruptedException
	{   
	//find username and enter username "Admin"
			Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

			//find password and enter password admin123
			Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

			//login button click
			Driver.findElement(By.xpath("//button[@type='submit']")).submit();
			
			
			//click on leave menu
			Driver.findElement(By.linkText("Leave")).click();
			
			//click on Apply menu
			Driver.findElement(By.linkText("Apply")).click();
			
			//click on leave type drop down
			Driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
			
			//select CAN-FMLA option from leave type dropdown
			Driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
			
			//enter from date
			Driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-07-07");
			
			
			//enter comment
			Driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
			Thread.sleep(3000);	
			
			//click on Apply button
			Driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			
			Thread.sleep(3000);
	
	
}
@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(3000);
	Driver.close();
	Driver.quit();
	
}		
}
