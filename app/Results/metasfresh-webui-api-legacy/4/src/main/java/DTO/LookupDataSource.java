package DTO;
 import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import org.compiere.util.Evaluatee;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
public interface LookupDataSource extends LookupValueByIdSupplier{

 private int FIRST_ROW;

 private int DEFAULT_PageLength;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public List<CCacheStats> getCacheStats()
;

public DocumentZoomIntoInfo getDocumentZoomInto(int id)
;

public Optional<WindowId> getZoomIntoWindowId()
;

public LookupValuesList findEntities(Evaluatee ctx){
    return findEntities(ctx, Integer.MAX_VALUE);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findEntities"))

.queryParam("ctx",ctx);
LookupValuesList aux = restTemplate.getForObject(builder.toUriString(),LookupValuesList.class);
return aux;
}
;

@Override
public LookupValue findById(Object id)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))

.queryParam("id",id);
LookupValue aux = restTemplate.getForObject(builder.toUriString(),LookupValue.class);
return aux;
}
;

}