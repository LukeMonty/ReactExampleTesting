package com.qa.Luke.Montgomery.ReactTests.Tests;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.Luke.Montgomery.ReactTests.Main.Constants;
import com.qa.Luke.Montgomery.ReactTests.Pages.FormPage;
import com.qa.Luke.Montgomery.ReactTests.Pages.HomePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReactSteps {

	WebDriver driver;
	ExtentTest test;

	HomePage home;
	FormPage form;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", Constants.DRIVER_FILEPATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test = ReactTests.report.startTest("Fill out form using parameters");
		test.log(LogStatus.INFO, "Go to Website");
	}

	@After
	public void tearDown() {
		driver.quit();
		ReactTests.report.endTest(test);
	}

	@Given("^I navigate to the React Application$")
	public void i_navigate_to_the_React_Application() {
		driver.get(Constants.URL);
		test.log(LogStatus.INFO, "Go to the React application");
	}

	@When("^I click the Link to Automated Testing Exercise Form$")
	public void i_click_the_Link_to_Automated_Testing_Exercise_Form() {
		home = PageFactory.initElements(driver, HomePage.class);
		home.goToForm();
		test.log(LogStatus.INFO, "Click link to go to form");
	}

	@When("^I fill click the \"([^\"]*)\" dropdown menu$")
	public void i_fill_click_the_dropdown_menu(String arg1) {
		form = PageFactory.initElements(driver, FormPage.class);

		form.clickDropDownButton(driver, arg1);
		test.log(LogStatus.INFO, "Click the country button on the dropdown menu: " + arg1);

	}

	@When("^I fill out the email field with \"([^\"]*)\"$")
	public void i_fill_out_the_email_field_with(String arg1) {
		form.enterEmail(arg1);
		test.log(LogStatus.INFO, "Enter into input the email: " + arg1);
	}

	@When("^I fill out the first password field with \"([^\"]*)\"$")
	public void i_fill_out_the_first_password_field_with(String arg1) {
		form.enterPassword(arg1);
		test.log(LogStatus.INFO, "Enter into first password field: " + arg1);
	}

	@When("^I fill out the second password field with \"([^\"]*)\"$")
	public void i_fill_out_the_second_password_field_with(String arg1) {
		form.enterSecondPassword(arg1);
		test.log(LogStatus.INFO, "Enter into second password field: " + arg1);
		form.clickSumbitButton();
		test.log(LogStatus.INFO, "Click submit");
	}

	@Then("^I should see a Success! Message$")
	public void i_should_see_a_Success_Message() throws Throwable {

		if (!form.checkIfSuccessfulMessage().equals("Success!")) {
			test.log(LogStatus.FAIL, "FAIL: One of the inputs was incorrect");
			assertEquals("Success!", form.checkIfSuccessfulMessage());
		} else {
			test.log(LogStatus.PASS, "Sucess: Correct inputs");
			assertEquals("Success!", form.checkIfSuccessfulMessage());
		}

	}

	@When("^I fill out the second password field with the wrong password \"([^\"]*)\"$")
	public void i_fill_out_the_second_password_field_with_the_wrong_password(String arg1) {
		form.enterSecondPassword(arg1);
		test.log(LogStatus.INFO, "Enter into second password field:" + arg1);
		form.clickSumbitButton();
		test.log(LogStatus.INFO, "Click submit");
	}

	@Then("^I should see a message stating that the passwords do not match\\.$")
	public void i_should_see_a_message_stating_that_the_passwords_do_not_match() {
		if (!form.checkWrongPasswordInput().equals("The passwords do not match")) {
			test.log(LogStatus.FAIL, "FAIL: The inputs matched, or there was invalid characters in the first password");
			assertEquals("The passwords do not match", form.checkWrongPasswordInput());
		} else {
			test.log(LogStatus.PASS, "Sucess: Printed correct error message");
			assertEquals("The passwords do not match", form.checkWrongPasswordInput());
		}
	}

}
