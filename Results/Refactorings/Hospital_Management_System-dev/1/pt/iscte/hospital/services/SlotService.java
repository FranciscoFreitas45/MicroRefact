import org.springframework.data.domain.Sort;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.entities.Speciality;
import pt.iscte.hospital.objects.utils.Day;
import pt.iscte.hospital.objects.utils.TimeInterval;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
public interface SlotService {


public Long countAllByIsAvailableAndDoctorAndDateBetween(boolean isAvailable,Doctor doctor,LocalDate dateBegin,LocalDate dateEnd)


public List<Slot> findAllByDoctorAndIsAvailableOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable)


public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(boolean isAvailable,Speciality speciality,LocalDate dateBegin,LocalDate dateEnd)


public Long countByDoctorAndDate(Doctor doctor,LocalDate date)


public void generateSlots(int duration,List<TimeInterval> timeIntervalList,List<DayOfWeek> weekDaysList,int year,int month,List<Doctor> doctors)


public void saveSlot(Slot slot)


public List<Slot> findAll(Sort sort)


public Slot findBySlotId(Long slotId)


public List<Day> calendarColor(List<Day> calendar,String specialityName)


public List<Slot> findAllByDoctorAndIsAvailableAndDateOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable,LocalDate date)


public Long countByDoctorSpecialityNameAndIsAvailableAndDate(String specialityName,boolean isAvailable,LocalDate date)


public Long countByDoctorAndIsAvailableAndDate(Doctor doctor,boolean isAvailable,LocalDate date)


public Long countByDoctorSpecialityNameAndDate(String specialityName,LocalDate date)


public boolean hasDisponibilidadeNoMes(List<Day> calendar,Doctor doctor)


public List<Slot> findAllByDoctorAndDateOrderByTimeBeginAsc(Doctor doctor,LocalDate date)


public boolean hasDisponibilidadeNoDia(LocalDate day,Doctor doctor)


}