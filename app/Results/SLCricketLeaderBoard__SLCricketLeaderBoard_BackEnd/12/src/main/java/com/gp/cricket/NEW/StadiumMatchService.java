package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.StadiumRepository;
import com.gp.cricket.entity.Stadium;
@Service
public class StadiumMatchService {

@Autowired
 private StadiumRepository stadiumrepository;


public void setStadiumId(Integer stadiumIdv2,Stadium stadiumId){
stadiumrepository.setStadiumId(stadiumIdv2,stadiumId);
}


public Stadium getStadiumId(Integer stadiumIdv2){
return stadiumrepository.getStadiumId(stadiumIdv2);
}


}