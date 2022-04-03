package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.BookingDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface IBookingDetailsRepository extends JpaRepository<BookingDetails, Integer>{


@Transactional
@Modifying
@Query(value = "update bookingdetails u set u.booking_id = ?1 where u.booking_id = ?1", nativeQuery = true)
public void setBookingdetails(int booking_id,BookingDetails bookingdetails);


@Query(value = "Select * from bookingdetails b  where b.booking_id = ?1", nativeQuery = true)
public BookingDetails getBookingdetails(int booking_id);

}