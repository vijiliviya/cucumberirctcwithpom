package com.irctc.commonfactory;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import org.junit.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFuntion extends BaseUtil{
	static Properties prop=new Properties();
	static Properties object;
	String locatorMethod = null;
	String locatorValue = null;
	public WebDriverWait wait = new WebDriverWait(driver, 30);
	String actelement;
	WebElement element;
	static String ActualTitle;

	public static String getProperty(String key) throws IOException {
		String value = "";
		String propFileName = System.getProperty(
				"user.dir") + "\\src\\test\\java\\config\\config.properties";
		FileInputStream file = new FileInputStream(propFileName);
		prop.load(file);
		value = prop.getProperty(key);

		return value;

	}
	/*
	 *To read locator attribute and attribute 
	 *values from locators properties
	 *
	 */
	public String[] readProperties(String locatorKey) {
		String[] locatorMethodName = null;
		try {
			String objectFileName = "..\\CucumberwithPOMIRCTC\\src\\test\\java\\config\\locators.properties";
			object = loadPropertiesFile(objectFileName);
			//locatorKey = locatorKey.replace(" ", "").replace(":", "");
			String objectValue = object.getProperty(locatorKey);
			locatorMethodName = objectValue.split("#");
		} catch (Exception e) {
			System.out.println("Error on readProperties: "+e.getCause());
		}
		return locatorMethodName;
	}

	/*
	 * To load properties from application.properties file
	 * 
	 * @param propFilePath
	 * @return
	 */

	public Properties loadPropertiesFile(String propFilePath) {
		Properties properties = null;
		try {
			properties = new Properties();
			InputStream fis = new FileInputStream(propFilePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	/*
	 * wait for locator
	 */
	public WebElement getWebElementWithWait(String locatorKey)
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		String locatorMethod = null;
		String locatorValue = null;
		WebElement element=null;
		try
		{
			String[] locatorMethodName=readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		}
		catch (Exception e) {
			System.out.println("error on config properties:"+e);
		}
		try
		{
			switch(locatorMethod)
			{
			case "id":
				element=driver.findElement(By.id(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "name":

				element = driver.findElement(By.name(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "class":
				element = driver.findElement(By.className(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "partiallinkText":
				element = driver.findElement(By.partialLinkText(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "tagname":
				element = driver.findElement(By.tagName(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "css":
				element = driver.findElement(By.cssSelector(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
		}
		catch (Exception e) {
			System.out.println("Error on  getWebElementWithWait method: "+e.getCause());
		}
		return element;

	}
	public void waitInSleep() throws InterruptedException 
	{
		Thread.sleep(15000);
	}
	/*
	 * Method to get the value
	 */
	public String getValue(String locatorKey) {
		try {
			waitInSleep();
			element = getWebElementWithWait(locatorKey);
			actelement=element.getText().trim();
		} catch (Exception e) {
			System.out.println("Error on get elements: "+e.getMessage());
		}
		return actelement;
	}
	/*
	 * Method to enter the text
	 */
	public void enterText(String locatorKey ,String data )
	{
		try
		{
			element=getWebElementWithWait(locatorKey);
			element.sendKeys(data);
		}
		catch (Exception e) {
			System.out.println("Error on  enterText method: "+e.getCause());
		}
	}

	/*
	 * Method to enter the value and enter the key
	 */
	public void sendValueAndEnter(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			element.sendKeys(data);
			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);	
			//element.sendKeys(Keys.ENTER);
			waitInSleep();
		} catch (Exception e) {
			System.out.println("Error on  sendValueAndEnter method: "+e.getCause());
		}
	}
	/*
	 * Method to clear the value and enter the value
	 */
	public void clearAndEnterValue(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			Thread.sleep(2000);
			element.clear();
			element.sendKeys(data);
			waitInSleep();
		} catch (Exception e) {
			System.out.println("Error on  sendValueAndEnter method: "+e.getCause());
		}
	}
	/*
	 * Method to verify page title
	 */
	public void verifyPageTitle(String ExpectedTitle) {
		try{
			waitInSleep();
			wait.until(ExpectedConditions.titleContains(ExpectedTitle));
			String ActualTitle = driver.getTitle();
			System.out.println(ActualTitle);
			System.out.println("Expected Title"+ExpectedTitle+"is match with Actual Title"+ActualTitle);
			Assert.assertEquals(ExpectedTitle, ActualTitle);
		}
		catch(Exception e)
		{
			System.out.println("Expected Title"+ExpectedTitle+"is not match with Actual Title"+ActualTitle);
			Assert.assertNotEquals(ExpectedTitle, ActualTitle);
		}

	}
	/*
	 * Click an Element
	 */
	public boolean clickAnElement(String locatorKey) {
		WebElement element = null;
		boolean status = false;
		try {
			//if (object.getProperty(locatorKey) != null && object.getProperty(locatorKey).contains("#")) {
			element = getWebElementWithWait(locatorKey);
			//} else {
			//element = getWebElementWithWait(locatorKey);
			//}
			wait.until(ExpectedConditions.elementToBeClickable(element));
			status = element.isDisplayed();
			Assert.assertTrue(status);
			element.click();
			waitInSleep();
			System.out.println(locatorKey+" is clicked");
			return status;

		} catch (Exception e) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);
				System.out.println(locatorKey+" is clicked by java script");
				waitInSleep();
			} catch (Exception e1) {
				System.out.println("Error on clickAnElement: "+e.getCause());

			}
		}
		return status;
	}
	/*
	 * wait for presence of element
	 */
	public void waitForElementUsingPresence(String locatorKey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				break;
			case "name":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				break;
			case "class":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				break;
			case "linkText":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				break;
			case "tagname":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				break;
			case "css":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on waitForElementUsingPresence: "+e.getCause());
		}
	}

	/*
	 * wait for presence of element
	 */
	public void waitForInvisibilityElement(String locatorKey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "name":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "class":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "linkText":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "tagname":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "css":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "xpath":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on wait for invisibility of element: "+e.getCause());
		}
	}

	/*
	 * wait for element to be clicked
	 */
	public void waitForElementToBeClickable(String locatorKey) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on elementToBeClickable: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "name":
				wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "class":
				wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "linkText":
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "tagname":
				wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "css":
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "xpath":
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on waitForElementUsingPresence: "+e.getMessage());
		}
	}
	/**
	 * Verify RadioButton or CheckBox is selected
	 */
	public void verifyRadioButtonOrCheckBoxIsSelected(String locatorKey) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			if (element.isSelected()) 
			{
				System.out.println(element+" is selected");
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	/**
	 * Click RadioButton or CheckBox
	 */
	public void clickRadioButtonOrCheckBox(String locatorKey) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			if (!element.isSelected()) {
				element.click();
				System.out.println(element+" is selecting...");
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Select drop down value
	 * @throws InterruptedException 
	 */
	public void selectDropdown(String locatorKey, String Option, String data) throws InterruptedException {
		WebElement element = getWebElementWithWait(locatorKey);
		Select sel = new Select(element);
		try {
			if (Option.equalsIgnoreCase("VisibleText")) {
				sel.selectByVisibleText(data);

			} else if (Option.equalsIgnoreCase("Value")) {
				sel.selectByValue(data);
			} else if (Option.equalsIgnoreCase("Index")) {
				int index = Integer.parseInt(data);
				sel.selectByIndex(index);
			}
			waitInSleep();
			System.out.println("Select value from " + locatorKey + " Listbox "+ data +
					"Expected value " + data + " is selected in the listbox");
		} catch (Exception e) {
			System.out.println("Select value from " + locatorKey + " Listbox "+ data +
					"Expected value " + data + " is not present in the listbox");
		}
	}

	public void alerthandle() throws InterruptedException
	{
		waitInSleep();
		// Switching to Alert        
		Alert alert = driver.switchTo().alert();		

		// Capturing alert message.    
		String alertMessage= driver.switchTo().alert().getText();		

		// Displaying alert message		
		System.out.println(alertMessage);	
		Thread.sleep(5000);

		// Accepting alert		
		alert.accept();	
	}

	// clicking the calendar 
	public static void selectDateByJS(WebDriver driver,WebElement element,String data) throws InterruptedException 
	{
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].value = '"+ data +"';",element);
		//js.executeScript("arguments[0].setAttribute('value', '"+dateValue+"');",element);

	}
	public void calendarmethod(String locatorKey,String data) throws InterruptedException
	{
		//WebElement date=driver.findElement(By.xpath("//input[@class='ng-tns-c14-6 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']"));
		WebElement element=getWebElementWithWait(locatorKey);
		//String dateval="18-09-2019";
		selectDateByJS(driver, element, data);
	}
	public void trainclassselection(String locatorKey, String expvalue)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String tdList = null;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
		}

		try {
			switch (locatorMethod) {
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				List<WebElement> initiList = element.findElements(By.tagName("li"));
				System.out.println("span values");

				for(int row=0; row<=initiList.size(); row++)
				{
					tdList = initiList.get(row).findElement(
							By.tagName("span")).getText().trim();
					System.out.println(tdList);
					//Assert.assertEquals(tdList, expvalue);

					if(tdList.contains(expvalue))
					{
						waitInSleep();
						initiList.get(row).click();
						System.out.println("yes");
						System.out.println(tdList+" value is present in irctc page");
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(tdList, expvalue);
			System.out.println("no");
			System.out.println("error on  irctc class table: "+e.getMessage());
		}
	}

	
}