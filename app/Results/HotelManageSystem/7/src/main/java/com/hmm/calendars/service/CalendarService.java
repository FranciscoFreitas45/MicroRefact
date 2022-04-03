package com.hmm.calendars.service;
 import java.util.List;
import java.util.Optional;
import com.hmm.calendars.entity.Calendar;
public interface CalendarService {


public boolean existsById(Long id)
;

public Optional<Calendar> findById(Long id)
;

public void save(Calendar entity)
;

public void deleteById(Long id)
;

public void deleteAll(Long[] ids)
;

public List<Calendar> findAll()
;

}