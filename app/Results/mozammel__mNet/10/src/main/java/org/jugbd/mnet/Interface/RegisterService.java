package org.jugbd.mnet.Interface;
public interface RegisterService {

   public Register findOne(Long registerId);
   public OutdoorRegister save(OutdoorRegister register);
   public OutdoorRegister findOpdRegister(Long id);
}