package TestNGproject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HRMTest {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver Driver;
	
	@BeforeTest
	public void setup()
	{
		System.out.println("Before Test Executed");
		Driver = new ChromeDriver();
		//Maximize Window
		Driver.manage().window().maximize();
		//Open URL
		Driver.get(baseUrl);
		//Timer
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	}
	@Test(priority = 1)
	public void loginInvalid()
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
	
	@Test(priority = 2)
	public void LoginPage()
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
	
	}
	
	@Test(priority = 3)
	public void AddEmployee() throws IOException, InterruptedException
	{   
		
		//Find PIM Menu and click on PIM Menu
		Driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		//Find Add employee and click on Add Employee option
		Driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

		//Enter first name
		Driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Salman");
				
		//Enter last name
		Driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("khan");
		
		Thread.sleep(3000);			
		//Click on Add image
		Driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();
				
		Thread.sleep(3000);//pause of 3 seconds

		Runtime.getRuntime().exec("E:\\selenium TestNG\\Test\\addimageautoit.exe");
		
	    Thread.sleep(3000);

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

	
	@Test(priority = 4)
	public void searchEmployee()
	{
		//Find PIM Menu And Click PIM menu
		Driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		//Select Employee List
		Driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		
		Driver.findElements(By.tagName("input")).get(1).sendKeys("khan");
		
		//Click Search Button
		Driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		List<WebElement>element= Driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));

		String message_actual = element.get(0).getText();
		System.out.println(message_actual);
	}
	
	
	@Test(priority =5)
	public void Fileupload() throws IOException, InterruptedException
	{   
		
		//Find PIM Menu and click on PIM Menu
		Driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		//Find configuration and click on Configuration
		Driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();
		
		//click on Data import
		Driver.findElement(By.partialLinkText("Data")).click();
		
		//click on browse button
		Driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();
		
		Thread.sleep(2000);//pause of 2 seconds
		
		Runtime.getRuntime().exec("E:\\selenium TestNG\\Test\\autoitupload.exe");
		
		Thread.sleep(2000);
		
		Driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
				

	}
	@Test(priority = 6)
	public void Printemployeelist() throws IOException, InterruptedException
	{   
		
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

		Thread.sleep(3000);
	
	}
	
	@Test(priority = 7)
	public void ApplyLeave() throws IOException, InterruptedException
	{   
			//click on leave menu
			Driver.findElement(By.linkText("Leave")).click();
			
			//click on Apply menu
			Driver.findElement(By.linkText("Apply")).click();
			
			//click on leave type drop down
			Driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
			
			//select CAN-FMLA option from leave type dropdown
			Driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
			
			//enter from date
			Driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-06-20");
			
			
			//enter comment
			Driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
			Thread.sleep(3000);	
			
			//click on Apply button
			Driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			
			Thread.sleep(3000);
	
}
	@Test(priority = 8)
	public void Deleteemployee() throws InterruptedException
	{
		//Find PIM Menu and click on PIM Menu
		Driver.findElement(By.xpath("//span[text()='PIM']")).click();
		//Select Employee List
		Driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		//enter employee name
		Driver.findElements(By.tagName("input")).get(1).sendKeys("khan");
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

		Thread.sleep(3000);

		
}
	
	@AfterTest
	public void Logout() throws InterruptedException
	{
		Thread.sleep(3000);
		Driver.close();
		Driver.quit();
	}
	
	
	

}
