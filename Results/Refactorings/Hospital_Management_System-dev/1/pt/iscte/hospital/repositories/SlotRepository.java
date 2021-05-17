import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.entities.Speciality;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {


public Long countAllByIsAvailableAndDoctorAndDateBetween(boolean isAvailable,Doctor doctor,LocalDate dateBegin,LocalDate dateEnd)


public Slot findByDoctorAndDateAndTimeBegin(Doctor doctor,LocalDate date,LocalTime timeBegin)


public List<Slot> findAllByDoctorAndIsAvailableOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable)


public List<Slot> findAllByDoctorAndIsAvailableAndDateOrderByTimeBeginAsc(Doctor doctor,boolean isAvailable,LocalDate date)


public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(boolean isAvailable,Speciality speciality,LocalDate dateBegin,LocalDate dateEnd)


public Long countByDoctorSpecialityNameAndIsAvailableAndDate(String specialityName,boolean isAvailable,LocalDate date)


public Long countByDoctorAndDate(Doctor doctor,LocalDate date)


public Long countByDoctorAndIsAvailableAndDate(Doctor doctor,boolean isAvailable,LocalDate date)


public Long countByDoctorSpecialityNameAndDate(String specialityName,LocalDate date)


public Slot findBySlotId(Long slotId)


public List<Slot> findAllByDoctorAndDateOrderByTimeBeginAsc(Doctor doctor,LocalDate date)


public void setSlot(Long Long,Slot slot)

public Slot getSlot(Long Long)

}