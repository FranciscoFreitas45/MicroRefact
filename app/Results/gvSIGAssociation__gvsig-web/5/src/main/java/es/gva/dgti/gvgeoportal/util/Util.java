package es.gva.dgti.gvgeoportal.util;
 import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
public class Util {


public StringBuffer getParamsFinder(Map<String,String> params){
    StringBuffer paramsFinder = new StringBuffer("");
    Iterator<Entry<String, String>> iter = params.entrySet().iterator();
    while (iter.hasNext()) {
        Entry<String, String> entry = iter.next();
        if (entry.getKey().contains("Finder")) {
            paramsFinder.append("&amp;");
            paramsFinder.append(entry.getKey());
            paramsFinder.append('=');
            paramsFinder.append(entry.getValue());
        }
    }
    return paramsFinder;
}


public boolean checkIfTransparencySelected(String transparencia){
    return transparencia.equals(Constants.SELECCIONAR_TRANSPARENCIA);
}


public boolean isNumeric(String str){
    try {
        double d = Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}


public String getSelectedLayersString(Set<AgrupadorCapa> listadoGeoPortalCapa){
    String selectedGroupLayers = "";
    if (listadoGeoPortalCapa != null && listadoGeoPortalCapa.size() > 0) {
        StringBuffer gruposCapasGeoPortal = new StringBuffer("");
        gruposCapasGeoPortal.append(StringPool.OPEN_CURLY_BRACE);
        Iterator<AgrupadorCapa> iter = listadoGeoPortalCapa.iterator();
        while (iter.hasNext()) {
            AgrupadorCapa agrupadorCapa = iter.next();
            long idGrupo = agrupadorCapa.getId();
            gruposCapasGeoPortal.append(StringPool.QUOTE);
            gruposCapasGeoPortal.append(idGrupo);
            gruposCapasGeoPortal.append(StringPool.QUOTE);
            gruposCapasGeoPortal.append(StringPool.COLON);
            gruposCapasGeoPortal.append(idGrupo);
            // anayado comma si ultimo objeto
            if (iter.hasNext()) {
                gruposCapasGeoPortal.append(StringPool.COMMA);
            }
        }
        gruposCapasGeoPortal.append(StringPool.CLOSE_CURLY_BRACE);
        selectedGroupLayers = gruposCapasGeoPortal.toString();
    }
    return selectedGroupLayers;
}


}