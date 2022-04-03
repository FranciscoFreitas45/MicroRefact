package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.ProduccionBGrupo;
import co.edu.uniquindio.gri.model.ProduccionGrupo;
import co.edu.uniquindio.gri.model.Tipo;
import co.edu.uniquindio.gri.repository.ProduccionRepository;
import co.edu.uniquindio.gri.repository.TipoRepository;
@Service
public class ProduccionDAO {

@Autowired
 private ProduccionRepository produccionRepository;

@Autowired
 private TipoRepository tipoRepository;


public boolean actualizarProducciones(String tipo,int estado,Long prodId){
    if (tipo.equals("3")) {
        if (estado == 0) {
            produccionRepository.updateProduccionBGrupo(prodId, 1);
            return true;
        } else {
            produccionRepository.updateProduccionBGrupo(prodId, 0);
            return true;
        }
    } else {
        if (estado == 0) {
            produccionRepository.updateProduccionGrupo(prodId, 1);
            return true;
        } else {
            produccionRepository.updateProduccionGrupo(prodId, 0);
            return true;
        }
    }
}


@SuppressWarnings({ "rawtypes" })
public List getProduccionBusqueda(String tipo,String cadena){
    if (tipo.equals("g")) {
        return produccionRepository.getProduccionGBusqueda(cadena);
    } else {
        return produccionRepository.getProduccionBusqueda(cadena);
    }
}


@SuppressWarnings("rawtypes")
public List getAllProducciones(Long id){
    return produccionRepository.getAllProducciones(id);
}


@SuppressWarnings("rawtypes")
public List getProducciones(String type,Long entityId,Long tipoId){
    Tipo tipo = tipoRepository.findOne(tipoId);
    long idTipoProd = tipo.getTipoProduccion().getId();
    if (type.equals("i")) {
        if (idTipoProd == 3) {
            return produccionRepository.getProduccionB(entityId, tipoId);
        } else if (tipoId == 1) {
            return produccionRepository.getTrabajosDirigidos(entityId);
        } else {
            return produccionRepository.getProduccion(entityId, tipoId);
        }
    } else if (type.equals("g")) {
        if (idTipoProd == 3) {
            return produccionRepository.getProduccionBGrupo(entityId, tipoId);
        } else if (tipoId == 1) {
            return produccionRepository.getTrabajosDirigidosGrupo(entityId);
        } else {
            return produccionRepository.getProduccionGrupo(entityId, tipoId);
        }
    } else if (type.equals("p")) {
        if (idTipoProd == 3) {
            return produccionRepository.getProduccionBPrograma(entityId, tipoId);
        } else if (tipoId == 1) {
            return produccionRepository.getTrabajosDirigidosPrograma(entityId);
        } else {
            return produccionRepository.getProduccionPrograma(entityId, tipoId);
        }
    } else if (type.equals("c")) {
        if (idTipoProd == 3) {
            return produccionRepository.getProduccionBCentro(entityId, tipoId);
        } else if (tipoId == 1) {
            return produccionRepository.getTrabajosDirigidosCentro(entityId);
        } else {
            return produccionRepository.getProduccionCentro(entityId, tipoId);
        }
    } else if (type.equals("f")) {
        if (idTipoProd == 3) {
            List<ProduccionBGrupo> prod_programas = produccionRepository.getProduccionBFacultadPrograma(entityId, tipoId);
            List<ProduccionBGrupo> prod_centros = produccionRepository.getProduccionBFacultadCentro(entityId, tipoId);
            for (ProduccionBGrupo produccion : prod_centros) {
                if (!prod_programas.contains(produccion)) {
                    prod_programas.add(produccion);
                }
            }
            return prod_programas;
        } else if (tipoId == 1) {
            List<ProduccionGrupo> prod_programas = produccionRepository.getTrabajosDirigidosFacultadPrograma(entityId);
            List<ProduccionGrupo> prod_centros = produccionRepository.getTrabajosDirigidosFacultadCentro(entityId);
            for (ProduccionGrupo produccion : prod_centros) {
                if (!prod_programas.contains(produccion)) {
                    prod_programas.add(produccion);
                }
            }
            return prod_programas;
        } else {
            List<ProduccionGrupo> prod_programas = produccionRepository.getProduccionFacultadPrograma(entityId, tipoId);
            List<ProduccionGrupo> prod_centros = produccionRepository.getProduccionFacultadCentro(entityId, tipoId);
            for (ProduccionGrupo produccion : prod_centros) {
                if (!prod_programas.contains(produccion)) {
                    prod_programas.add(produccion);
                }
            }
            return prod_programas;
        }
    } else {
        if (idTipoProd == 3) {
            return produccionRepository.getProduccionBGeneral(tipoId);
        } else if (tipoId == 1) {
            return produccionRepository.getTrabajosDirigidosGeneral();
        } else {
            return produccionRepository.getProduccionGeneral(tipoId);
        }
    }
}


}