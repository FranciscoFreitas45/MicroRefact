package de.metas.ui.web.pickingV2.productsToPick.rows;
 import javax.annotation.Nullable;
import de.metas.handlingunits.HuId;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@Value
@ToString(of = "documentId")
public class ProductsToPickRowId {

 private ProductId productId;

 private ShipmentScheduleId shipmentScheduleId;

 private HuId pickFromHUId;

 private PPOrderId pickFromPickingOrderId;

 private PPOrderBOMLineId issueToOrderBOMLineId;

@Getter(AccessLevel.NONE)
 private DocumentId documentId;


public DocumentId toDocumentId(){
    return documentId;
}


public DocumentId createDocumentId(ProductId productId,ShipmentScheduleId shipmentScheduleId,HuId pickFromHUId,PPOrderId pickFromPickingOrderId,PPOrderBOMLineId issueToOrderBOMLineId){
    final StringBuilder sb = new StringBuilder();
    sb.append("P").append(productId.getRepoId());
    sb.append("_").append("S").append(shipmentScheduleId.getRepoId());
    if (pickFromHUId != null) {
        sb.append("_").append("HU").append(pickFromHUId.getRepoId());
    }
    if (pickFromPickingOrderId != null) {
        sb.append("_").append("MO").append(pickFromPickingOrderId.getRepoId());
    }
    if (issueToOrderBOMLineId != null) {
        sb.append("_").append("BOML").append(issueToOrderBOMLineId.getRepoId());
    }
    return DocumentId.ofString(sb.toString());
}


}