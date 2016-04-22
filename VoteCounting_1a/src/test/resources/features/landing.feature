Feature: landing page 
  Scenario: client makes call to GET /
    When the client calls http://aswneo.azurewebsites.net
    Then the client receives status code of 200
    And the client receives the string "Resultados"