package com.cg.hbm.entites;
 import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.cg.hbm.Request.BookingDetailsRequest;
import com.cg.hbm.Request.Impl.BookingDetailsRequestImpl;
import com.cg.hbm.DTO.BookingDetails;
import com.cg.hbm.Request.TransactionsRequest;
import com.cg.hbm.Request.Impl.TransactionsRequestImpl;
import com.cg.hbm.DTO.Transactions;
@Entity
@Table(name = "payments")
public class Payments {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int payment_id;

@Transient
 private  BookingDetails bookingdetails;

@Transient
 private  Transactions transactions;

@Column(name = "booking_id")
 private int booking_id;

@Transient
 private BookingDetailsRequest bookingdetailsrequest = new BookingDetailsRequestImpl();;

@Column(name = "transaction_id")
 private int transaction_id;

@Transient
 private TransactionsRequest transactionsrequest = new TransactionsRequestImpl();;

public Payments() {
}/**
 * @param bookingdetails
 * @param transactions
 */
public Payments(BookingDetails bookingdetails, Transactions transactions) {
    super();
    // this.payment_id=payment_id;
    this.bookingdetails = bookingdetails;
    this.transactions = transactions;
}
public void setBookingdetails(BookingDetails bookingdetails){
 bookingdetailsrequest.setBookingdetails(bookingdetails,this.booking_id);
}



public void setPayment_id(int payment_id){
    this.payment_id = payment_id;
}


public void setTransactions(Transactions transactions){
 transactionsrequest.setTransactions(transactions,this.transaction_id);
}



public Transactions getTransactions(){
  this.transactions = transactionsrequest.getTransactions(this.transaction_id);
return this.transactions;
}


public int getPayment_id(){
    return payment_id;
}


public BookingDetails getBookingdetails(){
  this.bookingdetails = bookingdetailsrequest.getBookingdetails(this.booking_id);
return this.bookingdetails;
}


}