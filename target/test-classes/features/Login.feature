Feature: Login

  @simpletag
  Scenario: valid admin login
   #Given user navigates to hrms
   When user enters valid admin username and password
   And user clicks on login button
    Then admin user is successfully logged in

    @regression
    Scenario: valid ess employee login
      #Given user navigates to hrms
      When user enters valid ess username and password
      And user clicks on login button
      Then ess user is successfully logged in

      @regression
      Scenario:  login with valid username and invalid password
      #  Given user navigates to hrms
        When user enters valid username and invalid password
        And user clicks on login button
        Then user see invalid credentials test on login page

        @example
        Scenario Outline: login with multiple data
          When user enters "<username>" and "<password>"
          And  user clicks on login button
          And  "<firstname>" is successfully logged in
          Examples:
          |username         |password   |firstname|
          |Admin            |Hum@nhrm123|Admin |
          |William1236000000|Syntax12!!!!|William|

          #this is just another example of refactory
          @error
          Scenario: Login with valid username and invalid password
            When user enters valid username and invalid password and verify the error
            |username|password|errormessage|
            |Admin   |Human   |Invalid credentials|
            |William1236000000|Syntax|Invalid credentials|

            @errorvalidation
            Scenario Outline: Login with multiple username and password combinations
              When user enters different "<usernamevalue>" and "<passwordvalue>" and verify the "<error>" for all the combinations
              Examples:
              |usernamevalue|passwordvalue|error|
              |Admin        |Sytax123!    |Invalid credentials|
              |abd77        |Hum@nhrm@123!|Invalid credentials|
              |James        |             |Password cannot be empty|
              |             |Syntax123!   |Username cannot be empty|