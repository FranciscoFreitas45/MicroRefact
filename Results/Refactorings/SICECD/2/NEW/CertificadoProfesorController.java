import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CertificadoProfesorController {

 private CertificadoProfesorService certificadoprofesorservice;


@PutMapping
("/Profesor/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_profesor,@RequestBody List<Certificado> certificados){
certificadoprofesorservice.setCertificados(pk_id_profesor,certificados);
}


@GetMapping
("/Profesor/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_profesor){
certificadoprofesorservice.getCertificados(pk_id_profesor);
}


}