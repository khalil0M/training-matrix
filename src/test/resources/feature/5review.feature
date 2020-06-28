Feature: Review End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Add new review
    Given  path '/review'
    And request { internId:4,courseId:2,createdOn:'2020-01-02',score:5 }
    When method POST
    Then status 201

  Scenario: Testing GET review by id
    Given  path '/review'
    And param internId = 1
    And param courseId = 2
    When method GET
    Then status 200
    * def review = response
    And match review contains {internId:1,courseId:2}

  Scenario: Testing GET all reviews by score
    Given  path '/review/all/score'
    And param score = 5
    When method GET
    Then status 200
    * def review1 = response[0]
    * def review2 = response[1]
    * def review3 = response[2]
    And match review1 contains {score:5}
    And match review2 contains {score:5}
    And match review3 contains {score:5}

  Scenario: Testing GET all reviews by course title
    Given  path '/review/all/coursetitle'
    And param courseTitle = 'Initiation à Spring boot'
    When method GET
    Then status 200
    * def review1 = response[0]
    * def review2 = response[1]
    And match review1 contains {courseTitle:'Initiation à Spring boot'}
    And match review2 contains {courseTitle:'Initiation à Spring boot'}

  Scenario: Testing GET all reviews by intern email
    Given  path '/review/all/internemail'
    And param internEmail = 'said@email.com'
    When method GET
    Then status 200
    * def review1 = response[0]
    * def review2 = response[1]
    And match review1 contains {internEmail:'said@email.com'}
    And match review2 contains {internEmail:'said@email.com'}

  Scenario: Testing GET all reviews
    Given path 'review/all'
    When method GET
    Then status 200
    * def review1 = response[0]
    * def review2 = response[1]
    * def review3 = response[2]
    * def review4 = response[3]
    * def review5 = response[4]
    * def review6 = response[5]
    And match review1 contains {courseId:1,internId:2}
    And match review2 contains {courseId:1,internId:3}
    And match review3 contains {courseId:2,internId:1}
    And match review4 contains {courseId:2,internId:4}
    And match review5 contains {courseId:3,internId:1}
    And match review6 contains {courseId:3,internId:4}

  Scenario: Add new review KO response
    Given  path '/review'
    And request { internId:4,courseId:2,createdOn:'2019-01-02',score:1 }
    When method POST
    Then status 302
    And match $ == "Review already exists."

  Scenario: Testing Error GET all reviews by score
    Given path 'review/all/score'
    And param score = -1
    When method GET
    Then status 404