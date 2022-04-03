package DTO;
 import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Joiner;
import java.util.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class HUEditorRowId {

 private  String PREFIX_TopLevelHUId;

 private  String ID_SEPARATOR;

 private  Splitter ID_SPLITTER;

 private  String PARTS_SEPARATOR;

 private  Joiner PARTS_JOINER;

 private  Splitter PARTS_SPLITTER;

 private  HuId huId;

 private  ProductId storageProductId;

 private  HuId topLevelHUId;

 private  String _json;

 private  DocumentId _documentId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public ProductId getStorageProductId(){
    return storageProductId;
}


public HuId getTopLevelHUId(){
    if (topLevelHUId != null) {
        return topLevelHUId;
    }
    return huId;
}


public HuId getHuId(){
    return huId;
}


public DocumentId toDocumentId(){
    DocumentId documentId = _documentId;
    if (documentId == null) {
        documentId = _documentId = DocumentId.of(toJson());
    }
    return documentId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentId"))

DocumentId aux = restTemplate.getForObject(builder.toUriString(),DocumentId.class);
return aux;
}


public HUEditorRowId ofHUStorage(HuId huId,HuId topLevelHUId,ProductId storageProductId){
    // to be computed when needed
    final String json = null;
    // to be computed when needed
    final DocumentId documentId = null;
    return new HUEditorRowId(huId, storageProductId, topLevelHUId, json, documentId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofHUStorage"))

.queryParam("huId",huId);
.queryParam("topLevelHUId",topLevelHUId);
.queryParam("storageProductId",storageProductId);
HUEditorRowId aux = restTemplate.getForObject(builder.toUriString(),HUEditorRowId.class);
return aux;
}


}