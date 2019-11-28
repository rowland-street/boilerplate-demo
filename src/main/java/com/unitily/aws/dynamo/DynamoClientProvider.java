package com.unitily.aws.dynamo;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import java.util.HashMap;
import java.util.Map;

public class DynamoClientProvider {
  private static final Map<String, AmazonDynamoDB> amazonDynamoClients = new HashMap<>();

  public AmazonDynamoDB get(final String region) {
    return amazonDynamoClients.computeIfAbsent(region, this::createClient);
  }

  private AmazonDynamoDB createClient(String region) {
    return AmazonDynamoDBClientBuilder.standard()
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .withRegion(region)
        .build();
  }
}
