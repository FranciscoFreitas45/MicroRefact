package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.CertificadoRep;
import com.cocay.sicecd.model.Certificado;
@Service
public class CertificadoGrupoService {

@Autowired
 private CertificadoRep certificadorep;


public void setCertificados(int pk_id_grupo,List<Certificado> certificados){
certificadorep.setCertificados(pk_id_grupo,certificados);
}


public List<Certificado> getCertificados(int pk_id_grupo){
return certificadorep.getCertificados(pk_id_grupo);
}


}