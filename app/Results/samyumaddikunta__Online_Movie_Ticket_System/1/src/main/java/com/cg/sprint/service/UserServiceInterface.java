package com.cg.sprint.service;
 import java.util.List;
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
public interface UserServiceInterface {


public Seats seatDetails(String s_type)
;

public List<Theatre> theatreNames(String name)
;

public List<City> getCityNames()
;

public List<Languages> getLanguage()
;

public List<Shows> getShows()
;

public String setSeats(Seats seat)
;

public List<Movies> movieNames(String name)
;

public String updateSeats(Seats seat_obj)
;

public String payments(Payments pay)
;

public String refundDetails(Refund ref)
;

public Account save(Account a)
;

public String updatePayment(Payments pay)
;

public Customer getAccountData(int acc_no)
;

public List<Seats> getSeats()
;

public Account validate(String uname,String pass)
;

public Payments refund(int acc_no,int book_id)
;

}