Feature: Login issues

  @Tag1
  Scenario Outline: Login works
    Given I am up to register at website
    When I submit an <email>
    And I submit a <username>
    And I submit a <password>
    And I click on sign-in
    Then The registration is completed
    Examples:
      | email | username | password |
      | mail  | usr      | pwd      |

  @Tag2:
  Scenario Outline: Username contains too many characters
    Given I am up to register at website
    When I submit valid <email> and <password>
    But I submit a <username> too extensive
    And I click on sign-in
    Then There is an error
    Examples:
      | email | password | username |
      | mail  | usr      | pwd      |

  @Tag3:
  Scenario Outline: Username is taken
    Given I am up to register at website
    When I submit valid <email> and <password>
    But I submit a <username> that is taken since before
    And I click on sign-in
    Then There is an error
    Examples:
      | email | password | username |
      | mail  | usr      | pwd      |

  @Tag4:
  Scenario Outline: Email is missing
    Given I am up to register at website
    When I submit valid <username> and <password>
    But I forget to submit an <email>
    And I click on sign-in
    Then There is an error
    Examples:
      | username | password | email |
      | mail  | usr      | pwd      |
