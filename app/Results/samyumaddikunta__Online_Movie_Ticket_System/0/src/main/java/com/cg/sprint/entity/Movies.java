package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "movies")
public class Movies {

@Id
@Column(length = 4)
 private  int movie_id;

@Column(length = 15)
 private  String theatres;

@Column(length = 30)
 private  String movies;


public int getMovie_id(){
    return movie_id;
}


public String getTheatres(){
    return theatres;
}


public String getMovies(){
    return movies;
}


public void setMovie_id(int movie_id){
    this.movie_id = movie_id;
}


public void setTheatres(String theatres){
    this.theatres = theatres;
}


public void setMovies(String movies){
    this.movies = movies;
}


}