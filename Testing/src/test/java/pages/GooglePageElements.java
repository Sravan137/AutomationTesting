package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePageElements {

	static  WebDriver driver = null;

	static By checkBox =  By.className("VfPpkd-muHVFf-bMcfAe");
	static By nextButton = By.className("VfPpkd-RLmnJb");
	static By signInButton = By.className("VfPpkd-RLmnJb");


	public GooglePageElements(WebDriver driver) {
		this.driver = driver;
	}

	public static void firstName(String text) {
		driver.findElement(By.id("firstName")).sendKeys(text);;
	}

	public static void lastName(String text) {
		driver.findElement(By.id("lastName")).sendKeys(text);

	}

	public static void userName(String text) {
		driver.findElement(By.id("username")).sendKeys(text);

	}

	public static void passWord(String text) {
		driver.findElement(By.name("Passwd")).sendKeys(text);

	}

	public static void confirmPassword(String text) {
		driver.findElement(By.name("ConfirmPasswd")).sendKeys(text);

	}

	public static void checkBox() {
		driver.findElement(checkBox).click();

	}

	public static void nextButton() {
		driver.findElement(nextButton).click();;

	}
	
	public static void signInButton() {
		driver.findElement(signInButton).click();
	}

}
