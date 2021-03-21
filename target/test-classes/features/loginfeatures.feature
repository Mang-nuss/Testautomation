Feature: Login issues

    # n single test run using randomised inputs,
    # with various restrictions. All cases should pass.
  @Tag1
  Scenario Outline: Registration works
    Given I am up to register at website
    When I submit an email using "<email-premise>"
    And I submit a username using "<username-premise>"
    And I submit a password using "<password-premise>"
    And I click on sign-in
    Then The registration is completed
    Examples:
      | email-premise | username-premise | password-premise |
      | no email suffix | lower+upper+int | random choice |
      | twisted email suffix | lower+int  | random choice |
      | taken email          | upper+int  | random choice |

    # The nrs of characters are set to 99, 100, and 101, to check bound values
  @Tag2:
  Scenario Outline: Username contains too many characters
    Given I am up to register at website
    When I submit valid "<email>" as email and "<password>" as password
    But I submit a username too extensive, containing <int> characters
    And I click on sign-in
    Then There is an error message saying that <int> characters are at least one too many
    Examples:
      | email | password | int |
      | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 99 |
      | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 100 |
      | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 101 |

    # my personal login data are used
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
    Then There is an error saying that email is missing
    Examples:
      | username | password |
      | johanssonmagnus86 | pynfet-1repXi-zadnyv |