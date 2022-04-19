package edu.ufp.inf.en.lp2._1_intro.videoclub;

import java.util.ArrayList;


public class Actor {

  private String name;
  private ArrayList<Movie> movies = new ArrayList<>();

  public Actor (String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void addMovie(Movie movie) {
    this.movies.add(movie);
  }

  public void removeMovie(Movie m) {
    this.movies.remove(m);
  }

  public Movie getMovie (Movie m) {
    for (Movie movie : movies) {
      if (movie.equals(m)) {
        return movie;
      }
    }
    return null;
  }

  public ArrayList<Movie> getMovies() {
    return this.movies;
  }

  public ArrayList<Movie> listMovies() {
    for (Movie movie : movies) {
      System.out.print(movie.getTitle() + " ");
    }
    return this.movies;
  }

  @Override
  public String toString() {
      return "Actor: " + this.name + "\n" + "Movies: " + this.movies;
  }

}