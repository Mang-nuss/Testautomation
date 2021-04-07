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
      | FF | a randomised address | a randomised username of uppercases, lowercases, and ints | a randomised password |
      | Chrome | a randomised address | an overlong username | pynfet-1repXi-zadnyv |
      | Safari | a randomised address | a username already in use | pynfet-1repXi-zadnyv |
      | Chrome | nothing | magnusjohansson86 | pynfet-1repXi-zadnyv |