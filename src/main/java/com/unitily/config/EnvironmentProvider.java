package com.unitily.config;

public class EnvironmentProvider {
  public String execute() {
    return System.getenv("Environment");
  }
}
