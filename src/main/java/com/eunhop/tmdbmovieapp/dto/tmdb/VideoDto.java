package com.eunhop.tmdbmovieapp.dto.tmdb;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class VideoDto {
  private int page;
  private int total_pages;
  private int total_results;
  private List<Results> results;

  @Getter
  public static class Results {
    private String backdrop_path;
    private Integer id;
    private String title;
    private String name;
    private String overview;
    private String tagline;
    private String poster_path;
    @Setter
    private String media_type;
    private List<Genre> genres;
    private List<Creator> created_by;
    private String release_date;
    private String first_air_date;
    private Float vote_average;
    @Setter
    private int score;

    @Getter
    public static class Genre {
      private Integer id;
      private String name;
    }

    @Getter
    public static class Creator {
      private String name;
      private String profile_path;
    }
  }
}
