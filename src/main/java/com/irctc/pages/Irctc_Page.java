package com.irctc.pages;

import java.io.IOException;


import com.irctc.commonfactory.BaseUtil;
import com.irctc.commonfactory.CommonFuntion;


public class Irctc_Page extends BaseUtil{

	CommonFuntion lib = new CommonFuntion();
	private BaseUtil base;
	public Irctc_Page(BaseUtil base) { 
		// TODO Auto-generated constructor stub
		this.base = base;
	}
	/*
	 * launch the Irctc site
	 */
	public void launchUrl(String url) throws InterruptedException, IOException {
		driver.get(CommonFuntion.getProperty(url));

	}
	/*
	 * Click on irctc login
	 */
	public void clickLoginlink()
	{
		lib.clickAnElement
		("loginlink");
	}

	public void sendCredentials(String usernames,String passwords) throws IOException, InterruptedException{
		lib.enterText("irusername",
				CommonFuntion.getProperty(usernames));

		lib.enterText("irpassword",
				CommonFuntion.getProperty(passwords));

		lib.waitInSleep();

	}
	public void clickLoginButton()
	{
		lib.clickAnElement
		("signin");
	}
	public void verifypageTitle(String irctcPageTitle) throws IOException{
		lib.verifyPageTitle(CommonFuntion.getProperty(irctcPageTitle));

	}
	public void enterTrainDetails(String fromplace,String toplace) throws IOException
	{

		
		lib.sendValueAndEnter("from", CommonFuntion.getProperty(fromplace));
		lib.sendValueAndEnter("to", CommonFuntion.getProperty(toplace));
		//lib.clearAndEnterValue("date", CommonFuntion.getProperty(datetotrip));
		//lib.sendValueAndEnter("date", CommonFuntion.getProperty(datetotrip) );

	}

	public void calendar(String datetotrip) throws InterruptedException, IOException
	{
		lib.calendarmethod("date", CommonFuntion.getProperty(datetotrip));
	}
	public void clickdropdownButton()
	{
		lib.clickAnElement
		("classdropdown");
	}
	public void classselection(String irctcclass) throws IOException
	{
		lib.trainclassselection("classlist", CommonFuntion.getProperty(irctcclass));
	}
}