package com.gp.cricket.mapobject;
 import java.time.LocalDate;
public class PaymentEmailBody {

 private  String club;

 private  Integer year;

 private  LocalDate date;

 private  Double amount;

public PaymentEmailBody() {
}
public void setClub(String club){
    this.club = club;
}


public String getClub(){
    return club;
}


public Integer getYear(){
    return year;
}


public void setDate(LocalDate date){
    this.date = date;
}


public LocalDate getDate(){
    return date;
}


@Override
public String toString(){
    return "PaymentEmailBody [club=" + club + ", year=" + year + ", date=" + date + ", amount=" + amount + "]";
}


public void setAmount(Double amount){
    this.amount = amount;
}


public void setYear(Integer year){
    this.year = year;
}


public Double getAmount(){
    return amount;
}


}