package com.gp.cricket.entity;
 import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "club_payment")
public class ClubPayment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "club_payment_id")
 public  Integer clubPaymentId;

@NotNull
@Min(0)
 public  Double amount;

@NotNull
@DateTimeFormat(pattern = "MM-dd-YYYY")
 public  LocalDate date;

@NotNull
 public  Integer paymentForYear;

@NotNull
@OneToOne
@JoinColumn(name = "club_id", referencedColumnName = "club_id")
 public  Club clubId;

public ClubPayment() {
}public ClubPayment(Integer clubPaymentId, @NotNull @Min(0) Double amount, @NotNull LocalDate date, @NotNull Integer paymentForYear, @NotNull Club clubId) {
    super();
    this.clubPaymentId = clubPaymentId;
    this.amount = amount;
    this.date = date;
    this.paymentForYear = paymentForYear;
    this.clubId = clubId;
}
public Integer getPaymentForYear(){
    return paymentForYear;
}


public void setClubId(Club clubId){
    this.clubId = clubId;
}


public Club getClubId(){
    return clubId;
}


public void setClubPaymentId(Integer clubPaymentId){
    this.clubPaymentId = clubPaymentId;
}


public void setDate(LocalDate date){
    this.date = date;
}


public void setPaymentForYear(Integer paymentForYear){
    this.paymentForYear = paymentForYear;
}


public LocalDate getDate(){
    return date;
}


@Override
public String toString(){
    return "ClubPayment [clubPaymentId=" + clubPaymentId + ", amount=" + amount + ", date=" + date + ", paymentForYear=" + paymentForYear + ", clubId=" + clubId + "]";
}


public Integer getClubPaymentId(){
    return clubPaymentId;
}


public void setAmount(Double amount){
    this.amount = amount;
}


public Double getAmount(){
    return amount;
}


}