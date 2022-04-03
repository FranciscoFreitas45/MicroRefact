package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Location;
import com.puffride.demo.repository.LocationRepository;
@Service
@Transactional
public class LocationDao {

@Autowired
 private LocationRepository locationRepository;


public Location findOne(Long id){
    return locationRepository.findById(id).orElse(null);
}


public Location save(Location entity){
    return locationRepository.save(entity);
}


public void deleteAll(List<Location> entityList){
    locationRepository.deleteAll();
}


public List<Location> findAll(){
    return locationRepository.findAll();
}


public void delete(Location entity){
    locationRepository.delete(entity);
}


}