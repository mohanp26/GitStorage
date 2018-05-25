package com.mypack.testNG;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestMethods {

	public WebDriver driver;
	@Test(priority=0)
	public  void f1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","F:\\Mohan P\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.gmail.com");
		Thread.sleep(5000);
	}
	@Test(priority=1)
	@Parameters({"uid"})
	public void f2(String u) throws Exception
	{
		driver.findElement(By.name("identifier")).sendKeys(u);
		Thread.sleep(5000);
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(5000);
	}
	@Test(priority=2)
	@Parameters({"criteria"})
	public void f3(String c)
	{
		try
		{
			if(c.equalsIgnoreCase("valid") && driver.findElement(By.name("password")).isDisplayed())
			{
				Assert.assertTrue(true,"userid test passed");
			}
			else if(c.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//*[contains(text(),'Enter an email')]")).isDisplayed())
			{
				Assert.assertTrue(true,"Userid test passed");
			}
			else
			{
				Assert.assertTrue(false,"Userid test failed");
				DateFormat df = new SimpleDateFormat("dd-mm-yy-hh-mm-ss");
				Date d = new Date();
				String imagename = df.format(d);
				File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String l = "C:\\Users\\DELL\\eclipse-workspace\\TestNGFW\\"+imagename+".png";
				File dest = new File(l);
				FileUtils.copyFile(source,dest);
				String path = "<img src=\"file://"+l+"\"alt=\"\"/>";
				Reporter.log("Capcher Screenshot path is "+path);
				
			}
			
		}catch(Exception ex)
		{
			Assert.assertTrue(false,"Userid test interrupted");
		}
	}
	@Test(priority=3,dependsOnMethods= {"f3"})
	public void f4()
	{
		driver.close();
	}
	
}
