Feature: Login
  Login user.
  
  Scenario: User login
    Given a web browser is launched with the given url and user
    When values for 'email address' and 'password' are entered
    Then the user should be logged in