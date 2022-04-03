package com.cg.sprint.service;
 import java.util.List;
import com.cg.sprint.entity.Account;
import com.cg.sprint.entity.Admin;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Customer;
import com.cg.sprint.entity.Languages;
import com.cg.sprint.entity.Movies;
import com.cg.sprint.entity.Payments;
import com.cg.sprint.entity.Refund;
import com.cg.sprint.entity.Seats;
import com.cg.sprint.entity.Shows;
import com.cg.sprint.entity.Theatre;
public interface AdminServiceInterface {


public String updateCity(City c)
;

public Admin aLogin(String uname,String pass)
;

public List<City> getCityList()
;

public Shows save(Shows s)
;

public List<Movies> getMovieList()
;

public void removeMovie(int movie_id)
;

public String updateShows(Shows s)
;

public List<Theatre> getTheatreList()
;

public String updateTheatre(Theatre t)
;

public List<Shows> getShowList()
;

public void removeTheatre(int theatre_id)
;

public void removeShow(int sno)
;

public void removeCity(int sno)
;

public String updateMovies(Movies m)
;

}