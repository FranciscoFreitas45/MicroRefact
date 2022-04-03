package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Ride;
import com.puffride.demo.repository.RideRepository;
@Service
@Transactional
public class RideDao {

@Autowired
 private RideRepository rideRepository;


public Ride findOne(Long id){
    return rideRepository.findById(id).orElse(null);
}


public Ride save(Ride entity){
    return rideRepository.save(entity);
}


public void deleteAll(List<Ride> entityList){
    rideRepository.deleteAll();
}


public List<Ride> findAll(){
    return rideRepository.findAll();
}


public void delete(Ride entity){
    rideRepository.delete(entity);
}


}