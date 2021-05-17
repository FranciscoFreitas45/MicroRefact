public interface PatientService {

   public Patient findByUserId(Long patientId);
   public Patient findByUsername(String username);
}