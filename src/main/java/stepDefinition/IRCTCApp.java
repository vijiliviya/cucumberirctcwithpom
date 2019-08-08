package stepDefinition;


import java.io.IOException;

import com.irctc.commonfactory.BaseUtil;
import com.irctc.commonfactory.CommonFuntion;
import com.irctc.pages.Irctc_Page;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class IRCTCApp extends BaseUtil {
	private BaseUtil base;
	Irctc_Page irctcpageobj = new Irctc_Page(base);
	CommonFuntion lib = new CommonFuntion();

	@Given("^Open chrome browser and start IRCTC application$")
	public void open_chrome_browser_and_start_IRCTC_application() throws InterruptedException, IOException{
		irctcpageobj.launchUrl("irctcurl");
		//lib.alerthandle();
		
	}

	@When("^Login as user on IRCTC application$")
	public void login_as_user_on_IRCTC_application() throws IOException, InterruptedException {
		irctcpageobj.clickLoginlink();
		irctcpageobj.sendCredentials("username","password");
	
		irctcpageobj.clickLoginButton();
	    
	}
	@Then("^User should able to visit the home page$")
	public void user_should_able_to_visit_the_home_page() throws IOException {
		irctcpageobj.verifypageTitle("irctcPageTitle");
	}
	@Then("^Enter Train details$")
	public void enter_Train_details() throws Throwable {
	    irctcpageobj.enterTrainDetails("fromplace", "toplace");
	    irctcpageobj.calendar("datetotrip");
	    irctcpageobj.clickdropdownButton();
	    irctcpageobj.classselection("irctcclass");
	}

}
