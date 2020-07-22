@functionalTesting @end2endTesting

Feature: 4 Login to Zoho and validate CRM

  Scenario Outline: 4 Navigate to Zoho.com
    Given launch browser '<browserName>'
    When user navigates to the URL 'http://zoho.com'
    And user click on Contact Sales
    And user mouse hover on products sales
    And user click on CRM
    And user enters the day trail form with '<Name>' and '<email>' and '<password>'  and '<mobile>'    
    Then user click on Pricing

     Examples: 
      | browserName | Name | email         | password | mobile    |
      | chrome      | Name | zoho1234@gmail.com| xyz1234     | 923 999 3456|
