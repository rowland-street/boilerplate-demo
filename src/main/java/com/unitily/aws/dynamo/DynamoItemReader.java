package com.unitily.aws.dynamo;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;

public class DynamoItemReader {

  private final DynamoClientProvider dynamoClientProvider;

  public DynamoItemReader(final DynamoClientProvider dynamoClientProvider) {
    this.dynamoClientProvider = dynamoClientProvider;
  }

  public Item execute(final DynamoTable dynamoTable, final String id) {
    return new DynamoDB(dynamoClientProvider.get(dynamoTable.region))
        .getTable(dynamoTable.tableName)
        .getItem(dynamoTable.idFieldName, id);
  }
}
