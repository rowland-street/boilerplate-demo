package com.unitily;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unitily.config.Config;
import com.unitily.config.ConfigProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsernameLetterCountPublisherTest {
  /** Variables */
  private static final String userId;

  private static final User user;

  private static final Config config;

  private static final String message;

  /** Mocked dependencies */
  private ConfigProvider configProvider;

  private UserProvider userProvider;

  private Publisher publisher;

  /** Test fixture */
  private UsernameLetterCountPublisher usernameLetterCountPublisher;

  static {
    userId = "aUserId";
    user = new User("aUsersName");
    config = new Config(null, userId);
    message = "aUsersName has 10 letters in his/her name";
  }

  @BeforeEach
  public void setupTestFixture() {
    configProvider = mock(ConfigProvider.class);
    userProvider = mock(UserProvider.class);
    publisher = mock(Publisher.class);

    usernameLetterCountPublisher =
        new UsernameLetterCountPublisher(
            configProvider,
            new UserIdProvider(),
            userProvider,
            new UserMessageCreator(),
            publisher);
  }

  @Test
  public void test1() {
    when(configProvider.execute()).thenReturn(config);
    when(userProvider.execute(userId)).thenReturn(user);

    usernameLetterCountPublisher.execute();
    verify(publisher).execute(message);
  }
}
