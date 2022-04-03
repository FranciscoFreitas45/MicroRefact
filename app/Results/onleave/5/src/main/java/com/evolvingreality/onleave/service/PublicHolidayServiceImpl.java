package com.evolvingreality.onleave.service;
 import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
import com.evolvingreality.onleave.repository.PublicHolidayRepository;
@Service
@Transactional(readOnly = true)
public class PublicHolidayServiceImpl extends EntityServiceImpl<PublicHoliday>implements PublicHolidayService{

 private  PublicHolidayRepository publicHolidayRepository;

@Autowired
public PublicHolidayServiceImpl(final PublicHolidayRepository publicHolidayRepository) {
    super(publicHolidayRepository);
    this.publicHolidayRepository = publicHolidayRepository;
}
@Override
public Boolean hasHolidayCalendarPublicHolidayForDate(HolidayCalendar holidayCalendar,DateTime date){
    return publicHolidayRepository.findOneByHolidayCalendarAndHolidayDate(holidayCalendar, date.toDate()).isPresent();
}


}