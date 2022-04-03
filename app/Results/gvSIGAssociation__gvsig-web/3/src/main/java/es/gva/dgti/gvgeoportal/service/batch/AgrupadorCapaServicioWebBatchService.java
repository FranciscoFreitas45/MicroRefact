package es.gva.dgti.gvgeoportal.service.batch;
 import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import org.gvnix.addon.jpa.annotations.batch.GvNIXJpaBatch;
import org.springframework.stereotype.Service;
@Service
@GvNIXJpaBatch(entity = AgrupadorCapaServicioWeb.class)
public class AgrupadorCapaServicioWebBatchService {


}