package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Rider;
import com.puffride.demo.repository.RiderRepository;
@Service
@Transactional
public class RiderDao {

@Autowired
 private RiderRepository riderRepository;


public Rider findOne(Long id){
    return riderRepository.findById(id).orElse(null);
}


public Rider save(Rider entity){
    return riderRepository.save(entity);
}


public void deleteAll(List<Rider> entityList){
    riderRepository.deleteAll();
}


public List<Rider> findAll(){
    return riderRepository.findAll();
}


public void delete(Rider entity){
    riderRepository.delete(entity);
}


}