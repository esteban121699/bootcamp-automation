Feature: Search Yelp

  Background:

    Given user navigates to https://www.yelp.com/
    And selects restaurants

  Scenario Outline: detail of the first restaurant search result

    Given user searches by <food>
    When filters by <distance>
    And selects the first result
    Then views restaurant information

    Examples:
      |food|distance|
      |Sushi|Biking (2 mi.)|
