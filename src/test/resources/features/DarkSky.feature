@darkSky
Feature: darksky testing

  Background:
    Given I am on Darksky home page

@darkskyTempTimeline
Scenario: Verify timline is displayed in correct format
Given I am on Darksky home page
Then I verify timeline is displayed with two hours incremented


@darkskyMaxMinTempVerify
  Scenario: Verify individual day temp timeline
    When I expand todays timeline
    Then I verify lowest and highest temp is displayed correctly

@tempVerification
  Scenario: Verify Current Temperature should not be greater or less than the Temperature from Daily Timeline
   Then I verify current temp is not greater or less then temps from daily timeline

