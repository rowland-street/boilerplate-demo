package com.unitily.aws.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.*;
import java.util.Collections;
import java.util.List;

public class LocalDynamoDbProvider {
  public final AmazonDynamoDB dynamoDB;

  static {
    System.setProperty("sqlite4java.library.path", "target/native-libs");
  }

  private LocalDynamoDbProvider(final AmazonDynamoDB dynamoDB) {
    this.dynamoDB = dynamoDB;
  }

  private static LocalDynamoDbProvider createWithTable(CreateTableRequest createTableRequest) {
    final AmazonDynamoDB client = DynamoDBEmbedded.create().amazonDynamoDB();
    client.createTable(createTableRequest);
    return new LocalDynamoDbProvider(client);
  }

  private static CreateTableRequest getCreateTableRequest(String tableName, String idFieldName) {
    final List<AttributeDefinition> definitions =
        Collections.singletonList(
            new AttributeDefinition()
                .withAttributeName("id")
                .withAttributeType(ScalarAttributeType.S));

    return new CreateTableRequest()
        .withTableName(tableName)
        .withKeySchema(new KeySchemaElement(idFieldName, "HASH"))
        .withAttributeDefinitions(definitions)
        .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
  }

  static LocalDynamoDbProvider create(
      final String tableName, final String idFieldName, final List<Item> items) {
    final CreateTableRequest createTableRequest = getCreateTableRequest(tableName, idFieldName);

    final LocalDynamoDbProvider result = LocalDynamoDbProvider.createWithTable(createTableRequest);

    final Table table = new DynamoDB(result.dynamoDB).getTable(tableName);
    items.forEach(table::putItem);

    return result;
  }
}
