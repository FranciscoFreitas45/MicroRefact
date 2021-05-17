import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CertificadoGrupoService {

 private CertificadoRep certificadorep;


public void setCertificados(int pk_id_grupo,List<Certificado> certificados){
certificadorep.setCertificados(pk_id_grupo,certificados);
}


public List<Certificado> getCertificados(int pk_id_grupo){
certificadorep.getCertificados(pk_id_grupo);
}


}