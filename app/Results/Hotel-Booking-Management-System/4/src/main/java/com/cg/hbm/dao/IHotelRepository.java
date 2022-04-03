package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.Hotel;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{


@Transactional
@Modifying
@Query(value = "update hotel h set h.hotel_id = ?1 where h.hotel_id = ?1", nativeQuery = true)
public void setHotel(int hotel_id,Hotel hotel);

@Query(value = "Select * from hotel h  where h.hotel_id = ?1", nativeQuery = true)
public Hotel getHotel(int hotel_id);


}