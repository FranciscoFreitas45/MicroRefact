package com.cg.hbm.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IBookingDetailsRepository;
import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
import com.cg.hbm.exceptions.HotelNotFoundException;
@Service
public class IBookingDetailsServiceImpl implements IBookingDetailsService{

@Autowired
 private IBookingDetailsRepository bDao;


@Override
public BookingDetails addBookingDetails(BookingDetails bookingdetails){
    Optional<BookingDetails> b = bDao.findById(bookingdetails.getBooking_id());
    if (b.isEmpty()) {
        return bDao.saveAndFlush(bookingdetails);
    } else {
        throw new BookingDetailsNotFoundException("BookingDetails already exists");
    }
}


@Override
public BookingDetails updateBookingDetails(int bookingId,BookingDetails bookingDetails){
    // TODO Auto-generated method stub
    Optional<BookingDetails> b = bDao.findById(bookingId);
    if (b.isEmpty()) {
        throw new BookingDetailsNotFoundException("BookingDetails not found");
    } else
        bDao.save(bookingDetails);
    return bookingDetails;
}


@Override
public List<BookingDetails> showAllBookingDetails(){
    List<BookingDetails> boo = bDao.findAll();
    if (boo.isEmpty()) {
        throw new BookingDetailsNotFoundException("BookingDetails not found");
    }
    return boo;
}


@Override
public BookingDetails showBookingDetails(int booking_id){
    Optional<BookingDetails> b = bDao.findById(booking_id);
    if (b.isEmpty()) {
        throw new BookingDetailsNotFoundException("Given BookingDetailsId does not exist");
    }
    return b.get();
}


@Override
public BookingDetails removeBookingDetails(int booking_id){
    Optional<BookingDetails> op = bDao.findById(booking_id);
    if (op.isPresent()) {
        bDao.deleteById(booking_id);
        return op.get();
    } else
        throw new BookingDetailsNotFoundException("BookingDetails with given Id doesn't exist.");
}


}