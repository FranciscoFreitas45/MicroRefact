package com.cg.sprint.service;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.sprint.dao.AccountDao;
import com.cg.sprint.dao.AdminDao;
import com.cg.sprint.dao.CityDao;
import com.cg.sprint.dao.CustomerDao;
import com.cg.sprint.dao.LanguagesDao;
import com.cg.sprint.dao.MoviesDao;
import com.cg.sprint.dao.PaymentDao;
import com.cg.sprint.dao.RefundDao;
import com.cg.sprint.dao.SeatsDao;
import com.cg.sprint.dao.ShowsDao;
import com.cg.sprint.dao.TheatreDao;
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
import com.cg.sprint.Interface.AccountDao;
import com.cg.sprint.Interface.TheatreDao;
import com.cg.sprint.Interface.ShowsDao;
import com.cg.sprint.Interface.LanguagesDao;
import com.cg.sprint.Interface.SeatsDao;
import com.cg.sprint.Interface.CustomerDao;
import com.cg.sprint.Interface.PaymentDao;
import com.cg.sprint.Interface.RefundDao;
@Service
@Transactional
public class AdminService implements AdminServiceInterface{

@Autowired
 private  AccountDao validation;

@Autowired
 private  CityDao city;

@Autowired
 private  TheatreDao theatre;

@Autowired
 private  MoviesDao movie;

@Autowired
 private  ShowsDao shows;

@Autowired
 private  LanguagesDao language;

@Autowired
 private  SeatsDao seats;

@Autowired
 private  CustomerDao account;

@Autowired
 private  PaymentDao payment;

@Autowired
 private  RefundDao refund;

@Autowired
 private  AdminDao admin;


@Override
public String updateCity(City c){
    boolean bool = city.existsById(c.getSno());
    if (bool == true) {
        city.save(c);
        return "cities were updated successfully!!";
    } else {
        return "sorry, cities were not updated";
    }
}


@Override
public Admin aLogin(String uname,String pass){
    return admin.aLogin(uname, pass);
}


@Override
public List<City> getCityList(){
    return city.getCityList();
}


@Override
public Shows save(Shows s){
    return shows.save(s);
}


@Override
public List<Movies> getMovieList(){
    return movie.getMovieList();
}


@Override
public void removeMovie(int movie_id){
    movie.deleteById(movie_id);
}


@Override
public String updateShows(Shows s){
    boolean bool = shows.existsById(s.getSno());
    if (bool == true) {
        shows.save(s);
        return "shows were updated successfully!!";
    } else {
        return "shows were not updated";
    }
}


@Override
public List<Theatre> getTheatreList(){
    return theatre.getTheatreList();
}


@Override
public String updateTheatre(Theatre t){
    boolean bool = theatre.existsById(t.getTheatre_id());
    if (bool == true) {
        theatre.save(t);
        return "theatres were updated successfully!!";
    } else {
        return "theatres were not updated";
    }
}


@Override
public List<Shows> getShowList(){
    return shows.getShowList();
}


@Override
public void removeTheatre(int theatre_id){
    theatre.deleteById(theatre_id);
}


@Override
public void removeShow(int sno){
    shows.deleteById(sno);
}


@Override
public void removeCity(int sno){
    city.deleteById(sno);
}


@Override
public String updateMovies(Movies m){
    boolean bool = movie.existsById(m.getMovie_id());
    if (bool == true) {
        movie.save(m);
        return "movies were updated successfully!!";
    } else {
        return "movies were not updated";
    }
}


}