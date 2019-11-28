package com.unitily;

import com.unitily.config.Config;

public class UserIdProvider {
  public String execute(final Config config) {

    return config.userId;
  }
}
