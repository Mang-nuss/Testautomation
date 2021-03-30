Feature: Login issues

    # n single test run using randomised inputs,
    # with various restrictions. All cases should pass.
  @Tag1
  Scenario Outline: Registration works
    Given I am up to register at website, using "<browser>"
    When I submit an email of <int> characters, using "<email-premise>"
    And I submit a username of <int> characters, using "<username-premise>"
    And I submit a password of <int> characters, using "<password-premise>"
    And I click on sign-in
    Then The registration is completed
    Examples:
      | browser | int | email-premise | username-premise | password-premise |
      | FF | 15 | none | lower+upper+int | random choice |
      #| FF | 20 | none | lower+int  | random choice |
      #| Safari | 25 | none | upper+int  | random choice |

    # The nrs of characters are set to 99, 100, and 101, to check boundary values
  @Tag2:
  Scenario Outline: Username contains too many characters
    Given I am up to register at website, using "<browser>"
    When I submit valid "<email>" as email and "<password>" as password
    But I submit a username too extensive, containing <int> characters
    And I click on sign-in
    #Then There is an error message saying that <int> characters are at least one too many
    Then If the nr <int> of characters is larger than 99, there is an error message
    Examples:
      | browser | email | password | int |
      #| FF | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 99 |
      #| Chrome | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 100 |
      | Chrome | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 100 |
      #| Safari | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 101 |
      #| Chrome | magjo063@student.liu.se | pynfet-1repXi-zadnyv | 101 |

    # my personal login data are used
  @Tag3:
  Scenario Outline: Username is taken
    Given I am up to register at website, using "Chrome"
    When I submit valid "<email>" as email and "<password>" as password
    But I submit a "<username>" that is taken since before
    And I click on sign-in
    Then There is an error saying username is taken
    Examples:
      | email | password | username |
      | johansson.magnus.86\"@gmail.com | pynfet-1repXi-zadnyv | johanssonmagnus86 |

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