package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.List;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import de.metas.purchasecandidate.PurchaseCandidate;
import de.metas.purchasecandidate.PurchaseCandidateId;
import de.metas.purchasecandidate.PurchaseCandidateRepository;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.PurchaseDemandWithCandidatesService;
import de.metas.purchasecandidate.availability.AvailabilityCheckService;
import de.metas.purchasecandidate.purchaseordercreation.localorder.PurchaseCandidateAggregate;
import de.metas.purchasecandidate.purchaseordercreation.localorder.PurchaseCandidateAggregator;
import de.metas.ui.web.order.sales.purchasePlanning.process.WEBUI_PurchaseCandidates_PurchaseView_Launcher;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.NonNull;
@ViewFactory(windowId = PurchaseCandidates2PurchaseViewFactory.WINDOW_ID_STRING)
public class PurchaseCandidates2PurchaseViewFactory extends PurchaseViewFactoryTemplate{

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  PurchaseCandidateRepository purchaseCandidatesRepo;


@Override
public List<PurchaseDemand> getDemands(CreateViewRequest request){
    final Set<PurchaseCandidateId> purchaseCandidateIds = PurchaseCandidateId.ofRepoIds(request.getFilterOnlyIds());
    Check.assumeNotEmpty(purchaseCandidateIds, "purchaseCandidateIds is not empty");
    final List<PurchaseCandidate> purchaseCandidates = purchaseCandidatesRepo.getAllByIds(purchaseCandidateIds);
    Check.assumeNotEmpty(purchaseCandidates, "purchaseCandidates is not empty");
    return PurchaseCandidateAggregator.aggregate(purchaseCandidates).stream().map(aggregate -> toPurchaseDemand(aggregate)).collect(ImmutableList.toImmutableList());
}


public PurchaseDemand toPurchaseDemand(PurchaseCandidateAggregate aggregate){
    return PurchaseDemand.builder().existingPurchaseCandidateIds(aggregate.getPurchaseCandidateIds()).orgId(aggregate.getOrgId()).warehouseId(aggregate.getWarehouseId()).productId(aggregate.getProductId()).attributeSetInstanceId(aggregate.getAttributeSetInstanceId()).qtyToDeliver(aggregate.getQtyToDeliver()).salesPreparationDate(aggregate.getDatePromised()).build();
}


@Override
public void onViewClosedByUser(PurchaseView purchaseView){
    final List<PurchaseRow> rows = purchaseView.getRows();
    PurchaseRowsSaver.builder().purchaseCandidatesRepo(purchaseCandidatesRepo).build().save(rows);
}


}