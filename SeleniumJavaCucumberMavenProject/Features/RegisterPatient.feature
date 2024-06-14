Feature: Register a Patient Feature

  @RegisterMultiplePatients @SmokeTest @SanityTest @RegressionTest
  Scenario Outline: Register patient with patient deatils "<Name>"
    Given User is on the Home Page
    When User selects the Module "Register a patient"
    Then Register a Patient page should be displayed
    When User enters name "<Name>" and clicks next button
    And User selects gender as "<Gender>" and clicks next button
    And User enters  date of birth as "<DateOfBirth>" and clicks next button
    And user enters address as "<Address>" and clicks next button
    And user enters phone number as "<PhoneNumber>" and clicks next button
    And user clicks next button
    Then user should able to verify entered details "<Name>" "<Gender>" "<DateOfBirth>"
    When user clicks confirm button
    Then user should be registered with name "<Name>" and patient Id must be genrated

    Examples: 
      | Name              | Gender | DateOfBirth        | Address                                                      | PhoneNumber |
      | H, Ramesh, Babu   | Male   | 26, December, 2000 | Near HDFC bank,S R Nagar,Hyderabad,Telanagana,India,500033   |  9876543210 |
      | M, Kishore, Kumar | Male   | 10, November, 1998 | Near Icici bank,HitechCity,Hyderabad,Telanagana,India,500033 |  9876543211 |
      | CH, Kumar, Raju   | Male   | 17, July, 2002     | Near SBI bank,Kukatpally,Hyderabad,Telanagana,India,500033   |  9876543212 |

  @RegisterSinglePatient @SanityTest @RegressionTest
  Scenario: Register patient with patient deatils "<Name>"
    Given User is on the Home Page
    When User selects the Module "Register a Patient"
    Then Register a Patient page should be displayed
    When User enters patient details
      | Name            | Gender | DateOfBirth        | Address                                                    | PhoneNumber |
      | H, Ramesh, Babu | Male   | 26, December, 2000 | Near HDFC bank,S R Nagar,Hyderabad,Telanagana,India,500033 |  9876543210 |
    And user clicks next button
    Then user should able to verify entered details
    When user clicks confirm button
    Then user should be registered and patient Id must be genrated

  @RegisterSinglePatientDetails @RegressionTest
  Scenario: Register patient with patient deatils "<Name>"
    Given User is on the Home Page
    When User selects the Module "Register a Patient"
    Then Register a Patient page should be displayed
    When User enters patient details as "H, Ramesh, Babu", "Male", "26, December, 2000", "Near HDFC bank,S R Nagar,Hyderabad,Telanagana,India,500033", "9876543210"
    And user clicks next button
    Then user should able to verify entered details
    When user clicks confirm button
    Then user should be registered and patient Id must be genrated
