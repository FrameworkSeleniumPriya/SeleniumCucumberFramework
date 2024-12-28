Feature: Home Page Feature

  Background:
    Given user has already logged in to application
      | username | password |
      | Admin    | admin123 |

  @accounts
  Scenario: Accounts page title
    Given user is on home page
    When user gets the title of the page
    Then page title should be "OrangeHRM"

  @accounts
  Scenario: Accounts section count
    Given user is on home page
    Then user gets accounts section
      | Admin       |
      | PIM         |
      | Leave       |
      | Time        |
      | Recruitment |
      | My Info     |
      | Performance |
      | Dashboard   |
      | Directory   |
      | Maintenance |
      | Claim       |
      | Buzz        |
    And accounts section count should be 12