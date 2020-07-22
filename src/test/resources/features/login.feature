@SmokeTest @end2endTesting

Feature: 1 Login to Zoho

  Scenario: 1 Navigate to Zoho.com in Chrome and perform login
    Given launch browser 'chrome'
    When user navigates to the URL 'http://zoho.com'
    Then user click on login
