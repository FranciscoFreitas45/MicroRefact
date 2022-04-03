package co.edu.uniquindio.gri.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.gri.dao.ProduccionDAO;
@RestController
@RequestMapping("/rest/service")
public class ProduccionController {

@Autowired
 private ProduccionDAO produccionDAO;


@SuppressWarnings("rawtypes")
@GetMapping("/busqueda/{type}/{cadena}")
public List getProduccionBBusqueda(String tipo,String cadena){
    String busqueda = cadena.replaceAll("\\+", " ").toUpperCase();
    return produccionDAO.getProduccionBusqueda(tipo, busqueda);
}


@PostMapping("/producciones/{tipo}/{estado}/{prodId}")
public boolean updateInfoProduccion(String tipo,int estado,Long prodId){
    return produccionDAO.actualizarProducciones(tipo, estado, prodId);
}


@SuppressWarnings("rawtypes")
@GetMapping("/producciones/{type}/{id}/{tipo}")
public List getProducciones(String type,Long entityId,Long tipoId){
    return produccionDAO.getProducciones(type, entityId, tipoId);
}


}