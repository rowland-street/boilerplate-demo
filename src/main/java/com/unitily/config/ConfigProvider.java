package com.unitily.config;

import com.typesafe.config.ConfigFactory;
import com.unitily.aws.dynamo.DynamoTable;

public class ConfigProvider {
  private final EnvironmentProvider environmentProvider;

  public ConfigProvider(final EnvironmentProvider environmentProvider) {
    this.environmentProvider = environmentProvider;
  }

  public Config execute() {
    final com.typesafe.config.Config config = ConfigFactory.load();
    final String environment = environmentProvider.execute();
    final DynamoTable userTable =
        new DynamoTable(
            config.getString(String.format("%s.userTable.region", environment)),
            config.getString(String.format("%s.userTable.tableName", environment)),
            config.getString(String.format("%s.userTable.idFieldName", environment)));
    final String userId = config.getString(String.format("%s.userId", environment));
    return new Config(userTable, userId);
  }
}
