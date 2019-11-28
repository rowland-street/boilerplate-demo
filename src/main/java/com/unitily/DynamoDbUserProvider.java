package com.unitily;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.unitily.aws.dynamo.DynamoItemReader;
import com.unitily.aws.dynamo.DynamoTable;
import com.unitily.config.Config;
import com.unitily.config.ConfigProvider;

public class DynamoDbUserProvider {
  private final ConfigProvider configProvider;

  private final UserTableProvider userTableProvider;

  private final DynamoItemReader dynamoReader;

  private final ItemToUserConverter itemToUserConverter;

  public DynamoDbUserProvider(
      final ConfigProvider configProvider,
      final UserTableProvider userTableProvider,
      final DynamoItemReader dynamoReader,
      final ItemToUserConverter itemToUserConverter) {
    this.configProvider = configProvider;
    this.userTableProvider = userTableProvider;
    this.dynamoReader = dynamoReader;
    this.itemToUserConverter = itemToUserConverter;
  }

  public User execute(final String id) {
    final Config config = configProvider.execute();
    final DynamoTable dynamoTable = userTableProvider.execute(config);
    final Item item = dynamoReader.execute(dynamoTable, id);
    final User user = itemToUserConverter.execute(item);
    return user;
  }
}
