package com.evolvingreality.onleave.service;
 import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.evolvingreality.onleave.domain.Day;
import com.evolvingreality.onleave.domain.Month;
import com.evolvingreality.onleave.domain.Year;
@Service
public class CalendarServiceImpl implements CalendarService{

 protected  Logger log;


@Override
public Year get(Integer yearInt){
    log.debug("Entering with {}", yearInt);
    LocalDate date = LocalDate.of(yearInt, java.time.Month.JANUARY, 1);
    Year year = new Year();
    year.setYear(yearInt);
    for (int i = 0; i < 12; i++) year.getMonths().add(create(date, date.plusMonths(i)));
    return year;
}


public Day create(LocalDate localDay){
    log.debug("Entering with {}", localDay);
    return new Day(localDay.getDayOfWeek(), localDay.getDayOfMonth(), localDay.getDayOfYear());
}


public List<Day> padMonthStart(List<Day> days){
    log.debug("Entering with {}", days);
    List<Day> paddedDays = new ArrayList<>(days);
    for (int i = 0; i < 6; i++) {
        Day day = paddedDays.get(0);
        if (day.getDayOfWeek() != DayOfWeek.SUNDAY)
            paddedDays.add(0, new Day(day.getDayOfWeek().minus(1), 0, 0));
        else
            break;
    }
    return paddedDays;
}


public List<Day> padMonthEnd(List<Day> days){
    log.debug("Entering with {}", days);
    List<Day> paddedDays = new ArrayList<>(days);
    for (int i = days.size(); i < 42; i++) {
        Day day = paddedDays.get(i - 1);
        paddedDays.add(new Day(day.getDayOfWeek().plus(1), 0, 0));
    }
    return paddedDays;
}


public Map<Integer,List<Day>> createWeeks(List<Day> days){
    log.debug("Entering with {}", days);
    Map<Integer, List<Day>> weeks = new HashMap<>();
    int count = 1;
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 42; i++) {
        week.add(days.get(i));
        if ((i + 1) % 7 == 0) {
            weeks.put(count++, new ArrayList<>(week));
            week.clear();
        }
    }
    return weeks;
}


}