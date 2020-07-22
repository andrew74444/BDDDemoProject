@SmokeTest @end2endTesting

Feature: 2 Login to Zoho and validate CRM

  Scenario Outline: 2 Navigate to Zoho.com
    Given launch browser '<browserName>'
    When user navigates to the URL 'http://zoho.com'
    Then user click on login

    Examples: 
      | browserName |
      | chrome      |
      | firefox     |
