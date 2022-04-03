package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Rating;
import com.puffride.demo.repository.RatingRepository;
@Service
@Transactional
public class RatingDao {

@Autowired
 private RatingRepository ratingRepository;


public Rating findOne(Long id){
    return ratingRepository.findById(id).orElse(null);
}


public Rating save(Rating entity){
    return ratingRepository.save(entity);
}


public void deleteAll(List<Rating> entityList){
    ratingRepository.deleteAll();
}


public List<Rating> findAll(){
    return ratingRepository.findAll();
}


public void delete(Rating entity){
    ratingRepository.delete(entity);
}


}