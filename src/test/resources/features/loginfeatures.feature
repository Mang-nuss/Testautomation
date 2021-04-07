Feature: Sign-in issues

  Scenario Outline:
    Given I am up to register at website, using "<browser>"
    And I submit "<email>" as email
    And I submit "<username>" as username
    And I submit "<password>" as password
    When I click on sign-in
    Then If the requirements are met, the registration is completed
    Examples:
      | browser | email | username | password |
      | FF | a randomised address | a randomised password of uppercases, lowercases, and ints | a randomised password |
      | Chrome | random email        | an overlong username | pynfet-1repXi-zadnyv |
      | Safari | random email        | a username already in use | pynfet-1repXi-zadnyv |
      | Chrome | nothing             | magnusjohansson86         | pynfet-1repXi-zadnyv |