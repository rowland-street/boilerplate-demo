package com.unitily.startup;

import com.unitily.ConsolePrinter;
import com.unitily.DynamoDbUserProvider;
import com.unitily.ItemToUserConverter;
import com.unitily.Publisher;
import com.unitily.User;
import com.unitily.UserIdProvider;
import com.unitily.UserMessageCreator;
import com.unitily.UserProvider;
import com.unitily.UserTableProvider;
import com.unitily.UsernameLetterCountPublisher;
import com.unitily.aws.dynamo.DynamoClientProvider;
import com.unitily.aws.dynamo.DynamoItemReader;
import com.unitily.config.ConfigProvider;
import com.unitily.config.EnvironmentProvider;

public class Main {
  public static void main(String[] args) {
    new UsernameLetterCountPublisher(
            new ConfigProvider(new EnvironmentProvider()),
            new UserIdProvider(),
            new UserProvider() {
              private final DynamoDbUserProvider dynamoDbUserProvider =
                  new DynamoDbUserProvider(
                      new ConfigProvider(new EnvironmentProvider()),
                      new UserTableProvider(),
                      new DynamoItemReader(new DynamoClientProvider()),
                      new ItemToUserConverter());

              public User execute(final String id) {
                return dynamoDbUserProvider.execute(id);
              }
            },
            new UserMessageCreator(),
            new Publisher() {
              private final ConsolePrinter consolePrinter = new ConsolePrinter();

              public void execute(final String x) {
                consolePrinter.execute(x);
              }
            })
        .execute();
  }
}
