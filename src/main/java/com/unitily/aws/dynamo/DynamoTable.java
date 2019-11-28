package com.unitily.aws.dynamo;

import java.util.Objects;

public class DynamoTable {
  public final String region;
  public final String tableName;
  public final String idFieldName;

  public DynamoTable(final String region, final String tableName, final String idFieldName) {
    this.region = region;
    this.tableName = tableName;
    this.idFieldName = idFieldName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DynamoTable that = (DynamoTable) o;
    return Objects.equals(region, that.region)
        && Objects.equals(tableName, that.tableName)
        && Objects.equals(idFieldName, that.idFieldName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(region, tableName, idFieldName);
  }

  @Override
  public String toString() {
    return "DynamoTable{"
        + "region='"
        + region
        + '\''
        + ", tableName='"
        + tableName
        + '\''
        + ", idFieldName='"
        + idFieldName
        + '\''
        + '}';
  }
}
