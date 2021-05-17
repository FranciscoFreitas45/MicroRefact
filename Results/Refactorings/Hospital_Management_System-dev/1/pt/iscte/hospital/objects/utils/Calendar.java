import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Calendar {

 public  DateTimeFormatter FORMATTER;

 public  DateTimeFormatter TIME_FORMATTER;

 public  DateTimeFormatter DATE_TIME_FORMATTER;


public List<Day> calendarList(int year,int month){
    LocalDate date = LocalDate.of(year, month, 1);
    Integer[] calendar = new Integer[42];
    int nrDays = date.lengthOfMonth();
    int weekDay = date.getDayOfWeek().getValue();
    for (int day = 1; day <= nrDays; day++) {
        calendar[day + weekDay - 2] = day;
    }
    List<Day> calendarDays = new ArrayList<>();
    for (int i = 0; i < calendar.length; i++) {
        String strDate = "";
        LocalDate ld = null;
        if (calendar[i] != null) {
            ld = LocalDate.of(year, month, calendar[i]);
            strDate = ld.format(FORMATTER);
        }
        calendarDays.add(new Day(i, calendar[i], strDate, ld));
    }
    return calendarDays;
}


}