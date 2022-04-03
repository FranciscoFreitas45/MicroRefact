package de.metas.ui.web.pporder;
 import java.util.List;
import javax.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
@EqualsAndHashCode
public class PPOrderLineRowId {

@Getter
 private  int recordId;

@Getter
 private  PPOrderLineRowType type;

@Getter
 private  DocumentId parentRowId;

 private  DocumentId _documentId;

 static  String PARTS_SEPARATOR;

 private  Splitter PARTS_SPLITTER;


public PPOrderLineRowId ofPPOrderId(int ppOrderId){
    Preconditions.checkArgument(ppOrderId > 0, "ppOrderId > 0");
    return new PPOrderLineRowId(PPOrderLineRowType.PP_Order, null, ppOrderId);
}


public PPOrderLineRowId ofSourceHU(DocumentId parentRowId,HuId sourceHuId){
    return new PPOrderLineRowId(PPOrderLineRowType.Source_HU, parentRowId, sourceHuId.getRepoId());
}


public PPOrderLineRowId ofPPOrderBomLineId(int ppOrderBomLineId){
    Preconditions.checkArgument(ppOrderBomLineId > 0, "ppOrderBomLineId > 0");
    return new PPOrderLineRowId(PPOrderLineRowType.PP_OrderBomLine, null, ppOrderBomLineId);
}


public DocumentId toDocumentId(){
    DocumentId documentId = _documentId;
    if (documentId == null) {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.getCode());
        sb.append(PARTS_SEPARATOR).append(parentRowId);
        sb.append(PARTS_SEPARATOR).append(recordId);
        documentId = DocumentId.ofString(sb.toString());
    }
    return documentId;
}


public PPOrderLineRowId ofIssuedOrReceivedHU(DocumentId parentRowId,HuId huId){
    return new PPOrderLineRowId(PPOrderLineRowType.IssuedOrReceivedHU, parentRowId, huId.getRepoId());
}


public PPOrderLineRowId fromDocumentId(DocumentId documentId){
    final List<String> parts = PARTS_SPLITTER.splitToList(documentId.toJson());
    return fromStringPartsList(parts);
}


public PPOrderLineRowId fromStringPartsList(List<String> parts){
    final int partsCount = parts.size();
    if (partsCount < 1) {
        throw new IllegalArgumentException("Invalid id: " + parts);
    }
    final PPOrderLineRowType type = PPOrderLineRowType.forCode(parts.get(0));
    final DocumentId parentRowId = !Check.isEmpty(parts.get(1), true) ? DocumentId.of(parts.get(1)) : null;
    final int recordId = Integer.parseInt(parts.get(2));
    return new PPOrderLineRowId(type, parentRowId, recordId);
}


}