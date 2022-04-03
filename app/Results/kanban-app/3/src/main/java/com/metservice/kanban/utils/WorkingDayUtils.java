package com.metservice.kanban.utils;
 import org.joda.time.DateTimeConstants.SATURDAY;
import org.joda.time.DateTimeConstants.SUNDAY;
import org.joda.time.LocalDate;
public class WorkingDayUtils {

 private  String[] PUBLIC_HOLIDAYS;

private WorkingDayUtils() {
}
public boolean isWorkingDay(LocalDate localDate){
    if (localDate.getDayOfWeek() == SATURDAY || localDate.getDayOfWeek() == SUNDAY) {
        return false;
    }
    for (String publicHoliday : PUBLIC_HOLIDAYS) {
        if (localDate.toString().equals(publicHoliday)) {
            return false;
        }
    }
    return true;
}


public int getWorkingDaysBetween(LocalDate inceptionDate,LocalDate acceptanceDate){
    int days = 0;
    LocalDate currentDate = inceptionDate;
    while (currentDate.isBefore(acceptanceDate)) {
        if (isWorkingDay(currentDate)) {
            days++;
        }
        currentDate = currentDate.plusDays(1);
    }
    return days;
}


public LocalDate nextWorkingDay(LocalDate localDate){
    if (localDate == null) {
        return null;
    }
    LocalDate nextDay = localDate;
    while (!isWorkingDay(nextDay)) {
        nextDay = nextDay.plusDays(1);
    }
    return nextDay;
}


}