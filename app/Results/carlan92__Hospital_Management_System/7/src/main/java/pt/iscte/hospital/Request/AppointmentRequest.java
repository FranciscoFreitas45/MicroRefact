package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Appointment;
public interface AppointmentRequest {

   public Appointment getAppointment(Long appointmentId);
}