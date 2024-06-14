Feature: Login Feature

Background:
	Given Setup the driver path

  @ValidCredentials
  Scenario: Login with valid Credentials
    Given User is on Login Page
    When User enters username as "Abc123" and password as "BCD234"
    And Clicks Login button
    Then User should able to login to the application

  @InvalidCredentials
  Scenario: Login with valid Credentials
    Given User is on Login Page
    When User enters username as "Abc123" and password as "BCD234"
    And Clicks Login button
    Then User should not login to the application

  @DifferentSetsOfData
  Scenario Outline: Login with "<Username>" Credentials
    Given User is on Login Page
    When User enters username as "<Username>" and password as "<Password>"
    And Clicks Login button
    Then User should able to login to the application

    Examples: 
      | Username | Password |
      | Abc123   | BCD234   |
      | Abc234   | CDE234   |
      | Abc345   | DEF234   |
