package com.evolvingreality.onleave.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.repository.HolidayCalendarRepository;
@Service
@Transactional(readOnly = true)
public class HolidayCalendarServiceImpl extends EntityServiceImpl<HolidayCalendar>implements HolidayCalendarService{

@Autowired
public HolidayCalendarServiceImpl(final HolidayCalendarRepository holidayCalendarRepository) {
    super(holidayCalendarRepository);
}
}