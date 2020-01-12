Feature: Course End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Add new course
    Given  path '/course'
    And request { courseTypeTitle:"React",trainerEmail:"sogeti@email.com",title:"React experts",description:"Une formation React pour les experts",startDate:"2020-01-17",endDate:"2020-01-18" }
    When method POST
    Then status 201

  Scenario: Testing GET course by id
    Given  path '/course'
    And param id = 1
    When method GET
    Then status 200
    * def course = response
    And match course contains { title:"Initiation à Spring boot" }

  Scenario: Testing GET course by title
    Given  path '/course/title'
    And param title = 'Formation React'
    When method GET
    Then status 200
    * def course = response
    And match course contains { title:"Formation React" }

  Scenario: Testing GET all courses
    Given path 'course/all'
    When method GET
    Then status 200
    * def course1 = response[0]
    * def course2 = response[1]
    * def course3 = response[2]
    * def course4 = response[3]
    And match course1 contains { title:"Initiation à Spring boot" }
    And match course2 contains { title:"Formation React" }
    And match course3 contains { title:"Formation Angular" }
    And match course4 contains { title:"React experts" }

  Scenario: Testing GET all courses by type title
    Given path 'course/all/typetitle'
    And param typeTitle = 'React'
    When method GET
    Then status 200
    * def course1 = response[0]
    * def course2 = response[1]
    And match course1 contains { title:"Formation React" }
    And match course2 contains { title:"React experts" }

  Scenario: Testing GET all courses by trainer email
    Given path 'course/all/traineremail'
    And param trainerEmail = 'sogeti@email.com'
    When method GET
    Then status 200
    * def course1 = response[0]
    * def course2 = response[1]
    And match course1 contains { title:"Formation React" }
    And match course2 contains { title:"Formation Angular" }

  Scenario: Testing GET all courses by intern email
    Given path 'course/all/internemail'
    And param internEmail = 'said@email.com'
    When method GET
    Then status 200
    * def course1 = response[0]
    * def course2 = response[1]
    And match course1 contains { title:"Formation React" }
    And match course2 contains { title:"Formation Angular" }

  Scenario: Add new course KO response
    Given  path '/course'
    And request { courseTypeTitle:"React",trainerEmail:"sogeti@email.com",title:"React experts",description:"Une formation React pour les experts",startDate:"2020-01-17",endDate:"2020-01-18" }
    When method POST
    Then status 302
    And match $ == "Course already exists."

  Scenario: Testing Error GET all courses by type title
    Given path 'course/all/typetitle'
    And param typeTitle = 'unknown'
    When method GET
    Then status 404