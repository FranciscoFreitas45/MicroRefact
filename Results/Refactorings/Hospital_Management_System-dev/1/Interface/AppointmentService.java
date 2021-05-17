public interface AppointmentService {

   public Appointment findByAppointmentId(Long appointmentId);
   public void saveAppointment(Appointment appointment);
}