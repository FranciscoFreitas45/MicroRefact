import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.objects.utils.TimeInterval;
import pt.iscte.hospital.services.SlotService;
import pt.iscte.hospital.services.user.DoctorService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class CreateSlots {

@Autowired
 private  SlotService slotService;

@Autowired
 private  DoctorService doctorService;


@Scheduled(fixedRate = 60000000)
@Scheduled(cron = "0 0 0 1 1/1 *")
public void createSlotsBeginOfNextMonth(){
    // For generating cron expressions: cronmaker.com
    LocalDate todayDate = LocalDate.now();
    LocalDate nextMonth = todayDate.plusMonths(1);
    int duration = 60 * 2;
    List<TimeInterval> timeIntervalList = new ArrayList<>();
    List<DayOfWeek> weekDaysList = new ArrayList<>();
    int year = nextMonth.getYear();
    int month = nextMonth.getMonthValue();
    List<Doctor> doctors = doctorService.findAll();
    timeIntervalList.add(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(12, 0)));
    timeIntervalList.add(new TimeInterval(LocalTime.of(13, 0), LocalTime.of(17, 0)));
    weekDaysList.add(DayOfWeek.MONDAY);
    weekDaysList.add(DayOfWeek.TUESDAY);
    weekDaysList.add(DayOfWeek.WEDNESDAY);
    weekDaysList.add(DayOfWeek.THURSDAY);
    weekDaysList.add(DayOfWeek.FRIDAY);
    weekDaysList.add(DayOfWeek.SATURDAY);
    weekDaysList.add(DayOfWeek.SUNDAY);
    slotService.generateSlots(duration, timeIntervalList, weekDaysList, year, month, doctors);
}


@Scheduled(fixedRate = 60000000)
public void createSlotsCurrentMonth(){
    // For generating cron expressions: cronmaker.com
    LocalDate todayDate = LocalDate.now();
    int duration = 60 * 2;
    List<TimeInterval> timeIntervalList = new ArrayList<>();
    List<DayOfWeek> weekDaysList = new ArrayList<>();
    int year = todayDate.getYear();
    int month = todayDate.getMonthValue();
    List<Doctor> doctors = doctorService.findAll();
    timeIntervalList.add(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(12, 0)));
    timeIntervalList.add(new TimeInterval(LocalTime.of(13, 0), LocalTime.of(17, 0)));
    weekDaysList.add(DayOfWeek.MONDAY);
    weekDaysList.add(DayOfWeek.TUESDAY);
    weekDaysList.add(DayOfWeek.WEDNESDAY);
    weekDaysList.add(DayOfWeek.THURSDAY);
    weekDaysList.add(DayOfWeek.FRIDAY);
    weekDaysList.add(DayOfWeek.SATURDAY);
    weekDaysList.add(DayOfWeek.SUNDAY);
    slotService.generateSlots(duration, timeIntervalList, weekDaysList, year, month, doctors);
}


}