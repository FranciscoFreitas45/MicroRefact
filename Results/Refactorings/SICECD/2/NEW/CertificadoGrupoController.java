import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CertificadoGrupoController {

 private CertificadoGrupoService certificadogruposervice;


@PutMapping
("/Grupo/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_grupo,@RequestBody List<Certificado> certificados){
certificadogruposervice.setCertificados(pk_id_grupo,certificados);
}


@GetMapping
("/Grupo/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_grupo){
certificadogruposervice.getCertificados(pk_id_grupo);
}


}