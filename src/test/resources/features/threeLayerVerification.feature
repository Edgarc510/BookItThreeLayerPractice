Feature: Three layer verification
#Started Jan 4 Cafe4
  Scenario: Get the info from UI
    Given User logs in with "wcanadinea@ihg.com" and "waverleycanadine"
    When User navigates to mySelf page
    Then User gets the UI info

  Scenario: Get info from API
    When User logs in BookIt API using "wcanadinea@ihg.com" and "waverleycanadine"
    Then User gets API information


  Scenario:Get the info from DB
    When User sends a query to database with "wcanadinea@ihg.com"
    Then User gets DataBase information


  @db @wip
  Scenario:
    Given User logs in with "wcanadinea@ihg.com" and "waverleycanadine"
    When User navigates to mySelf page
    Then User gets the UI info
    When User logs in BookIt API using "wcanadinea@ihg.com" and "waverleycanadine"
    Then User gets API information
    When User sends a query to database with "wcanadinea@ihg.com"
    Then User gets DataBase information