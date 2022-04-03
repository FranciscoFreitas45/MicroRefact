package org.jugbd.mnet.Interface;
public interface RegisterService {

   public List<Register> findAllRegisterByPatientId(Long patientId);
   public List<OutdoorRegister> findAllOutdoorRegisterByPatientId(Long patientId);
}