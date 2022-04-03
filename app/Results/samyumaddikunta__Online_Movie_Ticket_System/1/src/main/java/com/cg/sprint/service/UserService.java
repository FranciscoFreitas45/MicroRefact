package com.cg.sprint.service;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.sprint.dao.AccountDao;
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
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Customer;
import com.cg.sprint.entity.Languages;
import com.cg.sprint.entity.Movies;
import com.cg.sprint.entity.Payments;
import com.cg.sprint.entity.Refund;
import com.cg.sprint.entity.Seats;
import com.cg.sprint.entity.Shows;
import com.cg.sprint.entity.Theatre;
import com.cg.sprint.Interface.CityDao;
import com.cg.sprint.Interface.TheatreDao;
import com.cg.sprint.Interface.MoviesDao;
import com.cg.sprint.Interface.ShowsDao;
@Service
@Transactional
public class UserService implements UserServiceInterface{

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


@Override
public Seats seatDetails(String s_type){
    return seats.seatDetails(s_type);
}


@Override
public List<Theatre> theatreNames(String name){
    List<Theatre> list = theatre.theatreNames(name);
    return list;
}


public List<City> getCityNames(){
    return city.getCityNames();
}


@Override
public List<Languages> getLanguage(){
    List<Languages> list = language.findAll();
    return list;
}


public List<Shows> getShows(){
    return shows.getShows();
}


@Override
public String setSeats(Seats seat){
    boolean bool = seats.existsById(seat.getSno());
    if (bool == true) {
        seats.save(seat);
        return "seats updated successfully!!";
    } else {
        return "Sorry!!seats were not updated.";
    }
}


@Override
public List<Movies> movieNames(String name){
    List<Movies> list = movie.movieNames(name);
    return list;
}


@Override
public String updateSeats(Seats seat_obj){
    boolean bool = seats.existsById(seat_obj.getSno());
    if (bool == true) {
        seats.save(seat_obj);
        return "seats were updated successfully!!";
    } else {
        return "sorry, seats were not updated";
    }
}


@Override
public String payments(Payments pay){
    payment.save(pay);
    int id = pay.getBooking_id();
    return "your booking id is : " + id;
}


@Override
public String refundDetails(Refund ref){
    refund.save(ref);
    return "inserted the refund details successfully!!";
}


@Override
public Account save(Account a){
    return validation.save(a);
}


@Override
public String updatePayment(Payments pay){
    boolean bool = payment.existsById(pay.getBooking_id());
    if (bool == true) {
        payment.save(pay);
        return "payment details updated successfully!!";
    } else {
        return "sorry,payment details not updated!!";
    }
}


@Override
public Customer getAccountData(int acc_no){
    return account.getAccountData(acc_no);
}


@Override
public List<Seats> getSeats(){
    List<Seats> list = seats.findAll();
    return list;
}


@Override
public Account validate(String uname,String pass){
    return validation.validate(uname, pass);
}


@Override
public Payments refund(int accountno,int bookingid){
    return payment.refund(accountno, bookingid);
}


}