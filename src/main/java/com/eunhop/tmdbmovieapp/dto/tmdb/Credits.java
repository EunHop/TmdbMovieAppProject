package com.eunhop.tmdbmovieapp.dto.tmdb;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Credits {
  private int id;
  private List<Person> cast;
  private List<Person> crew;

  @Getter
  public static class Person {
    private Integer gender;
    private String known_for_department;
    private String name;
    private String profile_path;
    private List<Role> roles;
    private List<Job> jobs;
    @Setter
    private String character;
    private Integer order;
    private String department;
    @Setter
    private String job;

    @Getter
    public static class Role {
      private String character;
      private Integer episode_count;
    }

    @Getter
    public static class Job {
      private String job;
      private Integer episode_count;
    }
  }

}
