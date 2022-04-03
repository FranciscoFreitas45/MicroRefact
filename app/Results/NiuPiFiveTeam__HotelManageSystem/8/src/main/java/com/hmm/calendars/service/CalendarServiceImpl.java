package com.hmm.calendars.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmm.calendars.dao.CalendarDao;
import com.hmm.calendars.entity.Calendar;
@Service
@Transactional
public class CalendarServiceImpl implements CalendarService{

@Autowired
 private  CalendarDao calendarDao;


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return calendarDao.existsById(id);
}


@Override
public Optional<Calendar> findById(Long id){
    // TODO Auto-generated method stub
    return calendarDao.findById(id);
}


@Override
public void save(Calendar entity){
    // TODO Auto-generated method stub
    calendarDao.save(entity);
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    calendarDao.deleteById(id);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> list = new ArrayList<>(Arrays.asList(ids));
    List<Calendar> calendars = (List<Calendar>) calendarDao.findAllById(list);
    calendarDao.deleteAll(calendars);
}


@Override
public List<Calendar> findAll(){
    // TODO Auto-generated method stub
    return (List<Calendar>) calendarDao.findAll();
}


}