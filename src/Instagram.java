import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Instagram {

	public static void main(String[] args) throws InterruptedException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter your ig username: ");
		String usernameInput = sc.nextLine(); // testerguyman

		System.out.println("Please enter your ig password: ");
		String passwordInput = sc.nextLine(); // Test1234#

		System.out.println("Please enter the keyword you'd like to serach: ");
		String keywordInput = sc.nextLine();

		System.setProperty("webdriver.chrome.driver", "C:/Users/mbhatt/Desktop/chromedriver_win32/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.instagram.com/");
		Thread.sleep(3000);

		WebElement login = driver.findElement(By.className("_b93kq"));
		login.click();

		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));

		username.sendKeys(usernameInput);
		password.sendKeys(passwordInput);

		WebElement loginBtn = driver.findElement(By.className("_4tgw8 "));
		loginBtn.click();
		Thread.sleep(3000);

		List<WebElement> error = driver.findElements(By.id("slfErrorAlert"));

		while (error.size() > 0) {

			WebElement errorMessage = driver.findElement(By.id("slfErrorAlert"));

			System.err.println(errorMessage.getText());
			loginBtn.click();

			System.out.println("Please enter your ig username again: ");
			usernameInput = sc.nextLine(); // testerguyman

			System.out.println("Please enter your ig password again: ");
			passwordInput = sc.nextLine(); // Test1234#

			username.clear();
			username.sendKeys(usernameInput);

			username.clear();
			username.sendKeys(usernameInput);

			password.clear();
			password.sendKeys(passwordInput);

			password.clear();
			password.sendKeys(passwordInput);

			loginBtn.click();

			Thread.sleep(3000);

			List<WebElement> search = driver.findElements(By.className("_avvq0"));

			if (search.size() > 0) {
				break;
			}

		}

		List<WebElement> search = driver.findElements(By.className("_avvq0"));

		for (WebElement obj : search) {

			if (obj.getTagName().equals("input")) {

				obj.sendKeys("#" + keywordInput + "\n");

			}
		}
		Thread.sleep(3000);

		List<WebElement> tag = driver.findElements(By.tagName("a"));

		for (WebElement obj1 : tag) {

			System.out.println(obj1.getText());

			if (obj1.getText().contains(keywordInput.toLowerCase() + "\n")) {

				System.out.println("match");
				obj1.click();
				break;

			}
		}

		Thread.sleep(3000);

		List<WebElement> images = driver.findElements(By.className("_si7dy"));

		for (WebElement obj : images) {

			obj.click();
			Thread.sleep(3000);

			List<WebElement> liked = driver.findElements(By.className("coreSpriteHeartFull"));

			if (liked.isEmpty()) {

				WebElement like = driver.findElement(By.className("coreSpriteHeartOpen"));
				like.click();
				Thread.sleep(3000);

				driver.findElement(By.className("_dcj9f")).click();
				Thread.sleep(3000);

			} else {

				driver.findElement(By.className("_dcj9f")).click();
				Thread.sleep(3000);

			}
		}

	}

}
