package com.unitily;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserMessageCreatorTest {
  /** Variables */
  private static final User user;

  private static final String message;

  /** Test fixture */
  private UserMessageCreator userMessageCreator;

  static {
    final String username = "aUsersName";
    user = new User(username);
    message = "aUsersName has 10 letters in his/her name";
  }

  @BeforeEach
  public void setupTestFixture() {

    userMessageCreator = new UserMessageCreator();
  }

  @Test
  public void test1() {

    assertEquals(
        message,
        userMessageCreator.execute(user),
        "should create a message containing the number of characters in the username\n");
  }
}
