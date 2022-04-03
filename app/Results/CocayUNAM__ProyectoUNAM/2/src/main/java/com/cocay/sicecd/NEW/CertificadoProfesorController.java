package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Certificado;
@RestController
@CrossOrigin
public class CertificadoProfesorController {

@Autowired
 private CertificadoProfesorService certificadoprofesorservice;


@PutMapping
("/Profesor/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_profesor,@RequestBody List<Certificado> certificados){
certificadoprofesorservice.setCertificados(pk_id_profesor,certificados);
}


@GetMapping
("/Profesor/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_profesor){
return certificadoprofesorservice.getCertificados(pk_id_profesor);
}


}