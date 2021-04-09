Feature: Sign-in issues

  Scenario Outline: Signing in
    Given I am up to register at website, using "<browser>"
    And I submit "<email>" as email
    And I submit "<username>" as username
    And I submit "<password>" as password
    When I click on sign-in
    Then Input data should generate "<result>"

    Examples:
      | browser | email | username | password | result |
      | FF | a randomised address | a randomised username of uppercases, lowercases, and ints | a randomised password | success |
      | Chrome | a randomised address | an overlong username | pynfet-1repXi-zadnyv | overlong username error |
      | Safari | a randomised address | a username already in use | pynfet-1repXi-zadnyv | existing username error |
      | Chrome | nothing | magnusjohansson86 | pynfet-1repXi-zadnyv | missing email error |