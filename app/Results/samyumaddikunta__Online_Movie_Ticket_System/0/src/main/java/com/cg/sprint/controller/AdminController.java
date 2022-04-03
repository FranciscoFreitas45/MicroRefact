package com.cg.sprint.controller;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
import com.cg.sprint.exceptions.AccountNotFoundException;
import com.cg.sprint.service.AdminServiceInterface;
@RestController
@CrossOrigin("http://localhost:4200")
public class AdminController {

@Autowired
 private  AdminServiceInterface adminservice;


@PutMapping("/update_cities")
public String updateCity(City c){
    String string = adminservice.updateCity(c);
    return string;
}


@GetMapping("/admin-validation/{username}/{password}")
public ResponseEntity<Admin> aLogin(String uname,String pwd){
    Admin auth = adminservice.aLogin(uname, pwd);
    ResponseEntity<Admin> response = new ResponseEntity<Admin>(auth, HttpStatus.OK);
    return response;
}


@GetMapping("/theatreList")
public List<Theatre> theatreList(){
    List<Theatre> list = adminservice.getTheatreList();
    return list;
}


@GetMapping("/cityList")
public List<City> cityList(){
    List<City> list = adminservice.getCityList();
    return list;
}


@GetMapping("/moviesList")
public List<Movies> movieList(){
    List<Movies> list = adminservice.getMovieList();
    return list;
}


@DeleteMapping("/removeMovie/{movie_id}")
public String removeMovie(int movie_id){
    adminservice.removeMovie(movie_id);
    return "Movie Details Removed";
}


@PostMapping("/addcity")
public City addCity(City c){
    return adminservice.save(c);
}


@PutMapping("/update_shows")
public String updateShows(Shows s){
    String string = adminservice.updateShows(s);
    return string;
}


@PostMapping("/addtheatre")
public Theatre addTheatre(Theatre t){
    return adminservice.save(t);
}


@PutMapping("/update_theatres")
public String updateTheatre(Theatre t){
    String string = adminservice.updateTheatre(t);
    return string;
}


@PostMapping("/addshow")
public Shows addShow(Shows s){
    return adminservice.save(s);
}


@PostMapping("/addmovie")
public Movies addMovie(Movies m){
    return adminservice.save(m);
}


@DeleteMapping("/removeTheatre/{theatre_id}")
public String removeTheatre(int theatre_id){
    adminservice.removeTheatre(theatre_id);
    return "Theatre Details Removed";
}


@DeleteMapping("/removeShow/{sno}")
public String removeShow(int sno){
    adminservice.removeShow(sno);
    return "Show Details Removed";
}


@DeleteMapping("/removeCity/{sno}")
public String removeCity(int sno){
    adminservice.removeCity(sno);
    return "City Details Removed";
}


@GetMapping("/showsList")
public List<Shows> ShowList(){
    List<Shows> list = adminservice.getShowList();
    return list;
}


@PutMapping("/update_movies")
public String updateMovies(Movies m){
    String string = adminservice.updateMovies(m);
    return string;
}


}