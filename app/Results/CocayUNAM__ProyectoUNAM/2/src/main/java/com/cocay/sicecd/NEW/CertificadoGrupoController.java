package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Certificado;
@RestController
@CrossOrigin
public class CertificadoGrupoController {

@Autowired
 private CertificadoGrupoService certificadogruposervice;


@PutMapping
("/Grupo/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_grupo,@RequestBody List<Certificado> certificados){
certificadogruposervice.setCertificados(pk_id_grupo,certificados);
}


@GetMapping
("/Grupo/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_grupo){
return certificadogruposervice.getCertificados(pk_id_grupo);
}


}