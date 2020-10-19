package stepDefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CRM_activities {
	WebDriver driver;
	WebDriverWait wait;
	SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");  
	Date date = new Date(); 
	String random = formatter.format(date);
	String LeadNameuniquepart;
	
	@Given("Open the browser to the ​Alchemy CRM​ site and login.​")
	public void open_the_browser_to_the_​alchemy_crm​_site_and_login_​() throws InterruptedException {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 20);
		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().window().maximize();
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

		driver.findElement(By.id("bigbutton")).click();
	}
	
	@When("Count the number of Dashlets on the homepage")
	public void count_the_number_of_dashlets_on_the_homepage() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		List<WebElement> dashlets = driver.findElements(By.cssSelector(".dashlet-title"));
		System.out.println("Count of Dashlets on the homepage: " + dashlets.size());
	}
	@Then("Print the number and title of each Dashlet into the console")
	public void print_the_number_and_title_of_each_dashlet_into_the_console() {
		
		List<WebElement> dashletTitles = driver.findElements(By.xpath("//td[@class='dashlet-title']/h3/span[contains(@class,'suitepicon-module-')]/following-sibling::span"));
		
		for (WebElement dashletTitle : dashletTitles) {
			System.out.println("The title of each Dashlet : " + dashletTitle.getText());			
		}
		
		List<WebElement> dashletNumbers = driver.findElements(By.cssSelector(".pageNumbers"));
		
		for (WebElement dashletNumber : dashletNumbers) {
			System.out.println("The number of each Dashlet : " + dashletNumber.getText());
		}
	}
	
	//****************************************************************************************
	
	@When("Navigate to Sales -> Leads -> Create Lead")
	public void navigate_to_sales_leads_create_lead() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_0"));
		
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("moduleTab_9_Leads")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Create Lead']")));
		driver.findElement(By.xpath("//div[text()='Create Lead']")).click();
	}
	
	@Then("Fill in the necessary details to create lead accounts using {string} and {string}")
	public void fill_in_the_necessary_details_to_create_lead_accounts_using_and(String fName, String lName) throws InterruptedException {
		Thread.sleep(3000);
		LeadNameuniquepart = random;
		driver.findElement(By.id("first_name")).clear();
		driver.findElement(By.id("first_name")).sendKeys(fName);
		
		driver.findElement(By.id("last_name")).clear();
		driver.findElement(By.id("last_name")).sendKeys(lName + LeadNameuniquepart);
	}
	
	@Then("Click Save to finish.")
	public void click_save_to_finish() {
		driver.findElement(By.id("SAVE")).click();
	}
	@Then("Navigate to the View Leads page to see lead {string}")
	public void navigate_to_the_view_leads_page_to_see_lead(String leadName) throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Leads']")));
		driver.findElement(By.xpath("//div[text()='View Leads']")).click();
		
		Thread.sleep(3000);
		
		String actualLeadName = driver.findElement(By.linkText(leadName+LeadNameuniquepart)).getText();
		
		Assert.assertEquals(actualLeadName, leadName + LeadNameuniquepart);
	}
	
	//****************************************************************************************
	
	@When("Navigate to Activities -> Meetings -> Schedule a Meeting.")
	public void navigate_to_activities_meetings_schedule_a_meeting() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_3"));
		
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("moduleTab_9_Meetings")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Schedule Meeting']")));
		driver.findElement(By.xpath("//div[text()='Schedule Meeting']")).click();
	}

	@Then("Enter the details of the meeting {string}")
	public void enter_the_details_of_the_meeting(String meetingname) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.id("name")).sendKeys(meetingname);
	}
	@Then("Search for {string} and {string} and add them to the meeting")
	public void search_for_and_and_add_them_to_the_meeting(String member1, String member2) throws InterruptedException {
		WebElement scroll = driver.findElement(By.id("invitees_search"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
		scroll.click();		
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='"+member1+"']//parent::tr//following::input")));
		//driver.findElement(By.xpath("//*[text()='"+member1+"']//parent::tr//following::input")).click();
		driver.findElement(By.id(member1)).click();
		driver.findElement(By.id(member2)).click();
		
		
	}
	
	@Then("Click Save to finish Meeting.")
	public void click_save_to_finish_meeting() {
		WebElement scroll = driver.findElement(By.id("SAVE_HEADER"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", scroll);
		scroll.click();
	}
	
	@Then("Navigate to View Meetings page and confirm creation of the meeting {string}.")
	public void navigate_to_view_meetings_page_and_confirm_creation_of_the_meeting(String meetingname) throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Meetings']")));
		driver.findElement(By.xpath("//div[text()='View Meetings']")).click();
		
		Thread.sleep(3000);
		
		String actualMeetingSubject = driver.findElement(By.linkText(meetingname)).getText();
		
		Assert.assertEquals(actualMeetingSubject, meetingname);
	}
	//****************************************************************************************
	
	@When("Navigate to All -> Products-> Create Product.")
	public void navigate_to_all_products_create_product() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_5"));
		
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		
		driver.findElement(By.linkText("Products")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Create Product']")));
		driver.findElement(By.xpath("//div[text()='Create Product']")).click();
	}


	@Then("Enter the details of the {string}")
	public void enter_the_details_of_the(String Products) throws InterruptedException {
		Thread.sleep(3500);
		
		driver.findElement(By.id("name")).sendKeys(Products);
	}

	@Then("Go to the View Products page to see all {string}")
	public void go_to_the_view_products_page_to_see_all(String Products) throws InterruptedException {
		Thread.sleep(3500);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Products']")));
		driver.findElement(By.xpath("//div[text()='View Products']")).click();
		
		Thread.sleep(3500);
		
		String actualProductName = driver.findElement(By.linkText(Products)).getText();
		
		Assert.assertEquals(actualProductName, Products);
	}
	
	//****************************************************************************************
	
	@Then("Close the open browser.")
	public void close_the_open_browser() {
		driver.quit();
	}
}
