package com.cg.hbm.service;
 import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IHotelRepository;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.exceptions.HotelNotFoundException;
@Service
@Transactional
public class IHotelServiceImpl implements IHotelService{

@Autowired
 private IHotelRepository hDao;


@Override
public Hotel updateHotel(int hotelId,Hotel hotel){
    // TODO Auto-generated method stub
    Optional<Hotel> h = hDao.findById(hotelId);
    if (h.isEmpty()) {
        throw new HotelNotFoundException("Hotel not found");
    } else
        hDao.save(hotel);
    return hotel;
}


@Override
public List<Hotel> showAllHotels(){
    List<Hotel> h = hDao.findAll();
    if (h.isEmpty()) {
        throw new HotelNotFoundException("Hotel not found");
    }
    return h;
}


@Override
public Hotel showHotel(int hotel_id){
    Optional<Hotel> h = hDao.findById(hotel_id);
    if (h.isEmpty()) {
        throw new HotelNotFoundException("Given HotelId does not exist");
    }
    return h.get();
}


@Override
public Hotel addHotel(Hotel hotel){
    Optional<Hotel> h = hDao.findById(hotel.getHotel_id());
    if (h.isEmpty()) {
        return hDao.saveAndFlush(hotel);
    } else {
        throw new HotelNotFoundException("Hotel already exists");
    }
}


@Override
public Hotel removeHotel(int hotel_id){
    Optional<Hotel> op = hDao.findById(hotel_id);
    if (op.isPresent()) {
        hDao.deleteById(hotel_id);
        return op.get();
    } else
        throw new HotelNotFoundException("Hotel with given Id doesn't exist.");
}


}