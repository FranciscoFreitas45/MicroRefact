package pt.iscte.hospital.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.Invoice;
import pt.iscte.hospital.entities.Patient;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{


public List<Appointment> findAllByPatientAndAppointmentStatus(Patient patient,Integer appointmentStatus)
;

public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasCheckedOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState,boolean hasChecked)
;

public List<Appointment> findAllBySlotDateAndAppointmentStatusAndSlotDoctorSpecialityNameContainingIgnoreCaseAndSlotDoctorNameContainingIgnoreCaseAndPatientNameContainingIgnoreCase(LocalDate date,Integer appointmentStatus,String specialityName,String doctorName,String patientName)
;

public List<Appointment> findAllByPatientUserIdAndAppointmentStatusAndSlotDateAndSlotDoctorNameContainingIgnoreCaseAndSlotDoctorSpecialityNameContainingIgnoreCase(Long userId,Integer appointmentStatus,LocalDate date,String doctorName,String specialityName)
;

public List<Appointment> findAll()
;

public List<Appointment> findAllBySlotDoctorUserId(Long userId)
;

public long countBySlotDateAndAppointmentStatusAndHasChecked(LocalDate date,int appointmentState,boolean hasChecked)
;

public long countBySlotDoctorUserIdAndPatientUserIdAndAppointmentStatus(Long doctorId,Long patientId,int appointmentState)
;

public List<Appointment> findAllBySlotDateAndAppointmentStatus(LocalDate date,Integer appointmentStatus)
;

public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState)
;

public List<Appointment> findAllByPatientNameContainingIgnoreCaseAndSlotDoctorUserId(String patientName,Long userId)
;

public List<Appointment> findAllBySlotDoctorNameContainingIgnoreCaseAndPatientNameContainingIgnoreCaseAndSlotDoctorSpecialityNameContainingIgnoreCase(String doctorName,String patientName,String specialityName)
;

public List<Appointment> findAllByPatientUserIdAndSlotDateAndAppointmentStatusAndHasChecked(Long userId,LocalDate date,Integer appointmentStatus,boolean hasCheckedIn)
;

public List<Appointment> findAllBySlotDoctorUserIdAndAppointmentStatus(Long doctorId,int appointmentStatus)
;

public List<Appointment> findAllByAppointmentStatus(Integer appointmentStatus)
;

public List<Appointment> findAllByPatientUserIdAndSlotDateAndAppointmentStatus(Long userId,LocalDate date,Integer appointmentStatus)
;

public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasChecked(long doctorId,LocalDate date,int appointmentState,boolean hasChecked)
;

public Appointment findByAppointmentId(Long appointmentId)
;

public List<Appointment> findAllByPatientUserId(Long userId)
;

public List<Appointment> findAllBySlotDateAndPatientNameContainingIgnoreCaseAndSlotDoctorUserId(LocalDate date,String patientName,Long userId)
;

public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatus(long doctorId,LocalDate date,int appointmentState)
;

public List<Appointment> findAllBySlotDateAndSlotDoctorNameContainingIgnoreCaseAndPatientNameContainingIgnoreCaseAndSlotDoctorSpecialityNameContainingIgnoreCase(LocalDate date,String doctorName,String patientName,String specialityName)
;

public List<Appointment> findAllByPatientUserIdAndAppointmentStatusAndSlotDoctorNameContainingIgnoreCaseAndSlotDoctorSpecialityNameContainingIgnoreCase(Long userId,Integer appointmentStatus,String doctorName,String specialityName)
;

public List<Appointment> findAllByAppointmentStatusAndInvoice(int appointmentStatus,Invoice invoice)
;

public long countBySlotDateAndAppointmentStatus(LocalDate date,int appointmentState)
;

public Appointment getAppointment(Long appointmentId);

public Appointment getAppointment(Long appointmentId);

}