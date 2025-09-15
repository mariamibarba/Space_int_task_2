Feature: Users CRUD on ReqRes

  Background:
    Given the API base URL is configured
    And I set header "x-api-key" to "reqres-free-v1"

  Scenario: GET list users (page=2)
    When I send GET "/api/users" with query params:
      | page | 2 |
    Then the response status should be 200
    And the response header "Content-Type" should contain "application/json"
    And the response body path "page" should equal 2
    And the response body path "data.size()" should be > 0

  Scenario: GET single user id=2
    When I send GET "/api/users/2"
    Then the response status should be 200
    And the response body path "data.id" should equal 2
    And the response body path "data.email" should contain "@reqres.in"

  Scenario: GET single user not found (id=23)
    When I send GET "/api/users/23"
    Then the response status should be 404
    And the response body should be empty

  Scenario: POST create user
    Given the request JSON body:
      """
      { "name": "string123", "job": "leader" }
      """
    When I send POST "/api/users"
    Then the response status should be 201
    And the response body path "name" should equal "string123"
    And the response body should have keys:
      | id        |
      | createdAt |

  Scenario: PUT update user id=2
    Given the request JSON body:
      """
      { "name": "Charles", "job": "QA Lead" }
      """
    When I send PUT "/api/users/2"
    Then the response status should be 200
    And the response body path "name" should equal "Charles"
    And the response body path "job" should equal "QA Lead"
    And the response body should have keys:
      | updatedAt |

  Scenario: PATCH update user id=2
    Given the request JSON body:
      """
      { "job": "Automation" }
      """
    When I send PATCH "/api/users/2"
    Then the response status should be 200
    And the response body path "job" should equal "Automation"
    And the response body should have keys:
      | updatedAt |

  Scenario: DELETE user id=2
    When I send DELETE "/api/users/2"
    Then the response status should be 204
    And the response body should be empty
