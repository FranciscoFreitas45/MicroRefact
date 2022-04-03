package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Schedule;
import com.puffride.demo.repository.ScheduleRepository;
@Service
@Transactional
public class ScheduleDao {

@Autowired
 private ScheduleRepository scheduleRepository;


public Schedule findOne(Long id){
    return scheduleRepository.findById(id).orElse(null);
}


public Schedule save(Schedule entity){
    return scheduleRepository.save(entity);
}


public void deleteAll(List<Schedule> entityList){
    scheduleRepository.deleteAll();
}


public List<Schedule> findAll(){
    return scheduleRepository.findAll();
}


public void delete(Schedule entity){
    scheduleRepository.delete(entity);
}


}