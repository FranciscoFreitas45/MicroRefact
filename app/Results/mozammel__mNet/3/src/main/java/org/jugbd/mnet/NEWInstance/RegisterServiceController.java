package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RegisterServiceController {

 private RegisterService registerservice;


@GetMapping
("/findOne")
public Register findOne(@RequestParam(name = "registerId") Long registerId){
  return registerservice.findOne(registerId);
}


@GetMapping
("/save")
public OutdoorRegister save(@RequestParam(name = "register") OutdoorRegister register){
  return registerservice.save(register);
}


@GetMapping
("/findRegisterEither")
public Either<Register,OutdoorRegister> findRegisterEither(@RequestParam(name = "registerId") Long registerId,@RequestParam(name = "registrationType") RegistrationType registrationType){
  return registerservice.findRegisterEither(registerId,registrationType);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "register") OutdoorRegister register){
registerservice.update(register);
}


@GetMapping
("/findAllRegisterByPatientId")
public List<Register> findAllRegisterByPatientId(@RequestParam(name = "patientId") Long patientId){
  return registerservice.findAllRegisterByPatientId(patientId);
}


@GetMapping
("/findAllOutdoorRegisterByPatientId")
public List<OutdoorRegister> findAllOutdoorRegisterByPatientId(@RequestParam(name = "patientId") Long patientId){
  return registerservice.findAllOutdoorRegisterByPatientId(patientId);
}


@GetMapping
("/findOpdRegister")
public OutdoorRegister findOpdRegister(@RequestParam(name = "id") Long id){
  return registerservice.findOpdRegister(id);
}


}