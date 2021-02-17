Feature: Flight Search
  As a user,
    I should be able to search flight
        so that I can find multiple flight results that first one shows my entered results.

  Scenario Outline: searching flights
    Given flight search page of kayak web site
    And name of the cities and dates
    When user enters the  "<originCity>" and "<destinationCity>" cities
    And chooses nearby airports
    And selects "<departureDate>" and "<arrivalDate>" for departure and arrivals:
    * clicks on search button
    * selects "<originCity>" and "<destinationCity>" one airport from available options
    Then user should be able to see the search result that contains entered cities "<originCity>" "<destinationCity>"


    Examples: cities and dates
      | originCity | destinationCity | departureDate | arrivalDate |
      | San Diego  | San Francisco   | February 28   | March 21    |
#      | Oakland    | Las Vegas       | February 25    | March 29   |
