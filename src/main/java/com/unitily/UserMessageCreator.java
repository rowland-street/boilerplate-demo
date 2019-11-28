package com.unitily;

public class UserMessageCreator {
  public String execute(final User user) {

    String message =
        String.format("%s has %d letters in his/her name", user.username, user.username.length());
    return message;
  }
}
