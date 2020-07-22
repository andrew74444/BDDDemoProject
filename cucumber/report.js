$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/login.feature");
formatter.feature({
  "name": "1 Login to Zoho",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@SmokeTest"
    },
    {
      "name": "@end2endTesting"
    }
  ]
});
formatter.scenario({
  "name": "1 Navigate to Zoho.com in Chrome and perform login",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SmokeTest"
    },
    {
      "name": "@end2endTesting"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "launch browser \u0027chrome\u0027",
  "keyword": "Given "
});
formatter.match({
  "location": "ZohoSteps.launch_browser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user navigates to the URL \u0027http://zoho.com\u0027",
  "keyword": "When "
});
formatter.match({
  "location": "ZohoSteps.user_navigates_to_the_URL(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user click on login",
  "keyword": "Then "
});
formatter.match({
  "location": "ZohoSteps.user_click_on_login()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});