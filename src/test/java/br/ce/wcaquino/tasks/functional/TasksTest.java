package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {
	
	public WebDriver accessApp() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void shouldSaveTask() {
		WebDriver driver = accessApp();
		try {
			
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Test by Selenium");
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
			
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWhenDescriptionEmpty() {
		WebDriver driver = accessApp();
		try {
			driver.findElement(By.id("addTodo"))
			.click();
					
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWhenDateEmpty() {
		WebDriver driver = accessApp();
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Test by Selenium");
					
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldNotSaveTaskWhenPastDate() {
		WebDriver driver = accessApp();
		try {
			driver.findElement(By.id("addTodo"))
			.click();
					
			driver.findElement(By.id("task")).sendKeys("Test by Selenium");

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			driver.quit();
		}
	}
}
