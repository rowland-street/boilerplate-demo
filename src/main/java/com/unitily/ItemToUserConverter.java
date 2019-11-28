package com.unitily;

import com.amazonaws.services.dynamodbv2.document.Item;

public class ItemToUserConverter {
  public User execute(final Item item) {

    String username = item.getString("username");
    User user = new User(username);
    return user;
  }
}
