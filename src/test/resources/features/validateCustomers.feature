@functionalTesting @end2endTesting

Feature: 3 Login to Zoho and validate CRM

  Scenario Outline: 3 Navigate to Zoho.com
    Given launch browser '<browserName>'
    When user navigates to the URL 'http://zoho.com'
    And user click on Contact Sales
    And user mouse hover on products sales
    And user click on CRM
    And user enters the day trail form with '<Name>' and '<email>' and '<password>'  and '<mobile>'
    Then user click on Customers

    Examples: 
      | browserName | Name | email          | password | mobile       |
      | chrome      | Name | zoho@gmail.com | abc1234  | 999 999 9999 |