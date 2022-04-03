package com.gp.cricket.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Manager;
import com.gp.cricket.entity.Stadium;
import com.gp.cricket.repository.StadiumRepository;
@Service
public class StadiumService {

@Autowired
 private StadiumRepository stadiumRepository;


public Stadium registerStadium(Stadium stadium){
    return this.stadiumRepository.save(stadium);
}


public List<Stadium> getAllStadium(){
    System.out.println("Get all stadiums here");
    return this.stadiumRepository.findAll();
}


}