Feature: Dashboard tab functionality

  @dashboardtabs
  Scenario: Dashboard tab verification
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    Then verify the following steps on dashboard
    #passing all the things from datatable & store in arraylist
    |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|