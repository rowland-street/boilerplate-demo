package com.unitily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.unitily.aws.dynamo.DynamoItemReader;
import com.unitily.aws.dynamo.DynamoTable;
import com.unitily.config.Config;
import com.unitily.config.ConfigProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamoDbUserProviderTest {
  /** Variables */
  private static final Item item;

  private static final User user;

  private static final String userId;

  private static final DynamoTable table;

  private static final Config config;

  /** Mocked dependencies */
  private ConfigProvider configProvider;

  private DynamoItemReader dynamoReader;

  /** Test fixture */
  private DynamoDbUserProvider dynamoDbUserProvider;

  static {
    final String username = "aUsersName";
    item = new Item().withString("username", username);
    user = new User(username);
    userId = "aUserId";
    table = new DynamoTable("region", "table", "idField");
    config = new Config(table, null);
  }

  @BeforeEach
  public void setupTestFixture() {
    configProvider = mock(ConfigProvider.class);
    dynamoReader = mock(DynamoItemReader.class);

    dynamoDbUserProvider =
        new DynamoDbUserProvider(
            configProvider, new UserTableProvider(), dynamoReader, new ItemToUserConverter());
  }

  @Test
  public void test1() {
    when(configProvider.execute()).thenReturn(config);
    when(dynamoReader.execute(table, userId)).thenReturn(item);

    assertEquals(
        user,
        dynamoDbUserProvider.execute(userId),
        "Should return a user created from the Item returned from Dynamo DB.");
  }
}
