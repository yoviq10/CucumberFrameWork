Feature: Adding employees

  Background:
    And user is logged in with valid admin credentials
    When user clicks on PIM option
    And user clicks on Add employee button

  @smoke
  Scenario: Adding employee from add employee page
    And user enters firstname middlename and lastname
    And user clicks on save button option
    Then employee added successfully

  @smoke
  Scenario: Adding employee from add employee page via feature file
    And user enters first name "Yoshi10" middle name "S" and last name "q10"
    And user clicks on save button option
    Then employee added successfully

    #this is for dataTable
    @example
    Scenario Outline: Adding employee from add employee page via feature file
      And user enters "<FirstName>" "<MiddleName>" and "<LastName>" in the application
      And user clicks on save button option
      Then employee added successfully

      #need to keep the same info from above down here
      Examples:
      |FirstName|MiddleName|LastName|
      |Goku     |MS        |Test1010|
      |Vegeta   |MS        |Test9   |
      |Test8    |MS        |Test8   |

      #refractory
      @datatablewithheader
      Scenario: Adding multiple employees in a single execution
        When add multiple employees and verify they are added successfully
        |FirstName|MiddleName|LastName|
        |Luffy    |Lucy      |OnePiece|
        |Zoro     |Sword     |Rowanowa|
        |chopper  |racoon    |Doctor  |

        @excel
        Scenario: Adding the employee from excel file
          When user adds multiple employees from excel file from "newdata" sheet and verify they are added