package DTO;
 import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReference;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class DocumentZoomIntoInfo {

 private  String tableName;

 private  Integer recordId;

 private  WindowId windowId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public DocumentZoomIntoInfo of(String tableName,int id){
    final Integer idObj = id >= 0 ? id : null;
    return builder().tableName(tableName).recordId(idObj).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("tableName",tableName);
.queryParam("id",id);
DocumentZoomIntoInfo aux = restTemplate.getForObject(builder.toUriString(),DocumentZoomIntoInfo.class);
return aux;
}


}