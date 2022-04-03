package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Certificado;
@RestController
@CrossOrigin
public class CertificadoCursoController {

@Autowired
 private CertificadoCursoService certificadocursoservice;


@PutMapping
("/Curso/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_curso,@RequestBody List<Certificado> certificados){
certificadocursoservice.setCertificados(pk_id_curso,certificados);
}


@GetMapping
("/Curso/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_curso){
return certificadocursoservice.getCertificados(pk_id_curso);
}


}