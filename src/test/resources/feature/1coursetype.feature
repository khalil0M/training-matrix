Feature: Course Type End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Add new course type
    Given  path '/coursetype'
    And request { typeTitle:'AEM' }
    When method POST
    Then status 201

  Scenario: Testing GET course type by id
    Given  path '/coursetype'
    And param id = 1
    When method GET
    Then status 200
    * def courseType = response
    And match courseType contains {typeTitle:"Spring"}

  Scenario: Testing GET course type by title
    Given  path '/coursetype/title'
    And param typeTitle = 'React'
    When method GET
    Then status 200
    * def courseType = response
    And match courseType contains {typeTitle:"React"}

  Scenario: Testing GET all course types
    Given path 'coursetype/all'
    When method GET
    Then status 200
    * def courseType1 = response[0]
    * def courseType2 = response[1]
    * def courseType3 = response[2]
    * def courseType4 = response[3]
    And match courseType1 contains {typeTitle:"Spring"}
    And match courseType2 contains {typeTitle:"React"}
    And match courseType3 contains {typeTitle:"Angular"}
    And match courseType4 contains {typeTitle:"AEM"}

  Scenario: Add new course type KO response
    Given  path '/coursetype'
    And request { typeTitle:'AEM' }
    When method POST
    Then status 302
    And match $ == "Course type already exists."

  Scenario: Testing Error GET course type by title
    Given path 'coursetype/title'
    And param typeTitle = 'unknown'
    When method GET
    Then status 404