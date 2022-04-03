package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Curso;
@RestController
@CrossOrigin
public class CursoCertificadoController {

@Autowired
 private CursoCertificadoService cursocertificadoservice;


@GetMapping
("/Certificado/{id}/Curso/getFk_id_curso")
public Curso getFk_id_curso(@PathVariable(name="id") int pk_id_curso){
return cursocertificadoservice.getFk_id_curso(pk_id_curso);
}


@PutMapping
("/Certificado/{id}/Curso/setFk_id_curso")
public void setFk_id_curso(@PathVariable(name="id") int pk_id_curso,@RequestBody Curso fk_id_curso){
cursocertificadoservice.setFk_id_curso(pk_id_curso,fk_id_curso);
}


}