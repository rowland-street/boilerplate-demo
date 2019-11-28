package com.unitily;

import com.unitily.config.Config;
import com.unitily.config.ConfigProvider;

public class UsernameLetterCountPublisher {
  private final ConfigProvider configProvider;

  private final UserIdProvider userIdProvider;

  private final UserProvider userProvider;

  private final UserMessageCreator userMessageCreator;

  private final Publisher publisher;

  public UsernameLetterCountPublisher(
      final ConfigProvider configProvider,
      final UserIdProvider userIdProvider,
      final UserProvider userProvider,
      final UserMessageCreator userMessageCreator,
      final Publisher publisher) {
    this.configProvider = configProvider;
    this.userIdProvider = userIdProvider;
    this.userProvider = userProvider;
    this.userMessageCreator = userMessageCreator;
    this.publisher = publisher;
  }

  public void execute() {
    final Config config = configProvider.execute();
    final String id = userIdProvider.execute(config);
    final User user = userProvider.execute(id);
    final String message = userMessageCreator.execute(user);
    publisher.execute(message);
  }
}
