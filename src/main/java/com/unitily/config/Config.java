package com.unitily.config;

import com.unitily.aws.dynamo.DynamoTable;
import java.util.Objects;

public class Config {
  public final DynamoTable userTable;

  public final String userId;

  public Config(final DynamoTable userTable, final String userId) {
    this.userTable = userTable;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Config config = (Config) o;
    return Objects.equals(userTable, config.userTable) && Objects.equals(userId, config.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userTable, userId);
  }

  @Override
  public String toString() {
    return "Config{" + " userTable=" + userTable + " userId=" + userId + "}";
  }
}
