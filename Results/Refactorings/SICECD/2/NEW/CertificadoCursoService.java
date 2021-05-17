import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CertificadoCursoService {

 private CertificadoRep certificadorep;


public void setCertificados(int pk_id_curso,List<Certificado> certificados){
certificadorep.setCertificados(pk_id_curso,certificados);
}


public List<Certificado> getCertificados(int pk_id_curso){
certificadorep.getCertificados(pk_id_curso);
}


}