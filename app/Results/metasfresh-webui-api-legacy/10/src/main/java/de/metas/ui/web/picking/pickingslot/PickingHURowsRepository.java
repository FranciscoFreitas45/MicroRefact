package de.metas.ui.web.picking.pickingslot;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.warehouse.WarehouseId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.handlingunits.picking.IHUPickingSlotDAO;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateRepository;
import de.metas.handlingunits.picking.PickingCandidateStatus;
import de.metas.handlingunits.picking.PickingCandidatesQuery;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.handlingunits.sourcehu.SourceHUsService.MatchingSourceHusQuery;
import de.metas.handlingunits.sourcehu.SourceHUsService.MatchingSourceHusQuery.MatchingSourceHusQueryBuilder;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.IShipmentSchedulePA;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.logging.LogManager;
import de.metas.picking.api.PickingSlotId;
import de.metas.picking.model.I_M_PickingSlot;
import de.metas.product.ProductId;
import de.metas.ui.web.handlingunits.DefaultHUEditorViewFactory;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowAttributesProvider;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorViewRepository;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class PickingHURowsRepository {

 private  Logger logger;

 private  ExtendedMemorizingSupplier<HUEditorViewRepository> huEditorRepoSupplier;

 private  PickingCandidateRepository pickingCandidatesRepo;

 private HUEditorRow huEditorRow;

 private boolean processed;


public List<HUEditorRow> retrieveSourceHUs(PickingSlotRepoQuery query){
    final HUEditorViewRepository huEditorRepo = getHUEditorViewRepository();
    final MatchingSourceHusQuery matchingSourceHUsQuery = createMatchingSourceHusQuery(query);
    final Set<HuId> sourceHUIds = SourceHUsService.get().retrieveMatchingSourceHUIds(matchingSourceHUsQuery);
    return huEditorRepo.retrieveHUEditorRows(sourceHUIds, HUEditorRowFilter.ALL);
}


public boolean isPickingCandidateProcessed(PickingCandidate pc){
    final PickingCandidateStatus status = pc.getProcessingStatus();
    if (PickingCandidateStatus.Closed.equals(status)) {
        return true;
    } else if (PickingCandidateStatus.Processed.equals(status)) {
        return true;
    } else if (PickingCandidateStatus.Draft.equals(status)) {
        return false;
    } else {
        throw new AdempiereException("Unexpected M_Picking_Candidate.Status=" + status).setParameter("pc", pc);
    }
}


public HUEditorViewRepository getHUEditorViewRepository(){
    return huEditorRepoSupplier.get();
}


public ListMultimap<PickingSlotId,PickedHUEditorRow> retrievePickedHUsIndexedByPickingSlotId(List<PickingCandidate> pickingCandidates){
    final HUEditorViewRepository huEditorRepo = getHUEditorViewRepository();
    final Map<HuId, PickedHUEditorRow> huId2huRow = new HashMap<>();
    final ImmutableListMultimap.Builder<PickingSlotId, PickedHUEditorRow> builder = ImmutableListMultimap.builder();
    for (final PickingCandidate pickingCandidate : pickingCandidates) {
        if (pickingCandidate.isRejectedToPick()) {
            continue;
        }
        final HuId huId = pickingCandidate.getPickFrom().getHuId();
        if (huId == null) {
            logger.warn("Skip {} because huId is null", huId);
            continue;
        }
        if (huId2huRow.containsKey(huId)) {
            continue;
        }
        final PickingSlotId pickingSlotId = pickingCandidate.getPickingSlotId();
        if (pickingSlotId == null) {
            logger.warn("Skip picking candidate because it has no picking slot set: {}." + "\n Usually that happening because it was picked with some other picking terminal.", pickingCandidate);
            continue;
        }
        final HUEditorRow huEditorRow = huEditorRepo.retrieveForHUId(huId);
        final boolean pickingCandidateProcessed = isPickingCandidateProcessed(pickingCandidate);
        final PickedHUEditorRow row = new PickedHUEditorRow(huEditorRow, pickingCandidateProcessed);
        huId2huRow.put(huId, row);
        builder.put(pickingSlotId, row);
    }
    return builder.build();
}


public ListMultimap<PickingSlotId,PickedHUEditorRow> retrieveAllPickedHUsIndexedByPickingSlotId(List<I_M_PickingSlot> pickingSlots){
    final SetMultimap<PickingSlotId, HuId> // 
    huIdsByPickingSlotId = Services.get(IHUPickingSlotDAO.class).retrieveAllHUIdsIndexedByPickingSlotId(pickingSlots);
    final HUEditorViewRepository huEditorRepo = getHUEditorViewRepository();
    huEditorRepo.warmUp(ImmutableSet.copyOf(huIdsByPickingSlotId.values()));
    return huIdsByPickingSlotId.entries().stream().map(pickingSlotAndHU -> {
        final PickingSlotId pickingSlotId = pickingSlotAndHU.getKey();
        final HuId huId = pickingSlotAndHU.getValue();
        final HUEditorRow huEditorRow = huEditorRepo.retrieveForHUId(huId);
        final boolean pickingCandidateProcessed = true;
        final PickedHUEditorRow row = new PickedHUEditorRow(huEditorRow, pickingCandidateProcessed);
        return GuavaCollectors.entry(pickingSlotId, row);
    }).collect(GuavaCollectors.toImmutableListMultimap());
}


public SqlHUEditorViewRepository createDefaultHUEditorViewRepository(DefaultHUEditorViewFactory huEditorViewFactory,HUReservationService huReservationService){
    return SqlHUEditorViewRepository.builder().windowId(PickingConstants.WINDOWID_PickingSlotView).attributesProvider(HUEditorRowAttributesProvider.builder().readonly(true).build()).sqlViewBinding(huEditorViewFactory.getSqlViewBinding()).huReservationService(huReservationService).build();
}


@VisibleForTesting
public MatchingSourceHusQuery createMatchingSourceHusQuery(PickingSlotRepoQuery query){
    final IShipmentSchedulePA shipmentSchedulesRepo = Services.get(IShipmentSchedulePA.class);
    final I_M_ShipmentSchedule currentShipmentSchedule = query.getCurrentShipmentScheduleId() != null ? shipmentSchedulesRepo.getById(query.getCurrentShipmentScheduleId(), I_M_ShipmentSchedule.class) : null;
    final Set<ProductId> productIds;
    final Set<ShipmentScheduleId> allShipmentScheduleIds = query.getShipmentScheduleIds();
    if (allShipmentScheduleIds.isEmpty()) {
        productIds = ImmutableSet.of();
    } else if (allShipmentScheduleIds.size() == 1) {
        productIds = ImmutableSet.of(ProductId.ofRepoId(currentShipmentSchedule.getM_Product_ID()));
    } else {
        productIds = shipmentSchedulesRepo.getProductIdsByShipmentScheduleIds(allShipmentScheduleIds);
    }
    final MatchingSourceHusQueryBuilder builder = MatchingSourceHusQuery.builder().productIds(productIds);
    final IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL = Services.get(IShipmentScheduleEffectiveBL.class);
    final WarehouseId effectiveWarehouseId = currentShipmentSchedule != null ? shipmentScheduleEffectiveBL.getWarehouseId(currentShipmentSchedule) : null;
    builder.warehouseId(effectiveWarehouseId);
    return builder.build();
}


}