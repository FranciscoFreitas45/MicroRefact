package com.cg.sprint.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class Movies {

 private  int movie_id;

 private  String theatres;

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


public void setTheatres(String theatres){
    this.theatres = theatres;
}


}