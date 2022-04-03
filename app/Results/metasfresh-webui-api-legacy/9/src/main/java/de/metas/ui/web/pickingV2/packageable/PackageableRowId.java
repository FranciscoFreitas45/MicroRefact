package de.metas.ui.web.pickingV2.packageable;
 import org.adempiere.warehouse.WarehouseTypeId;
import com.google.common.base.Joiner;
import de.metas.order.OrderId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
@Value(staticConstructor = "of")
public class PackageableRowId {

@NonNull
 private OrderId orderId;

 private WarehouseTypeId warehouseTypeId;

@Getter(lazy = true)
 private DocumentId documentId;


public DocumentId createDocumentId(){
    final String documentIdStr = Joiner.on("_").skipNulls().join(orderId.getRepoId(), warehouseTypeId != null ? warehouseTypeId.getRepoId() : null);
    return DocumentId.of(documentIdStr);
}


}