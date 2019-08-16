$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("application.feature");
formatter.feature({
  "line": 1,
  "name": "IRCTC New User Validation",
  "description": "",
  "id": "irctc-new-user-validation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 7455763104,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Create new user with all details",
  "description": "",
  "id": "irctc-new-user-validation;create-new-user-with-all-details",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Open chrome browser and start IRCTC application",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Login as user on IRCTC application",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "User should able to visit the home page",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "Enter Train details",
  "keyword": "Then "
});
formatter.match({
  "location": "IRCTCApp.open_chrome_browser_and_start_IRCTC_application()"
});
formatter.result({
  "duration": 7034634528,
  "status": "passed"
});
formatter.match({
  "location": "IRCTCApp.login_as_user_on_IRCTC_application()"
});
formatter.result({
  "duration": 49467839710,
  "status": "passed"
});
formatter.match({
  "location": "IRCTCApp.user_should_able_to_visit_the_home_page()"
});
formatter.result({
  "duration": 15044137631,
  "status": "passed"
});
formatter.match({
  "location": "IRCTCApp.enter_Train_details()"
});
formatter.result({
  "duration": 96069586254,
  "status": "passed"
});
formatter.after({
  "duration": 286408,
  "status": "passed"
});
});