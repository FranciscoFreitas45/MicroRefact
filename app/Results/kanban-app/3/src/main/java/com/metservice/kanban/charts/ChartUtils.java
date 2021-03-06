package com.metservice.kanban.charts;
 import com.metservice.kanban.utils.WorkingDayUtils.isWorkingDay;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.DTO.WorkItem;
public class ChartUtils {

private ChartUtils() {
}
public List<LocalDate> getWorkingDays(LocalDate startDate,LocalDate endDate){
    List<LocalDate> dates = new ArrayList<LocalDate>();
    LocalDate date = nextWorkingDayAfter(startDate.minusDays(1));
    while (date.isBefore(endDate) || date.isEqual(endDate)) {
        dates.add(date);
        date = nextWorkingDayAfter(date);
    }
    return dates;
}


public LocalDate getFirstDate(Iterable<WorkItem> workItems){
    LocalDate startDate = null;
    for (WorkItem workItem : workItems) {
        for (String phase : workItem.getType().getPhases()) {
            if (workItem.hasDate(phase)) {
                LocalDate date = workItem.getDate(phase);
                if (startDate == null || date.isBefore(startDate)) {
                    startDate = date;
                }
            }
        }
    }
    return startDate;
}


public List<LocalDate> getWorkingDaysForWorkItems(List<WorkItem> workItems,LocalDate startDate,LocalDate endDate){
    if (startDate == null) {
        startDate = ChartUtils.getFirstDate(workItems);
        if (startDate == null) {
            startDate = endDate;
        }
    }
    return getWorkingDays(startDate, endDate);
}


public LocalDate nextWorkingDayAfter(LocalDate date){
    date = date.plusDays(1);
    while (!isWorkingDay(date)) {
        date = date.plusDays(1);
    }
    return date;
}


}