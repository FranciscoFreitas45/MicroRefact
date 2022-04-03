package es.gva.dgti.gvgeoportal.comparator;
 import java.io.Serializable;
import java.util.Comparator;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
public class OrdenarCapasServicioWebPorNombreCapa implements Serializable,Comparator<CapasServicioWeb>{


@Override
public int compare(CapasServicioWeb capaServicioWeb1,CapasServicioWeb capaServicioWeb2){
    return capaServicioWeb1.getNombreCapa().compareTo(capaServicioWeb2.getNombreCapa());
}


}