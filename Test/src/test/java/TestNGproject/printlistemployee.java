package TestNGproject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class printlistemployee {
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
public void Login() throws IOException, InterruptedException
{   
	//Find username and enter username "Admin"
	Driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	
	//Find password and enter password admin123
	Driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	
	//login button click
	Driver.findElement(By.xpath("//button[@type='submit']")).submit();
	
	//Find PIM Menu and click on PIM Menu
	Driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
	//Select Employee List
	Driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	
	//Find total link
	List<WebElement> totalLinksElements=Driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));
	int totalLinks = totalLinksElements.size();
	
	for (int i=0; i<totalLinks; i++ )//0,1,2,3,
	{

		try
		{
			String currentLinkText = totalLinksElements.get(i).getText();


			int page = Integer.parseInt(currentLinkText);
			System.out.println("Page: " + page);

			totalLinksElements.get(i).click();

			Thread.sleep(2000);

			List <WebElement> emp_list = Driver.findElements(By.xpath("//div[@class='oxd-table-card']/div /div[4]"));

			for(int j=0; j<emp_list.size();j++)
			{
				//print last name of each row 
				String lastName = emp_list.get(j).getText();
				System.out.println(lastName);
			}
		}
		catch(Exception e)
		{
			System.out.println("Not a number.");
		}
	}

	Thread.sleep(5000);
	
	Driver.close();
	
	
}


}
