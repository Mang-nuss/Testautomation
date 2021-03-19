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

  @Tag3:
  Scenario Outline: Username is taken
    Given I am up to register at website
    When I submit valid "<email>" as email and "<password>" as password
    But I submit a "<username>" that is taken since before
    And I click on sign-in
    Then There is an error
    Examples:
      | email | username | password |
      | johansson.magnus.86@gmail.com | johanssonmagnus86 | pynfet-1repXi-zadnyv |

  @Tag4:
  Scenario Outline: Email is missing
    Given I am up to register at website
    When I submit valid "<username>" as username and "<password>" as password
    But I forget to submit an email
    And I click on sign-in
    Then There is an error
    Examples:
      | username | password |
      | johanssonmagnus86 | pynfet-1repXi-zadnyv |