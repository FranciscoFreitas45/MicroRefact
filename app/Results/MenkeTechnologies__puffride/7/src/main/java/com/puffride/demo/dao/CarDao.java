package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Car;
import com.puffride.demo.repository.CarRepository;
@Service
@Transactional
public class CarDao {

@Autowired
 private CarRepository carRepository;


public Car findOne(Long id){
    return carRepository.findById(id).orElse(null);
}


public Car save(Car entity){
    return carRepository.save(entity);
}


public void deleteAll(List<Car> entityList){
    carRepository.deleteAll();
}


public List<Car> findAll(){
    return carRepository.findAll();
}


public void delete(Car entity){
    carRepository.delete(entity);
}


}