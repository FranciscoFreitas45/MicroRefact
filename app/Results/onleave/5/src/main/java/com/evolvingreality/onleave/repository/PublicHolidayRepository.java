package com.evolvingreality.onleave.repository;
 import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Long>{


public Optional<PublicHoliday> findOneByHolidayCalendarAndHolidayDate(HolidayCalendar holidayCalendar,Date holidayDate)
;

public Page<PublicHoliday> findByHolidayCalendarAndHolidayDateBetween(HolidayCalendar holidayCalendar,Date startDate,Date endDate,Pageable pageable)
;

}