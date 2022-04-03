package org.jugbd.mnet.Interface;
public interface RegisterService {

   public Either<Register,OutdoorRegister> findRegisterEither(Long registerId,RegistrationType registrationType);
   public Register findOne(Long registerId);
}