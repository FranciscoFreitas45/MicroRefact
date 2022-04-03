package com.evolvingreality.onleave.service;
 import org.joda.time.DateTime;
import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
public interface PublicHolidayService extends EntityService<PublicHoliday>{


public Boolean hasHolidayCalendarPublicHolidayForDate(HolidayCalendar holidayCalendar,DateTime date)
;

}