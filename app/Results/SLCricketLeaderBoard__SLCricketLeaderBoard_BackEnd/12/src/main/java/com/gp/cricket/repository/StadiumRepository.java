package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Stadium;
public interface StadiumRepository extends JpaRepository<Stadium, Integer>{


public void setStadiumId(Integer stadiumIdv2,Stadium stadiumId);

public Stadium getStadiumId(Integer stadiumIdv2);

}