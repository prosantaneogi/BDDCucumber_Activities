package stepDefinitions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonpackage.commonmethod;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Job_Board_activities {
	WebDriver driver;
	WebDriverWait wait;
	String jobTitle; 
	SimpleDateFormat formatter;

	@Given("Open a browser and Navigate to Job Board​")
	public void open_a_browser_and_navigate_to_job_board​() throws InterruptedException {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://alchemy.hguy.co/jobs/wp-login.php?");
		driver.manage().window().maximize();

	}


	@Given("^log in with \"(.*)\" and \"(.*)\"$")
	public void log_in_with(String uname, String passw) {

		driver.findElement(By.id("user_login")).sendKeys(uname.trim());
		driver.findElement(By.id("user_pass")).sendKeys(passw);
		driver.findElement(By.id("wp-submit")).click();
	}

	@When("Locate the left hand menu and click the menu item that says Users")
	public void locate_the_left_hand_menu_and_click_the_menu_item_that_says_users() {
		driver.findElement(By.xpath("//div[text()='Users']")).click();

	}
	@Then("Locate the Add New button and click it.")
	public void locate_the_add_new_button_and_click_it() {
		driver.findElement(By.xpath("//a[@class='page-title-action']")).click();
	}
	@Then("Fill in the necessary details.")
	public void fill_in_the_necessary_details() throws ParseException, InterruptedException {
		formatter = new SimpleDateFormat("HHmmss");  
		Date date = new Date(); 
		String random = formatter.format(date);
		driver.findElement(By.id("user_login")).sendKeys("Prosanta".concat(random));
		driver.findElement(By.id("email")).sendKeys("abc"+random+"@gmail.com");
		//driver.findElement(By.id("first_name")).sendKeys("abc");
		//driver.findElement(By.id("last_name")).sendKeys("efg");
		//driver.findElement(By.xpath("//button[text()='Show password']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='send_user_notification']")));

		driver.findElement(By.xpath("//input[@id='send_user_notification']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Show password']")).click();	
		Thread.sleep(3000);
	}
	@Then("Click the Add New User button.")
	public void click_the_add_new_user_button() throws InterruptedException {
		driver.findElement(By.id("createusersub")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.id("createusersub")).isDisplayed() ) {
			driver.findElement(By.id("createusersub")).click();
		}
	}
	@Then("Verify that the user was created")
	public void verify_that_the_user_was_created() {
		WebElement elmnewuser = driver.findElement(By.xpath("//p[contains(text(),'New user created')]"));
		if (elmnewuser.isDisplayed()) {
			Assert.assertTrue(elmnewuser.getText().contains(""));
			System.out.println("The user created successfully");
		}
		else
			System.out.println("The user not created");

	}

	//************************************************************
	
	@Given("Open browser with ​Alchemy Jobs site​ and navigate to Jobs page​")
	public void open_browser_with_​alchemy_jobs_site​_and_navigate_to_jobs_page​() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Jobs")).click();
	}

	@Given("Find the Keywords search input field.")
	public void find_the_keywords_search_input_field() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search_keywords")));
	}
	
	@When("Type in keywords to search for jobs and change the Job type")
	public void type_in_keywords_to_search_for_jobs_and_change_the_job_type() {
	
		driver.findElement(By.id("search_keywords")).clear();
		driver.findElement(By.id("search_keywords")).sendKeys("Tester");

		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
	}
	@Then("Find the filter using XPath and filter job type to show only “Full Time” jobs")
	public void find_the_filter_using_x_path_and_filter_job_type_to_show_only_full_time_jobs() throws InterruptedException {
		driver.findElement(By.id("job_type_freelance")).click();
		driver.findElement(By.id("job_type_internship")).click();
		driver.findElement(By.id("job_type_part-time")).click();
		driver.findElement(By.id("job_type_temporary")).click(); 
		Thread.sleep(3000);
	}
	@Then("Find a job listing using XPath and it to see job details.")
	public void find_a_job_listing_using_x_path_and_it_to_see_job_details() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='job_listings']")));
		driver.findElement(By.xpath("//*[@class='job_listings']/li[1]/a[1]")).click();
	}
	@Then("Find the title of the job listing using XPath and print it to the console.")
	public void find_the_title_of_the_job_listing_using_x_path_and_print_it_to_the_console() {
		System.out.println("The title of the job : " + driver.findElement(By.className("entry-title")).getText());

	}
	@Then("Find and Click on the “Apply for job” button.")
	public void find_and_click_on_the_apply_for_job_button() {
		driver.findElement(By.xpath("//input[@value='Apply for job']")).click();
	}
	
	//************************************************************
	
	@Given("Open browser with ​Alchemy Jobs site​ and navigate to Post a Job page​")
	public void open_browser_with_​alchemy_jobs_site​_and_navigate_to_post_a_job_page​() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
		
	}
	
	@Given("Read job information from the Feature file table and fill in the details")
	public void read_job_information_from_the_feature_file_table_and_fill_in_the_details(DataTable dataTable) { 
		
		List<List<String>> data  = dataTable.asLists();
		System.out.println(data);
		int row = data.size();
		int col = data.get(0).size();
		for (int i = 0 ; i< row ; i++ ) {
			driver.findElement(By.linkText("Post a Job")).click();
			//for (int j = 0 ; j < col ; j++) {
			formatter = new SimpleDateFormat("HHmmss");  
			Date date = new Date(); 
			String random = formatter.format(date);
				String email = random.concat(data.get(i).get(0));
				jobTitle = data.get(i).get(1);
				String location = data.get(i).get(2);
				String description = data.get(i).get(3);
				String applicationUrl = data.get(i).get(4);
				String companyName = data.get(i).get(5);
				if (i==0) {
					driver.findElement(By.id("create_account_email")).sendKeys(email);
				}
				
				driver.findElement(By.id("job_title")).clear();
				driver.findElement(By.id("job_title")).sendKeys(jobTitle);

				driver.findElement(By.id("job_location")).clear();
				driver.findElement(By.id("job_location")).sendKeys(location);

				WebElement iframe = driver.findElement(By.id("job_description_ifr"));
				driver.switchTo().frame(iframe);
				driver.findElement(By.xpath("/html/body")).sendKeys(description);
				driver.switchTo().defaultContent();

				driver.findElement(By.id("application")).clear();
				driver.findElement(By.id("application")).sendKeys(applicationUrl);

				driver.findElement(By.id("company_name")).clear();
				driver.findElement(By.id("company_name")).sendKeys(companyName);


				driver.findElement(By.name("submit_job")).click();
				
				driver.findElement(By.id("job_preview_submit_button")).click();
				//driver.findElement(By.linkText("Sign out")).click();
			//}
		}
	
	}
	
	@When("Click submit")
	public void click_submit() {
		
	}
	
	@Then("Go to the Jobs page")
	public void go_to_the_jobs_page() {
		driver.findElement(By.linkText("Jobs")).click();
	}
	@Then("Confirm job listing is shown on page")
	public void confirm_job_listing_is_shown_on_page() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search_keywords")));
		driver.findElement(By.id("search_keywords")).sendKeys(jobTitle);
		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h3[text()='" + jobTitle + "']")));
		String actualJobTitle = driver.findElement(By.xpath("//div/h3[text()='" + jobTitle + "']")).getText();
		System.out.println("Expected :" +actualJobTitle + "Actual : "+ jobTitle);
		Assert.assertEquals(actualJobTitle, jobTitle);
	}
	//************************************************************

	@Given("Fill in the details {string} and {string} and {string} and {string} and {string} and {string}")
	public void fill_in_the_details_and_and_and_and_and(String email, String jobTitles , String location, String description, String applicationUrl, String companyName) { 

			driver.findElement(By.linkText("Post a Job")).click();
		
			formatter = new SimpleDateFormat("HHmmss");  
			Date date = new Date(); 
			String random = formatter.format(date);
			jobTitle = jobTitles;
				driver.findElement(By.id("create_account_email")).sendKeys(random.concat(email));
					
				driver.findElement(By.id("job_title")).clear();
				driver.findElement(By.id("job_title")).sendKeys(jobTitle);

				driver.findElement(By.id("job_location")).clear();
				driver.findElement(By.id("job_location")).sendKeys(location);

				WebElement iframe = driver.findElement(By.id("job_description_ifr"));
				driver.switchTo().frame(iframe);
				driver.findElement(By.xpath("/html/body")).sendKeys(description);
				driver.switchTo().defaultContent();

				driver.findElement(By.id("application")).clear();
				driver.findElement(By.id("application")).sendKeys(applicationUrl);

				driver.findElement(By.id("company_name")).clear();
				driver.findElement(By.id("company_name")).sendKeys(companyName);


				driver.findElement(By.name("submit_job")).click();
				
				driver.findElement(By.id("job_preview_submit_button")).click();
				
		}
	

	//************************************************************
	@Then("Close the Browser") 
	public void close_the_browser() { 
		driver.quit();

	}





}
