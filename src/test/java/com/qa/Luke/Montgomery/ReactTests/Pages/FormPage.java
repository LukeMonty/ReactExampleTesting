package com.qa.Luke.Montgomery.ReactTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/button")
	private WebElement dropDownButton;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/a[1]")
	private WebElement dropDownUK;
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/a[2]")
	private WebElement dropDownFrance;
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/a[3]")
	private WebElement dropDownGermany;

	@FindBy(id = "nameInput") // Confusing name, this was the one indicating the email input
	private WebElement emailInput;

	@FindBy(id = "passInput")
	private WebElement passwordInput;
	
	@FindBy(id = "passInput2")
	private WebElement secondPasswordInput;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/h1")
	private WebElement successMessage;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/p[10]")
	private WebElement passwordDontMatchStatment;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/button")
	private WebElement submitButton;

	public void clickDropDownButton(WebDriver driver, String input) {
		dropDownButton.click();

		// Waits until the dropdown menu button are present
		dropDownUK = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div/a[1]")));

		if (input.equals(dropDownUK.getText())) {
			dropDownUK.click();
		} else if (input.equals(dropDownFrance.getText())) {
			dropDownFrance.click();
		} else if (input.equals(dropDownGermany.getText())) {
			dropDownGermany.click();
		}

	}

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void enterSecondPassword(String secondPassword) {
		secondPasswordInput.sendKeys(secondPassword);
	}
	
	public void clickSumbitButton() {
		submitButton.click();
	}
	
	public String checkIfSuccessfulMessage() {
		return successMessage.getText();
	}
	
	public String checkWrongPasswordInput() {
		return passwordDontMatchStatment.getText();
	}
	

}
