package org.jugbd.mnet.Interface;
public interface RegisterService {

   public Register findOne(Long registerId);
   public void update(OutdoorRegister register);
}