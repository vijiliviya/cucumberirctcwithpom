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
	}public void clickflexiblecheckbox() throws InterruptedException
	{
		lib.clickRadioButtonOrCheckBox("flexiblecheckbox");
		lib.clickRadioButtonOrCheckBox("Journalistcheckbox");
		lib.clickAnElement("Okbutton");

		//lib.alerthandle("Okbutton");

	}
	public void clickfindtrainButton()
	{
		lib.clickAnElement("findTrain");
	}
	public void logoutbutton()
	{
		lib.clickAnElement("logout");
	}
	//	public void pnrbutton(String secondwindow , String firstwindow) throws InterruptedException, IOException
	//	{
	//		lib.clickAnElement("pnrstatus");
	//		Thread.sleep(3000);
	//		CommonFuntion.switchToWindow(CommonFuntion.getProperty(secondwindow));
	//		System.out.println("The Second Window title is "+driver.getTitle());
	//		lib.waitInSleep();
	//		CommonFuntion.switchToWindow(CommonFuntion.getProperty(firstwindow));
	//		System.out.println("The first Window title is "+driver.getTitle());
	//		
	//	}
	public void pnrbutton(String secondwindow) throws InterruptedException, IOException
	{
		lib.clickAnElement("pnrstatus");
		Thread.sleep(3000);
		CommonFuntion.switchToWindow(CommonFuntion.getProperty(secondwindow));
		System.out.println("The Second Window title is "+driver.getTitle());
		lib.waitInSleep();
	}

	public void pnrnostatus(String pnrnumberless,String pnrerror ,String pnrnumber, String pnrinvalid) throws IOException, InterruptedException
	{
		lib.waitForElementUsingPresence("pnrinput");
		lib.enterText("pnrinput", CommonFuntion.getProperty(pnrnumberless));
		lib.clickAnElement("pnrsubmit");
		lib.verifyErrorMessage("pnrerror", CommonFuntion.getProperty(pnrerror));
		lib.clearAndEnterValue("pnrinput", CommonFuntion.getProperty(pnrnumber));
		lib.clickAnElement("pnrsubmit");
		lib.waitInSleep();
		lib.clickAnElement("pnrnosubmit");
		lib.verifyErrorMessage("pnrerror", CommonFuntion.getProperty(pnrinvalid));
	}
	
	public void seatAvail(String seatclassValue, String seatQuotaText , String seatsourcename ,String seatDestinationname ,String seatsourcestationname , String seatdestinationstationname,String trainnumber,String firstwindow) throws InterruptedException, IOException
	{
		lib.clickAnElement("seatavailabilitylink");
		lib.clickAnElement("seatcalendarimg");
		lib.clickAnElement("seatcalendarnextbutton");
		lib.clickAnElement("seatdate");
		lib.selectDropdown("seatavailabilityclass", "Value", CommonFuntion.getProperty(seatclassValue));
		lib.selectDropdown("seatQuota", "VisibleText", CommonFuntion.getProperty(seatQuotaText));
		lib.enterText("seatSourceStation", CommonFuntion.getProperty(seatsourcename));
		lib.seatclasssection("seatsourcestationlist",CommonFuntion.getProperty(seatsourcestationname) );
		lib.enterText("seatDestinationStation", CommonFuntion.getProperty(seatDestinationname));
		lib.seatclasssection("seatDestinationlist", CommonFuntion.getProperty(seatdestinationstationname));
		lib.enterText("seatTrainNo", CommonFuntion.getProperty(seatDestinationname));
		lib.seatclasssection("seatTrainNoList", CommonFuntion.getProperty(trainnumber));
		lib.clickAnElement("seatGetAvailability");
		lib.clickAnElement("seatpopup");
		CommonFuntion.switchToWindow(CommonFuntion.getProperty(firstwindow));
	}
 
}