package com.unitily;

import com.unitily.aws.dynamo.DynamoTable;
import com.unitily.config.Config;

public class UserTableProvider {
  public DynamoTable execute(final Config config) {

    return config.userTable;
  }
}
