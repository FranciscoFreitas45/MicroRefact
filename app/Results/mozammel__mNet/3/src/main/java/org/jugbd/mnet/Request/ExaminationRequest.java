package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Examination;
public interface ExaminationRequest {

   public Examination getExamination(Long id);
   public OutdoorRegister setExamination(Examination examination,Long id);
}