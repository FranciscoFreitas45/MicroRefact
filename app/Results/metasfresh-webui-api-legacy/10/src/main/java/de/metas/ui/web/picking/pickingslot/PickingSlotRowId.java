package de.metas.ui.web.picking.pickingslot;
 import java.util.List;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import de.metas.handlingunits.HuId;
import de.metas.picking.api.PickingSlotId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@EqualsAndHashCode
public class PickingSlotRowId {

@Getter
 private  PickingSlotId pickingSlotId;

@Getter
 private  HuId huId;

@Getter
 private  int huStorageProductId;

 private  DocumentId _documentId;

 private  String SEPARATOR;

 private  Joiner DOCUMENT_ID_JOINER;

 private  Splitter DOCUMENT_ID_SPLITTER;


public PickingSlotRowId ofSourceHU(HuId huId){
    final PickingSlotId pickingSlotId = null;
    final int huStorageProductId = -1;
    return new PickingSlotRowId(pickingSlotId, huId, huStorageProductId);
}


public PickingSlotRowId ofPickingSlotId(PickingSlotId pickingSlotId){
    final HuId huId = null;
    final int huStorageProductId = -1;
    return new PickingSlotRowId(pickingSlotId, huId, huStorageProductId);
}


public DocumentId toDocumentId(){
    DocumentId id = _documentId;
    if (id == null) {
        final String idStr = DOCUMENT_ID_JOINER.join(pickingSlotId != null ? pickingSlotId.getRepoId() : 0, huId != null ? huId.getRepoId() : null, huStorageProductId > 0 ? huStorageProductId : null);
        id = _documentId = DocumentId.ofString(idStr);
    }
    return id;
}


public boolean isPickingSlotRow(){
    return getPickingSlotId() != null && getHuId() == null;
}


@Override
public String toString(){
    return toDocumentId().toJson();
}


public PickingSlotRowId fromDocumentId(DocumentId documentId){
    final List<String> parts = DOCUMENT_ID_SPLITTER.splitToList(documentId.toJson());
    return fromStringPartsList(parts);
}


public boolean isPickedHURow(){
    return getPickingSlotId() != null && getHuId() != null;
}


public boolean isPickingSourceHURow(){
    return getPickingSlotId() == null && getHuId() != null;
}


public PickingSlotRowId ofPickedHU(PickingSlotId pickingSlotId,HuId huId,int huStorageProductId){
    return new PickingSlotRowId(pickingSlotId, huId, huStorageProductId);
}


public PickingSlotRowId fromStringPartsList(List<String> parts){
    final int partsCount = parts.size();
    if (partsCount < 1) {
        throw new IllegalArgumentException("Invalid id: " + parts);
    }
    final PickingSlotId pickingSlotId = !Check.isEmpty(parts.get(0), true) ? PickingSlotId.ofRepoIdOrNull(Integer.parseInt(parts.get(0))) : null;
    final HuId huId = partsCount >= 2 ? HuId.ofRepoIdOrNull(Integer.parseInt(parts.get(1))) : null;
    final int huStorageProductId = partsCount >= 3 ? Integer.parseInt(parts.get(2)) : 0;
    return new PickingSlotRowId(pickingSlotId, huId, huStorageProductId);
}


}