package DTO;
 import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class JSONDocumentPath {

 private  WindowId windowId;

 private  ProcessId processId;

 private  ViewId viewId;

 private  DocumentId documentId;

 private  DetailId tabId;

 private  DocumentId rowId;

 private  String fieldName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public JSONDocumentPath ofWindowDocumentPath(DocumentPath documentPath,String fieldName){
    final JSONDocumentPathBuilder builder = builder().windowId(documentPath.getWindowId()).documentId(documentPath.getDocumentId()).fieldName(// optional
    fieldName);
    if (documentPath.isRootDocument()) {
        return builder.build();
    } else if (documentPath.isSingleIncludedDocument()) {
        return builder.tabId(documentPath.getDetailId()).rowId(documentPath.getSingleRowId()).build();
    } else {
        throw new IllegalArgumentException("Cannot convert " + documentPath + " to JSON");
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofWindowDocumentPath"))

.queryParam("documentPath",documentPath);
.queryParam("fieldName",fieldName);
JSONDocumentPath aux = restTemplate.getForObject(builder.toUriString(),JSONDocumentPath.class);
return aux;
}


}