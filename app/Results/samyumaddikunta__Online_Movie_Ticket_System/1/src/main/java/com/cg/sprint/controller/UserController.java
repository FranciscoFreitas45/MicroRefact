package com.cg.sprint.controller;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cg.sprint.entity.Account;
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
import com.cg.sprint.service.UserServiceInterface;
@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

@Autowired
 private  UserServiceInterface movieservice;


@GetMapping("/seat_details/{seat_type}")
public Seats seatDetails(String seattype){
    return movieservice.seatDetails(seattype);
}


@GetMapping("/theatres/{city-name}")
public List<Theatre> theatreNames(String name){
    List<Theatre> list = movieservice.theatreNames(name);
    return list;
}


@GetMapping("/languages")
public List<Languages> getLanguage(){
    List<Languages> list = movieservice.getLanguage();
    return list;
}


@GetMapping("/shows")
public List<Shows> getShows(){
    List<Shows> list = movieservice.getShows();
    return list;
}


@PutMapping("/set_seats")
public String setSeats(Seats seat){
    String string = movieservice.setSeats(seat);
    return string;
}


@GetMapping("/movies/{theatre-name}")
public List<Movies> movieNames(String name){
    List<Movies> list = movieservice.movieNames(name);
    return list;
}


@PutMapping("/update_seats")
public String updateSeats(Seats seat){
    String string = movieservice.updateSeats(seat);
    return string;
}


@GetMapping("/cities")
public List<City> cityNames(){
    List<City> list = movieservice.getCityNames();
    return list;
}


@PostMapping("/signup")
public Account addUser(Account a){
    return movieservice.save(a);
}


@PostMapping("/payments")
public String payments(Payments pay){
    String string = movieservice.payments(pay);
    return string;
}


@PostMapping("/refund_details")
public String refundDetails(Refund refund){
    String string = movieservice.refundDetails(refund);
    return string;
}


@PutMapping("/update_payment")
public String updatePayment(Payments payment){
    String string = movieservice.updatePayment(payment);
    return string;
}


@GetMapping("/account_data/{account_no}")
public ResponseEntity<Customer> getAccountData(int accno){
    Customer customer = movieservice.getAccountData(accno);
    ResponseEntity<Customer> response = new ResponseEntity<Customer>(customer, HttpStatus.OK);
    return response;
}


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "controller account is not present")
@ExceptionHandler({ Exception.class })
public void handleException(){
}


@GetMapping("/seats")
public List<Seats> getSeats(){
    List<Seats> list = movieservice.getSeats();
    return list;
}


@GetMapping("/validation/{username}/{password}")
public ResponseEntity<Account> validate(String uname,String pwd){
    Account auth = movieservice.validate(uname, pwd);
    ResponseEntity<Account> response = new ResponseEntity<Account>(auth, HttpStatus.OK);
    return response;
}


@GetMapping("/refund/{acc_no}/{book_id}")
public Payments refund(int accno,int bookingid){
    return movieservice.refund(accno, bookingid);
}


}