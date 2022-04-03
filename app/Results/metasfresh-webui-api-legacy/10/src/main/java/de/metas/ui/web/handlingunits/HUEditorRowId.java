package de.metas.ui.web.handlingunits;
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
@Immutable
@EqualsAndHashCode
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


public HUEditorRowId ofTopLevelHU(HuId topLevelHUId){
    return new HUEditorRowId(topLevelHUId, // storageProductId
    (ProductId) null, // topLevelHUId parameter
    (HuId) null, // json, to be computed when needed
    (String) null, // to be computed when needed
    (DocumentId) null);
}


public boolean isHU(){
    return storageProductId == null;
}


public boolean isTopLevel(){
    return huId != null && topLevelHUId == null && storageProductId == null;
}


public HUEditorRowId ofDocumentId(DocumentId documentId){
    return fromJson(documentId.toJson(), documentId);
}


public HUEditorRowId ofHUStorage(HuId huId,HuId topLevelHUId,ProductId storageProductId){
    // to be computed when needed
    final String json = null;
    // to be computed when needed
    final DocumentId documentId = null;
    return new HUEditorRowId(huId, storageProductId, topLevelHUId, json, documentId);
}


public DocumentIdsSelection rowIdsFromTopLevelHuIds(Collection<HuId> huIds){
    final HuId topLevelHUId = null;
    return huIds.stream().map(huId -> ofHU(huId, topLevelHUId)).map(HUEditorRowId::toDocumentId).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


public HUEditorRowId toTopLevelRowId(){
    if (isTopLevel()) {
        return this;
    }
    final HuId huId = getTopLevelHUId();
    final ProductId storageProductId = null;
    final HuId topLevelHUId = null;
    final String json = null;
    final DocumentId documentId = null;
    return new HUEditorRowId(huId, storageProductId, topLevelHUId, json, documentId);
}


public String toJson(HuId huId,ProductId storageProductId,HuId topLevelHUId){
    // IMPORTANT: top level row shall be perfectly convertible to integers, else, a lot of APIs could fail
    final String idStrPart;
    if (storageProductId != null) {
        idStrPart = huId.getRepoId() + ID_SEPARATOR + storageProductId.getRepoId();
    } else {
        idStrPart = String.valueOf(huId.getRepoId());
    }
    final String topLevelHUIdPart = topLevelHUId != null ? PREFIX_TopLevelHUId + topLevelHUId.getRepoId() : null;
    return PARTS_JOINER.join(idStrPart, topLevelHUIdPart);
}


public DocumentId toDocumentId(){
    DocumentId documentId = _documentId;
    if (documentId == null) {
        documentId = _documentId = DocumentId.of(toJson());
    }
    return documentId;
}


public HUEditorRowId ofHU(HuId huId,HuId topLevelHUId){
    final ProductId storageProductId = null;
    // to be computed when needed
    final String json = null;
    // to be computed when needed
    final DocumentId documentId = null;
    return new HUEditorRowId(huId, storageProductId, topLevelHUId, json, documentId);
}


public HUEditorRowId fromJson(String json,DocumentId documentId){
    // 
    // Split json to parts
    final Iterator<String> partsIterator;
    {
        final List<String> parts = PARTS_SPLITTER.splitToList(json);
        if (parts.isEmpty()) {
            throw new IllegalArgumentException("Invalid HU rowId: " + json);
        }
        partsIterator = parts.iterator();
    }
    // 
    // huId and storageProductId
    final HuId huId;
    final ProductId storageProductId;
    {
        final String idStrPart = partsIterator.next();
        final List<String> idParts = ID_SPLITTER.splitToList(idStrPart);
        if (idParts.size() == 1) {
            huId = HuId.ofRepoId(Integer.parseInt(idParts.get(0)));
            storageProductId = null;
        } else if (idParts.size() == 2) {
            huId = HuId.ofRepoId(Integer.parseInt(idParts.get(0)));
            storageProductId = ProductId.ofRepoId(Integer.parseInt(idParts.get(1)));
        } else {
            throw new IllegalArgumentException("Invalid HU rowId: " + json + ". Cannot parse ID part: " + idStrPart);
        }
    }
    // 
    // Others
    HuId topLevelHUId = null;
    while (partsIterator.hasNext()) {
        final String part = partsIterator.next();
        if (part.startsWith(PREFIX_TopLevelHUId)) {
            final String topLevelHUIdStr = part.substring(PREFIX_TopLevelHUId.length());
            topLevelHUId = HuId.ofRepoId(Integer.parseInt(topLevelHUIdStr));
        } else {
            throw new IllegalArgumentException("Invalid HU rowId: " + json + ". Cannot parse part: " + part);
        }
    }
    return new HUEditorRowId(huId, storageProductId, topLevelHUId, json, documentId);
}


public Set<HuId> extractHUIdsOnly(DocumentIdsSelection rowIds){
    return rowIds.stream().map(HUEditorRowId::ofDocumentId).map(HUEditorRowId::getHuId).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
}


public ProductId getStorageProductId(){
    return storageProductId;
}


@Override
public String toString(){
    return toJson();
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


}