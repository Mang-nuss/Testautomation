Feature: Login issues

  @Tag1
  Scenario Outline: Registration works
    Given I am up to register at website
    When I submit "<email>" as email
    And I submit "<username>" as username
    And I submit "<password>" as password
    And I click on sign-in
    Then The registration is completed
    Examples:
      | email | username | password |
      | johansson.magnus.86@gmail.com | johanssonmagnus86 | pynfet-1repXi-zadnyv |
