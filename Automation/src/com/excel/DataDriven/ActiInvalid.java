package com.excel.DataDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActiInvalid {

	

	public static void main(String[] args, String Sheet1)  throws Throwable{
		String excelpath="C:\\Users\\MY PC\\Downloads\\DataDrivenFile.xlsx";
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.actitime.com/login.do");
		FectchingDataFromExcel fde = new FectchingDataFromExcel();
		int rowCount = fde.getRowCount(excelpath, Sheet1);
		String Uname=fde.getCelldata(excelpath,"Sheet1", 5, 0);
		String Pass=fde.getCelldata(excelpath, "Sheet1", 5, 0);

		for(int i=1;i<rowCount;i++)
		{

			driver.findElement(By.name("username")).sendKeys(Uname);
			Thread.sleep(2000);
			driver.findElement(By.name("pwd")).sendKeys(Pass);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[.='Login ']")).click();
			Thread.sleep(2000);


			WebElement vara = driver.findElement(By.xpath("(//span[@class='error msg'][1]"));
			vara.getText();
			if(vara.isDisplayed())
			{
				System.out.println("error msg is displayed==> passed");
				fde.setCelldata(excelpath, "Sheet1", i, 2,"Pass");
			}
			else
			{
			        
					System.out.println("error msg is not displayed==>failed");
				fde.setCelldata(excelpath,"Sheet1", i, 2, "Fail");

			}

		}
	}
}


	
