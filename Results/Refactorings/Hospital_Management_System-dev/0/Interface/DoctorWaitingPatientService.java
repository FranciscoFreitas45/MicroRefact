public interface DoctorWaitingPatientService {

   public List<DoctorWaitingPatient> findAllByDate(LocalDate date);
}