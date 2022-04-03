package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.MedicalHistory;
public interface MedicalHistoryRequest {

   public MedicalHistory getMedicalHistory(Long id);
   public void setMedicalHistory(MedicalHistory medicalHistory,Long id);
}