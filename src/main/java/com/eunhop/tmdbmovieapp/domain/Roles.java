package com.eunhop.tmdbmovieapp.domain;

import lombok.Getter;

public enum Roles {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER"),
  USER_WITHOUT_WRITE("ROLE_USER_WITHOUT_WRITE");

  @Getter
  private final String value;

  Roles(String value) {
    this.value = value;
  }
}
