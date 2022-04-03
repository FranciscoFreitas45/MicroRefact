package de.metas.ui.web.pickingV2.productsToPick.rows;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Locator;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickFrom;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.PickRequest;
import de.metas.handlingunits.picking.requests.PickRequest.IssueToPickingOrderRequest;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.pickingV2.config.PickingConfigRepositoryV2;
import de.metas.ui.web.pickingV2.config.PickingConfigV2;
import de.metas.ui.web.pickingV2.packageable.PackageableRow;
import de.metas.ui.web.pickingV2.productsToPick.rows.factory.ProductsToPickRowsDataFactory;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class ProductsToPickRowsService {

 private  PickingConfigRepositoryV2 pickingConfigRepo;

 private  IBPartnerBL bpartnersService;

 private  HUReservationService huReservationService;

 private  PickingCandidateService pickingCandidateService;

 private  AdMessageKey MSG_TYPE_UNALLOCATED;

 private  AdMessageKey MSG_TYPE_NOT_SUPPORTED;


public PickRequest.IssueToPickingOrderRequest toIssueToPickingOrderRequest(ProductsToPickRow issueRow){
    final HuId issueFromHUId = issueRow.getPickFromHUId();
    if (issueFromHUId == null) {
        throw new AdempiereException("No HU to issue from for product " + issueRow.getProductName().getDefaultValue());
    }
    return PickRequest.IssueToPickingOrderRequest.builder().issueToOrderBOMLineId(issueRow.getIssueToOrderBOMLineId()).issueFromHUId(issueFromHUId).productId(issueRow.getProductId()).qtyToIssue(issueRow.getQtyEffective()).build();
}


public ProductsToPickRowsData createProductsToPickRowsData(PackageableRow packageableRow){
    return newProductsToPickRowsFactory().create(packageableRow);
}


public ProductsToPickRowsDataFactory newProductsToPickRowsFactory(){
    final PickingConfigV2 pickingConfig = pickingConfigRepo.getPickingConfig();
    return ProductsToPickRowsDataFactory.builder().bpartnersService(bpartnersService).huReservationService(huReservationService).pickingCandidateService(pickingCandidateService).locatorLookup(LookupDataSourceFactory.instance.searchInTableLookup(I_M_Locator.Table_Name)).considerAttributes(pickingConfig.isConsiderAttributes()).build();
}


public PickRequest createPickRequest(ProductsToPickRow row,boolean isPickingReviewRequired){
    final ProductsToPickRowType rowType = row.getType();
    if (ProductsToPickRowType.PICK_FROM_HU.equals(rowType)) {
        return PickRequest.builder().shipmentScheduleId(row.getShipmentScheduleId()).qtyToPick(row.getQtyEffective()).pickFrom(PickFrom.ofHuId(row.getPickFromHUId())).autoReview(!isPickingReviewRequired).build();
    } else if (ProductsToPickRowType.PICK_FROM_PICKING_ORDER.equals(rowType)) {
        final ImmutableList<IssueToPickingOrderRequest> issues = row.getIncludedRows().stream().map(issueRow -> toIssueToPickingOrderRequest(issueRow)).collect(ImmutableList.toImmutableList());
        return PickRequest.builder().shipmentScheduleId(row.getShipmentScheduleId()).qtyToPick(row.getQtyEffective()).pickFrom(PickFrom.ofPickingOrderId(row.getPickFromPickingOrderId())).issuesToPickingOrder(issues).autoReview(!isPickingReviewRequired).build();
    } else if (ProductsToPickRowType.UNALLOCABLE.equals(rowType)) {
        final ITranslatableString message = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_TYPE_UNALLOCATED);
        throw new AdempiereException(message);
    } else {
        final ITranslatableString message = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_TYPE_NOT_SUPPORTED);
        throw new AdempiereException(message);
    }
}


public List<PickingCandidate> createPickingCandidates(PackageableRow packageableRow){
    final ProductsToPickRowsData productsToPickRowsData = createProductsToPickRowsData(packageableRow);
    return productsToPickRowsData.getAllRows().stream().map(productsToPickRow -> pickingCandidateService.createAndSavePickingCandidates(createPickRequest(productsToPickRow, false))).map(pickHUResult -> pickHUResult.getPickingCandidate()).collect(ImmutableList.toImmutableList());
}


}