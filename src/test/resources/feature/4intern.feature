Feature: Intern End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Add new intern
    Given  path '/intern'
    And request { emailPerson:'karim@email.com' }
    When method POST
    Then status 201

  Scenario: Testing GET intern by id
    Given  path '/intern'
    And param id = 1
    When method GET
    Then status 200
    * def intern = response
    And match intern contains {emailPerson:"said@email.com"}

  Scenario: Testing GET intern by email
    Given  path '/intern/email'
    And param email = 'karim@email.com'
    When method GET
    Then status 200
    * def intern = response
    And match intern contains {emailPerson:"karim@email.com"}

  Scenario: Testing GET all interns
    Given path 'intern/all'
    When method GET
    Then status 200
    * def intern1 = response[0]
    * def intern2 = response[1]
    * def intern3 = response[2]
    * def intern4 = response[3]
    * def intern5 = response[4]
    And match intern1 contains {emailPerson:"said@email.com"}
    And match intern2 contains {emailPerson:"youcef@email.com"}
    And match intern3 contains {emailPerson:"mohamed@email.com"}
    And match intern4 contains {emailPerson:"paul@email.com"}
    And match intern5 contains {emailPerson:"karim@email.com"}

  Scenario: Testing GET all interns by course title
    Given path 'intern/all/coursetitle'
    And param courseTitle = 'Formation Angular'
    When method GET
    Then status 200
    * def intern1 = response[0]
    * def intern2 = response[1]
    And match intern1 contains {emailPerson:"said@email.com"}
    And match intern2 contains {emailPerson:"paul@email.com"}

  Scenario: Add new intern KO response
    Given  path '/intern'
    And request { emailPerson:'karim@email.com' }
    When method POST
    Then status 302
    And match $ == "Intern already exists."

  Scenario: Testing Error GET all interns by course title
    Given path 'intern/all/coursetitle'
    And param courseTitle = 'unknown'
    When method GET
    Then status 404