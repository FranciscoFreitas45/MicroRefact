public interface AppointmentService {

   public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState);
   public Appointment findByAppointmentId(Long appointmentId);
   public void saveAppointment(Appointment appointment);
   public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasCheckedOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState,boolean hasChecked);
   public long countBySlotDoctorUserIdAndPatientUserIdAndAppointmentStatus(Long doctorId,Long patientId,int appointmentState);
   public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatus(long doctorId,LocalDate date,int appointmentState);
   public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasChecked(long doctorId,LocalDate date,int appointmentState,boolean hasChecked);
}