package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Profesor;
@RestController
@CrossOrigin
public class ProfesorCertificadoController {

@Autowired
 private ProfesorCertificadoService profesorcertificadoservice;


@GetMapping
("/Certificado/{id}/Profesor/getFk_id_profesor")
public Profesor getFk_id_profesor(@PathVariable(name="id") int pk_id_profesor){
return profesorcertificadoservice.getFk_id_profesor(pk_id_profesor);
}


@PutMapping
("/Certificado/{id}/Profesor/setFk_id_profesor")
public void setFk_id_profesor(@PathVariable(name="id") int pk_id_profesor,@RequestBody Profesor fk_id_profesor){
profesorcertificadoservice.setFk_id_profesor(pk_id_profesor,fk_id_profesor);
}


}