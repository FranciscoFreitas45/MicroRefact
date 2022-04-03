package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Patient;
public interface PatientRequest {

   public Vital setPatient(Patient patient,Long id);
   public Patient getPatient(Long id);
}