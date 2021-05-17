import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.entities.Speciality;
import pt.iscte.hospital.objects.utils.CalendarColor;
import pt.iscte.hospital.objects.utils.Day;
import pt.iscte.hospital.objects.utils.TimeInterval;
import pt.iscte.hospital.repositories.user.DoctorRepository;
import pt.iscte.hospital.repositories.SlotRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class SlotServiceImpl implements SlotService,pt.iscte.hospital.services.SlotService{

@Autowired
 private  DoctorRepository doctorRepository;

@Autowired
 private  SlotRepository slotRepository;


@Override
public Long countAllByIsAvailableAndDoctorAndDateBetween(boolean isAvailable,Doctor doctor,LocalDate dateBegin,LocalDate dateEnd){
    return slotRepository.countAllByIsAvailableAndDoctorAndDateBetween(isAvailable, doctor, dateBegin, dateEnd);
}


@Override
public List<Slot> findAllByDoctorAndIsAvailableOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable){
    return slotRepository.findAllByDoctorAndIsAvailableOrderByTimeBeginAsc(doctor, isAvailable);
}


@Override
public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(boolean isAvailable,Speciality speciality,LocalDate dateBegin,LocalDate dateEnd){
    return slotRepository.countAllByIsAvailableAndDoctorSpecialityAndDateBetween(isAvailable, speciality, dateBegin, dateEnd);
}


@Override
public Long countByDoctorAndDate(Doctor doctor,LocalDate date){
    return slotRepository.countByDoctorAndDate(doctor, date);
}


@Override
public void generateSlots(int duration,List<TimeInterval> timeIntervalList,List<DayOfWeek> weekDaysList,int year,int month,List<Doctor> doctors){
    // number of days in the month
    int nrDays = LocalDate.of(year, month, 1).lengthOfMonth();
    // For each day
    for (int day = 1; day <= nrDays; day++) {
        LocalDate slotDate = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = slotDate.getDayOfWeek();
        System.out.println(day);
        // if the week day is available
        if (weekDaysList.contains(dayOfWeek)) {
            // for each doctor
            for (Doctor doctor : doctors) {
                List<Slot> listSlots = slotRepository.findAllByDoctorAndDateOrderByTimeBeginAsc(doctor, slotDate);
                for (TimeInterval timeInterval : timeIntervalList) {
                    LocalTime slotTimeBegin = timeInterval.getTimeBegin();
                    LocalTime slotTimeEnd = slotTimeBegin.plusMinutes(duration);
                    while (insideTimeInterval(timeInterval, slotTimeBegin, slotTimeEnd)) {
                        // while inside the interval
                        // check if it is inside an existing slot
                        if (!checkIfInsideListSlot(listSlots, slotTimeBegin, slotTimeEnd)) {
                            // create slot
                            Slot slot = new Slot();
                            slot.setDate(slotDate);
                            slot.setTimeBegin(slotTimeBegin);
                            slot.setTimeEnd(slotTimeEnd);
                            slot.setDoctor(doctor);
                            saveSlot(slot);
                        }
                        slotTimeBegin = slotTimeEnd;
                        slotTimeEnd = slotTimeBegin.plusMinutes(duration);
                    }
                }
            }
        }
    }
    System.out.println("Geração de Vagas concluída");
}


@Override
public void saveSlot(Slot slot){
    slotRepository.save(slot);
}


@Override
public List<Slot> findAll(Sort sort){
    return slotRepository.findAll(sort);
}


public boolean insideTimeInterval(TimeInterval timeInterval,LocalTime slotTimeBegin,LocalTime slotTimeEnd){
    // Begin time is inside time interval
    boolean cond1 = slotTimeBegin.isAfter(timeInterval.getTimeBegin());
    boolean cond2 = slotTimeBegin.isBefore(timeInterval.getTimeEnd());
    boolean cond3 = slotTimeBegin.equals(timeInterval.getTimeBegin());
    boolean cond4 = (cond1 && cond2) || cond3;
    // End time is inside time interval
    boolean cond5 = slotTimeEnd.isAfter(timeInterval.getTimeBegin());
    boolean cond6 = slotTimeEnd.isBefore(timeInterval.getTimeEnd());
    boolean cond7 = slotTimeEnd.equals(timeInterval.getTimeEnd());
    boolean cond8 = (cond5 && cond6) || cond7;
    return cond4 && cond8;
}


@Override
public Slot findBySlotId(Long slotId){
    return slotRepository.findBySlotId(slotId);
}


public boolean checkIfInsideListSlot(List<Slot> listSlots,LocalTime slotTimeBegin,LocalTime slotTimeEnd){
    for (Slot existingSlot : listSlots) {
        TimeInterval interval = new TimeInterval(existingSlot.getTimeBegin(), existingSlot.getTimeEnd());
        if (insideTimeInterval(interval, slotTimeBegin, slotTimeEnd)) {
            return true;
        }
    }
    return false;
}


@Override
public List<Day> calendarColor(List<Day> calendar,String specialityName){
    LocalDate todayDate = LocalDate.now();
    ArrayList<Day> calendarColor = new ArrayList<>();
    for (Day day : calendar) {
        long totalSlots = countByDoctorSpecialityNameAndDate(specialityName, day.getDate());
        long availableSlots = countByDoctorSpecialityNameAndIsAvailableAndDate(specialityName, true, day.getDate());
        calendarColor.add(colorDay(day, todayDate, totalSlots, availableSlots));
    }
    return calendarColor;
}


@Override
public List<Slot> findAllByDoctorAndIsAvailableAndDateOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable,LocalDate date){
    return slotRepository.findAllByDoctorAndIsAvailableAndDateOrderByTimeBeginAsc(doctor, isAvailable, date);
}


@Override
public Long countByDoctorSpecialityNameAndIsAvailableAndDate(String specialityName,boolean isAvailable,LocalDate date){
    return slotRepository.countByDoctorSpecialityNameAndIsAvailableAndDate(specialityName, isAvailable, date);
}


@Override
public Long countByDoctorAndIsAvailableAndDate(Doctor doctor,boolean isAvailable,LocalDate date){
    return slotRepository.countByDoctorAndIsAvailableAndDate(doctor, isAvailable, date);
}


public Day colorDay(Day day,LocalDate todayDate,long totalSlots,long availableSlots){
    double fraction = 1 - ((double) availableSlots) / totalSlots;
    if (totalSlots == 0 || todayDate.isAfter(day.getDate())) {
        day.setColor(CalendarColor.WHITE.getName());
    } else if (fraction >= CalendarColor.RED.getMinFraction()) {
        day.setColor(CalendarColor.RED.getName());
    } else if (fraction > CalendarColor.YELLOW.getMinFraction()) {
        day.setColor(CalendarColor.YELLOW.getName());
    } else {
        day.setColor(CalendarColor.GREEN.getName());
    }
    return day;
}


@Override
public Long countByDoctorSpecialityNameAndDate(String specialityName,LocalDate date){
    return slotRepository.countByDoctorSpecialityNameAndDate(specialityName, date);
}


@Override
public boolean hasDisponibilidadeNoMes(List<Day> calendar,Doctor doctor){
    for (Day day : calendar) {
        long availableSlots = countByDoctorAndIsAvailableAndDate(doctor, true, day.getDate());
        if (availableSlots > 0) {
            return true;
        }
    }
    return false;
}


@Override
public List<Slot> findAllByDoctorAndDateOrderByTimeBeginAsc(Doctor doctor,LocalDate date){
    return slotRepository.findAllByDoctorAndDateOrderByTimeBeginAsc(doctor, date);
}


@Override
public boolean hasDisponibilidadeNoDia(LocalDate day,Doctor doctor){
    long availableSlots = countByDoctorAndIsAvailableAndDate(doctor, true, day);
    if (availableSlots > 0) {
        return true;
    }
    return false;
}


}