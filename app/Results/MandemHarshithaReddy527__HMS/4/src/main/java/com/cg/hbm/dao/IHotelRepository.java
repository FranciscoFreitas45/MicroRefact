package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.Hotel;
@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{


public void setHotel(int hotel_id,Hotel hotel);

public Hotel getHotel(int hotel_id);

public Hotel getHotel(int hotel_id);

public void setHotel(int hotel_id,Hotel hotel);

}