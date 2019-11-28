package com.unitily;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.amazonaws.services.dynamodbv2.document.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemToUserConverterTest {
  /** Variables */
  private static final Item item;

  private static final User user;

  /** Test fixture */
  private ItemToUserConverter itemToUserConverter;

  static {
    final String username = "aUsersName";
    item = new Item().withString("username", username);
    user = new User(username);
  }

  @BeforeEach
  public void setupTestFixture() {

    itemToUserConverter = new ItemToUserConverter();
  }

  @Test
  public void test1() {

    assertEquals(
        user,
        itemToUserConverter.execute(item),
        "Should convert a Dynamo SDK item to a User entity object.");
  }
}
