package com.cg.hbm.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.service.IHotelService;
@RestController
@RequestMapping("/hotel")
public class HotelController {

@Autowired
 private IHotelService hService;


@PutMapping("/hotel")
public ResponseEntity<Hotel> updateHotel(Hotel hotel){
    Hotel resultHotel = hService.updateHotel(hotel.getHotel_id(), hotel);
    return new ResponseEntity<Hotel>(resultHotel, HttpStatus.OK);
}


@GetMapping("/all")
public ResponseEntity<List<Hotel>> showAllHotels(){
    List<Hotel> resultHotel = hService.showAllHotels();
    return new ResponseEntity<List<Hotel>>(resultHotel, HttpStatus.OK);
}


@GetMapping("/{hotel_id}")
public ResponseEntity<Hotel> showHotel(int hotel_id){
    Hotel h = hService.showHotel(hotel_id);
    if (h != null) {
        return new ResponseEntity<Hotel>(h, HttpStatus.OK);
    } else {
        return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
    }
}


@PostMapping("/add")
public ResponseEntity<Hotel> addHotel(Hotel hotel){
    Hotel resulthotel = hService.addHotel(hotel);
    return new ResponseEntity<Hotel>(resulthotel, HttpStatus.OK);
}


@DeleteMapping("/{hotel_id}")
public Hotel removeHotel(int hotel_id){
    return hService.removeHotel(hotel_id);
}


}