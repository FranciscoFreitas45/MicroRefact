package DTO;
 import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
public interface IDocumentChangesCollector {

 public  ReasonSupplier NONE;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String get()
;

public void setPrimaryChange(DocumentPath documentPath)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPrimaryChange"))

.queryParam("documentPath",documentPath);
restTemplate.put(builder.toUriString(),null);
}
;

}