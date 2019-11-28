package com.unitily.aws.dynamo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.services.dynamodbv2.document.Item;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DynamoItemReaderTest {

  // Values
  private static final String tableName;
  private static final String idFieldName;
  private static final String region;
  private static final DynamoTable dynamoTable;
  private static final String recordId;
  private static final Item expectedItem;

  static {
    tableName = "aTableName";
    idFieldName = "id";
    region = "aRegion";
    dynamoTable = new DynamoTable(region, tableName, idFieldName);
    recordId = "aRecordId";
    expectedItem = new Item().withString(idFieldName, recordId).withString("name", "a record name");
  }

  // Resources
  private static LocalDynamoDbProvider localDynamoDbProvider;

  @BeforeAll
  static void setupResources() {
    localDynamoDbProvider =
        LocalDynamoDbProvider.create(
            tableName,
            idFieldName,
            Arrays.asList(
                new Item().withString(idFieldName, "record-0").withString("name", "record 0"),
                expectedItem,
                new Item().withString(idFieldName, "record-2").withString("name", "record 2")));
  }

  @AfterAll
  static void tearDownResources() {
    localDynamoDbProvider.dynamoDB.shutdown();
  }

  // Mocked dependencies
  private DynamoClientProvider dynamoClientProvider;

  // Test Fixture
  private DynamoItemReader dynamoRecordById;

  @BeforeEach
  void setupTestFixture() {
    dynamoClientProvider = mock(DynamoClientProvider.class);
    dynamoRecordById = new DynamoItemReader(dynamoClientProvider);
  }

  @Test
  void test1() {
    when(dynamoClientProvider.get(region)).thenReturn(localDynamoDbProvider.dynamoDB);

    assertEquals(
        dynamoRecordById.execute(dynamoTable, recordId),
        expectedItem,
        "Get record by id should return the correct item");
  }
}
