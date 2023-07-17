Feature: Registration
  Register a new user.
  
  Scenario: Register user
    Given a web browser is launched with the given url
    When values for 'user', 'email address' and 'password' are entered
    Then the user is registered and also logged in