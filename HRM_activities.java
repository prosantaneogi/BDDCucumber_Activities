package stepDefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HRM_activities {
	WebDriver driver;
	WebDriverWait wait;
	SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");  
	Date date = new Date(); 
	String random = formatter.format(date);
	String Vacancyfor = "FULL Stack"+random;
	String vacancyName;
	String fName = random + "pro";
	String lName = random + "neo";
	String email = random + "neo@abc.com";
	String path = System.getProperty("user.dir")+"\\Sample CV.docx";


	@Given("Open the ​OrangeHRM​ page and login with credentials provided")
	public void open_the_​orange_hrm​_page_and_login_with_credentials_provided() throws InterruptedException {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("orange");

		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");

		driver.findElement(By.id("btnLogin")).click();

	}

	@When("Navigate to the Recruitment page")
	public void navigate_to_the_recruitment_page() {
		WebElement dashLink = driver.findElement(By.id("menu_dashboard_index"));
		WebElement recLink = driver.findElement(By.id("menu_recruitment_viewRecruitmentModule"));

		if (dashLink.isEnabled()) {

			recLink.click();

		} else {

			dashLink.click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_recruitment_viewRecruitmentModule")));
			recLink.click();
		}
	}
	@When("Click on the Vacancies menu item to navigate to the vacancies page")
	public void click_on_the_vacancies_menu_item_to_navigate_to_the_vacancies_page() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_recruitment_viewJobVacancy")));		
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}
	@When("Click on the Add button to navigate to the Add Job Vacancy form")
	public void click_on_the_add_button_to_navigate_to_the_add_job_vacancy_form() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
	}
	@When("Fill out the necessary details")
	public void fill_out_the_necessary_details() {

		WebElement addJobVacancy_jTitle = driver.findElement(By.id("addJobVacancy_jobTitle"));

		Select selectJobTitle = new Select(addJobVacancy_jTitle);
		selectJobTitle.selectByValue("3");

		driver.findElement(By.id("addJobVacancy_name")).sendKeys(Vacancyfor);

		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Test Employee");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(Keys.ENTER);
	}
	@When("Click the Save button to save the vacancy")
	public void click_the_save_button_to_save_the_vacancy() {
		driver.findElement(By.id("btnSave")).click();
	}
	@Then("Verify that the vacancy was created")
	public void verify_that_the_vacancy_was_created() {
		driver.findElement(By.id("btnBack")).click();

		Select selectJobTitle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		selectJobTitle.selectByValue("3");

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_jobVacancy")));

		Select selectJobVacancy = new Select(driver.findElement(By.id("vacancySearch_jobVacancy")));
		selectJobVacancy.selectByVisibleText(Vacancyfor);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_hiringManager")));

		Select selectHiringManager = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
		selectHiringManager.selectByVisibleText("Test Employee");

		driver.findElement(By.id("btnSrch")).click();

		String actualVacancyName = driver.findElement(By.linkText(Vacancyfor)).getText();

		Assert.assertEquals(actualVacancyName, Vacancyfor);
	}

	//************************************************************

	@When("click on the Add button to add candidateinformation")
	public void click_on_the_add_button_to_add_candidateinformation() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_recruitment_viewCandidates")));		
		driver.findElement(By.id("menu_recruitment_viewCandidates")).click();
		driver.findElement(By.id("btnAdd")).click();
	}


	@When("fill in the details of the candidate")
	public void fill_in_the_details_of_the_candidate() {
			
			driver.findElement(By.id("addCandidate_firstName")).sendKeys(fName);
			driver.findElement(By.id("addCandidate_lastName")).sendKeys(lName);
			driver.findElement(By.id("addCandidate_email")).sendKeys(email);	
	}
	@When("Upload a resume \\(docx or pdf) to the form")
	public void upload_a_resume_docx_or_pdf_to_the_form() {
		driver.findElement(By.id("addCandidate_resume")).sendKeys(path);
	}
	@When("Click Save")
	public void click_save() {
		driver.findElement(By.id("btnSave")).click();
	}
	@Then("Navigate back to the Recruitments page to confirm candidate entry")
	public void navigate_back_to_the_recruitments_page_to_confirm_candidate_entry() {
		driver.findElement(By.id("btnBack")).click();

		driver.findElement(By.id("candidateSearch_candidateName")).clear();
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys(fName+" "+lName);
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("btnSrch")).click();

		String actualCandidateName = driver.findElement(By.linkText(fName+" "+lName)).getText();

		Assert.assertEquals(actualCandidateName, fName+" "+lName);
	}

	//************************************************************
	
	@When("Find the PIM option in the menu and click it.")
	public void find_the_pim_option_in_the_menu_and_click_it() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_dashboard_index")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_pim_viewPimModule")));
		WebElement dashLink = driver.findElement(By.id("menu_dashboard_index"));
		WebElement PIMLink = driver.findElement(By.id("menu_pim_viewPimModule"));

		if (dashLink.isDisplayed()) {
			PIMLink.click();
		} else {

			dashLink.click();
			PIMLink.click();
		}
		
	}

	@When("Click the Add button to add a new Employee.")
	public void click_the_add_button_to_add_a_new_employee() {
		driver.findElement(By.id("menu_pim_addEmployee")).click();
	}
	@When("Make sure the Create Login Details checkbox is checked.")
	public void make_sure_the_create_login_details_checkbox_is_checked() {
		driver.findElement(By.id("chkLogin")).click();
	}
	@When("Fill in the required fields {string} and {string} and {string} and {string} and {string} and click Save.")
	public void fill_in_the_required_fields_and_and_and_and_and_click_save(String FName, String LName, String UName, String PWord, String ConfirmPWord) {
		
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys(FName);
		
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys(LName);
		
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(UName);
		
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys(PWord);
		
		driver.findElement(By.id("re_password")).clear();
		driver.findElement(By.id("re_password")).sendKeys(ConfirmPWord);		
		
		driver.findElement(By.id("btnSave")).click();
	}

	@Then("Verify that the employees have been created with {string} and {string}.")
	public void verify_that_the_employees_have_been_created_with_and(String FName, String LName) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='profile-pic']/h1")));
		String actualEmployeeName = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
		
		Assert.assertEquals(actualEmployeeName, FName +" "+ LName);
	}
	


	//************************************************************
	@When("Fill out the necessary details using {string}")
	public void fill_out_the_necessary_details_using(String vacName) {
		WebElement addJobVacancy_jTitle = driver.findElement(By.id("addJobVacancy_jobTitle"));
		vacancyName = vacName + random;
		Select selectJobTitle = new Select(addJobVacancy_jTitle);
		selectJobTitle.selectByValue("3");

		driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacancyName);

		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Pro123 Neo123");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(Keys.ENTER);
	}
	
	@Then("Verify that {string} the vacancies have been successfully created.")
	public void verify_that_the_vacancies_have_been_successfully_created(String vacName) {
		driver.findElement(By.id("btnBack")).click();
		
		Select selectJobTitle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		selectJobTitle.selectByValue("3");

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_jobVacancy")));

		Select selectJobVacancy = new Select(driver.findElement(By.id("vacancySearch_jobVacancy")));
		selectJobVacancy.selectByVisibleText(vacancyName);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_hiringManager")));

		Select selectHiringManager = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
		selectHiringManager.selectByVisibleText("Pro123 Neo123");

		driver.findElement(By.id("btnSrch")).click();

		String actualVacancyName = driver.findElement(By.linkText(vacancyName)).getText();

		Assert.assertEquals(actualVacancyName, vacancyName);
	}

	//************************************************************
	@Then("Close browser") 
	public void close_the_browser() { 
		driver.quit();

	}

}
