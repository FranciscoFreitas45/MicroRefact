package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Grupo;
@RestController
@CrossOrigin
public class GrupoCertificadoController {

@Autowired
 private GrupoCertificadoService grupocertificadoservice;


@PutMapping
("/Certificado/{id}/Grupo/setFk_id_grupo")
public void setFk_id_grupo(@PathVariable(name="id") int pk_id_grupo,@RequestBody Grupo fk_id_grupo){
grupocertificadoservice.setFk_id_grupo(pk_id_grupo,fk_id_grupo);
}


@GetMapping
("/Certificado/{id}/Grupo/getFk_id_grupo")
public Grupo getFk_id_grupo(@PathVariable(name="id") int pk_id_grupo){
return grupocertificadoservice.getFk_id_grupo(pk_id_grupo);
}


}