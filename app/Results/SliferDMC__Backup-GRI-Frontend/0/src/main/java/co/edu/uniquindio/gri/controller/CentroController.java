package co.edu.uniquindio.gri.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.gri.dao.CentroDAO;
import co.edu.uniquindio.gri.model.Centro;
@RestController
@RequestMapping("/rest/service")
public class CentroController {

@Autowired
 private CentroDAO centroDAO;


@GetMapping("/centros")
public List<Centro> getAllCentros(){
    return centroDAO.getAllCentros();
}


@GetMapping("/centros/{id}")
public ResponseEntity<Centro> getCentroById(Long centroId){
    Centro centro = centroDAO.getCentroById(centroId);
    if (centro == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(centro);
}


}