package edu.ufp.inf.en.lp2._1_intro.videoclub;

import java.util.ArrayList;

public class Genre {

  private String name;
  private ArrayList<Movie> movies = new ArrayList<>();

  public Genre (String name) {
    this.name = name;
  }

  public void addMovie(Movie m) {
    this.movies.add(m);
  }

  public void removeMovie(Movie m) {
    this.movies.remove(m);
  }

  public ArrayList<Movie> listMovie(Movie m) {
    for (Movie movie : movies) {
      System.out.println(movie);
    }
    return this.movies;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}