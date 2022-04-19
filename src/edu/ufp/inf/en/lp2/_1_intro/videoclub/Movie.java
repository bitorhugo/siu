package edu.ufp.inf.en.lp2._1_intro.videoclub;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Date;

public class Movie {

  private String title;
  private Date year;
  private ArrayList<Actor> actors = new ArrayList<>();
  private ArrayList<Genre> genres = new ArrayList<>();

  /**
   * Movie Constructor
   * @param title movie title
   * @param year movie year
   * @param actors ArrayList of actors present in movie
   * @param genres ArrayList of genres present in movie
   */
  public Movie (String title, Date year, ArrayList<Actor> actors, ArrayList<Genre> genres) {
    this.title = title;
    this.year = year;
    this.actors = actors;
    this.genres = genres;
  }

  /**
   * Movie Constructor
   * @param title movie title
   */
  public Movie (String title) {
    this.title = title;
  }

  /**
   * Set the title for Movie
   * @param title title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the title of Movie
   * @return title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the year of Movie
   * @param year Movie year
   */
  public void setYear(Date year) {
    this.year = year;
  }

  /**
   * Returns the date of Movie
   * @return Movie date
   */
  public Date getYear() {
    return this.year;
  }
  
  /**
   * Adds Actor to Movie
   * @param a Actor
   * @return true if able to add || false if not able to add
   */
  public boolean addActor(Actor a) {
    return this.actors.add(a);
  }

  /**
   * removes Actor from Movie
   * @param a Actor to remove
   * @return true if able to remove || false if not able to remove Actor
   */
  public boolean removeActor(Actor a) {
    return this.actors.remove(a);
  }

  /**
   * Checks if Movie contains a certain Actor
   * @param a Actor to search
   * @return true if contains || false if doesn't contain
   */
  public boolean containsActor(Actor a) {
    return this.actors.contains(a);
  }

  /**
   * Return a certain Actor from Movie
   * @param a Actor to return
   * @return Actor
   */
  public Actor getActor (Actor a) {
      for (Actor actor : actors) {
        if (actor.equals(a)) {
          return actor;
        }
      }
    return null;
  }

  /**
   * Return ArrayList of Actors of Movie
   * @return Actors ArrayList
   */
  public ArrayList<Actor> getActors () {
    return this.actors;
  }

  /**
   * Addas Genre to Movie
   * @param g Genre to add
   * @return true if able to add || false if not able to add
   */
  public boolean addGenre (Genre g) {
    return this.genres.add(g);
  }

  /**
   * Removes Genre from Movie
   * @param g Genre
   * @return true if able to remove || false if not able to remove
   */
  public boolean removeGenre (Genre g) {
    return this.genres.remove(g);
  }

  /**
   * Checks if Movie contains a certain Genre
   * @param g Genre to search
   * @return true if contains || false if doesn't contain
   */
  public boolean containsGenre (Genre g) {
    return this.genres.contains(g);
  }

  /**
   * Returns certain Genre
   * @param g Genre
   * @return Genre
   */
  public Genre getGenre(Genre g) {
    for (Genre genre : genres) {
      if (genre.equals(g)) {
        return genre;
      }
    }
    return null;
  }

  /**
   * Returns ArrayList of Genres of Movie
   * @return Genres ArrayList
   */
  public ArrayList<Genre> getGenres() {
    return this.genres;
  }

  @Override
  public String toString() {
      return this.getTitle();
  }
}