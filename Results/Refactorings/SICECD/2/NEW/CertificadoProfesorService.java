import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CertificadoProfesorService {

 private CertificadoRep certificadorep;


public void setCertificados(int pk_id_profesor,List<Certificado> certificados){
certificadorep.setCertificados(pk_id_profesor,certificados);
}


public List<Certificado> getCertificados(int pk_id_profesor){
certificadorep.getCertificados(pk_id_profesor);
}


}