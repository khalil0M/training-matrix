Feature: Trainer End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Add new trainer
    Given  path '/trainer'
    And request { name:'DUPONT',address:'Lyon',phone:'+33512345680',email:'dupont2@email.com' }
    When method POST
    Then status 201

  Scenario: Testing GET trainer by id
    Given  path '/trainer'
    And param id = 1
    When method GET
    Then status 200
    * def trainer = response
    And match trainer contains {email:"sogeti@email.com"}

  Scenario: Testing GET trainer by email
    Given  path '/trainer/email'
    And param email = 'dupont@email.com'
    When method GET
    Then status 200
    * def trainer = response
    And match trainer contains {email:"dupont@email.com"}

  Scenario: Testing GET trainer by phone
    Given  path '/trainer/phone'
    And param phone = '+33512345679'
    When method GET
    Then status 200
    * def trainer = response
    And match trainer contains {email:"dupont@email.com"}

  Scenario: Testing GET all trainers
    Given path 'trainer/all'
    When method GET
    Then status 200
    * def trainer1 = response[0]
    * def trainer2 = response[1]
    * def trainer3 = response[2]
    And match trainer1 contains {email:"sogeti@email.com"}
    And match trainer2 contains {email:"dupont@email.com"}
    And match trainer3 contains {email:"dupont2@email.com"}

  Scenario: Testing GET all trainers by name
    Given path 'trainer/all/name'
    And param name = 'DUPONT'
    When method GET
    Then status 200
    * def trainer1 = response[0]
    * def trainer2 = response[1]
    And match trainer1 contains {email:"dupont@email.com"}
    And match trainer2 contains {email:"dupont2@email.com"}

  Scenario: Add new trainer KO response
    Given  path '/trainer'
    And request { name:'DUBOIS',address:'METZ',phone:'+33512345681',email:'dupont2@email.com' }
    When method POST
    Then status 302
    And match $ == "Trainer already exists."

  Scenario: Testing Error GET all trainers by name
    Given path 'trainer/all/name'
    And param name = 'unknown'
    When method GET
    Then status 404