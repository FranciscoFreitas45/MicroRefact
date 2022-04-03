package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.BookingDetails;
@Repository
public interface IBookingDetailsRepository extends JpaRepository<BookingDetails, Integer>{


public void setBookingdetails(int booking_id,BookingDetails bookingdetails);

public BookingDetails getBookingdetails(int booking_id);

}