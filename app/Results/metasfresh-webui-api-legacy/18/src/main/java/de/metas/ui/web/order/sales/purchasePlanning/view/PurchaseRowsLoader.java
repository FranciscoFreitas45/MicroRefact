package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.service.ISysConfigBL;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.logging.LogManager;
import de.metas.purchasecandidate.PurchaseCandidatesGroup;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.PurchaseDemandWithCandidates;
import de.metas.purchasecandidate.availability.AvailabilityCheckService;
import de.metas.purchasecandidate.availability.AvailabilityException;
import de.metas.purchasecandidate.availability.AvailabilityMultiResult;
import de.metas.purchasecandidate.availability.PurchaseCandidatesAvailabilityRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
import de.metas.vendor.gateway.api.availability.TrackingId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class PurchaseRowsLoader {

 private  Logger logger;

 private  ISysConfigBL sysConfigBL;

 private  PurchaseRowFactory purchaseRowFactory;

 private  AvailabilityCheckService availabilityCheckService;

 private  String SYSCONFIG_ASYNC_AVAILIABILITY_CHECK;

 private  Supplier<IView> viewSupplier;

 private  ImmutableList<PurchaseDemandWithCandidates> purchaseDemandWithCandidatesList;

@Getter
 private  ImmutableList<PurchaseRow> topLevelRows;

 private  ImmutableMap<TrackingId,PurchaseRowId> trackingIdsByTopLevelRowIds;

@Getter
 private  ImmutableMap<TrackingId,PurchaseCandidatesGroup> purchaseCandidatesGroups;

 private  ImmutableMap<TrackingId,PurchaseRow> purchaseCandidateRows;


public void createAndAddAvailabilityResultRowsAsync(PurchaseRowsList rows){
    availabilityCheckService.checkAvailabilityAsync(createAvailabilityRequest(rows), (result, error) -> handleResultForAsyncAvailabilityCheck(rows, result, error));
}


public DocumentId getTopLevelDocumentIdByTrackingId(TrackingId trackingId,DocumentId defaultValue){
    final PurchaseRowId purchaseRowId = trackingIdsByTopLevelRowIds.get(trackingId);
    return purchaseRowId != null ? purchaseRowId.toDocumentId() : defaultValue;
}


public PurchaseRowsSupplier createPurchaseRowsSupplier(){
    return this::loadAndCheckAvailability;
}


public void notifyViewOfChanges(List<DocumentId> changedRowIds){
    final IView view = viewSupplier.get();
    if (view != null) {
        ViewChangesCollector.getCurrentOrAutoflush().collectRowsChanged(view, DocumentIdsSelection.of(changedRowIds));
    }
}


@VisibleForTesting
public PurchaseCandidatesAvailabilityRequest createAvailabilityRequest(PurchaseRowsList rows){
    return PurchaseCandidatesAvailabilityRequest.of(rows.getPurchaseCandidatesGroups());
}


public PurchaseRow getPurchaseRowByTrackingId(TrackingId trackingId){
    return purchaseCandidateRows.get(trackingId);
}


public void handleResultForAsyncAvailabilityCheck(PurchaseRowsList rows,AvailabilityMultiResult availabilityMultiResult,Throwable error){
    if (availabilityMultiResult != null) {
        handleResultForAsyncAvailabilityCheck_Success(rows, availabilityMultiResult);
    }
    if (error != null) {
        handleResultForAsyncAvailabilityCheck_Error(rows, CoalesceUtil.coalesce(error.getCause(), error));
    }
}


public boolean isMakeAsynchronousAvailiabilityCheck(){
    final Properties ctx = Env.getCtx();
    final boolean result = sysConfigBL.getBooleanValue(SYSCONFIG_ASYNC_AVAILIABILITY_CHECK, false, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
    return result;
}


@VisibleForTesting
public PurchaseRowsList load(){
    final PurchaseRowsList.PurchaseRowsListBuilder resultBuilder = PurchaseRowsList.builder();
    for (final PurchaseDemandWithCandidates demandWithCandidates : purchaseDemandWithCandidatesList) {
        final PurchaseDemand demand = demandWithCandidates.getPurchaseDemand();
        final List<PurchaseRow> purchaseCandidateRows = new ArrayList<>();
        final List<TrackingId> trackingIds = new ArrayList<>();
        for (final PurchaseCandidatesGroup purchaseCandidatesGroup : demandWithCandidates.getPurchaseCandidatesGroups()) {
            final PurchaseRow purchaseCandidateRow = purchaseRowFactory.lineRowBuilder().purchaseCandidatesGroup(purchaseCandidatesGroup).convertAmountsToCurrencyId(demand.getCurrencyIdOrNull()).build();
            purchaseCandidateRows.add(purchaseCandidateRow);
            final TrackingId trackingId = TrackingId.random();
            trackingIds.add(trackingId);
            resultBuilder.purchaseCandidatesGroup(trackingId, purchaseCandidatesGroup);
            resultBuilder.purchaseCandidateRow(trackingId, purchaseCandidateRow);
        }
        final PurchaseRow groupRow = purchaseRowFactory.createGroupRow(demand, purchaseCandidateRows);
        resultBuilder.topLevelRow(groupRow);
        final PurchaseRowId groupRowId = groupRow.getRowId();
        trackingIds.forEach(trackingId -> resultBuilder.trackingIdsByTopLevelRowId(trackingId, groupRowId));
    }
    return resultBuilder.build();
}


@VisibleForTesting
public void createAndAddAvailabilityResultRows(PurchaseRowsList rows){
    try {
        final AvailabilityMultiResult availabilityCheckResult = availabilityCheckService.checkAvailability(createAvailabilityRequest(rows));
        handleResultForAsyncAvailabilityCheck_Success(rows, availabilityCheckResult);
    } catch (final Throwable throwable) {
        handleResultForAsyncAvailabilityCheck_Error(rows, throwable);
    }
}


public void handleResultForAsyncAvailabilityCheck_Success(PurchaseRowsList rows,AvailabilityMultiResult availabilityResults){
    final List<DocumentId> changedRowIds = new ArrayList<>();
    for (final TrackingId trackingId : availabilityResults.getTrackingIds()) {
        final PurchaseRow lineRow = rows.getPurchaseRowByTrackingId(trackingId);
        if (lineRow == null) {
            logger.warn("No row found for {}. Skip updating the row with availability results.", trackingId);
            continue;
        }
        final ImmutableList<PurchaseRow> availabilityResultRows = availabilityResults.getByTrackingId(trackingId).stream().map(availabilityResult -> purchaseRowFactory.availabilityDetailSuccessBuilder().lineRow(lineRow).availabilityResult(availabilityResult).build()).collect(ImmutableList.toImmutableList());
        lineRow.setAvailabilityInfoRows(availabilityResultRows);
        changedRowIds.add(rows.getTopLevelDocumentIdByTrackingId(trackingId, lineRow.getId()));
    }
    notifyViewOfChanges(changedRowIds);
}


public List<PurchaseRow> loadAndCheckAvailability(){
    final PurchaseRowsList rows = load();
    if (isMakeAsynchronousAvailiabilityCheck()) {
        createAndAddAvailabilityResultRowsAsync(rows);
    } else {
        createAndAddAvailabilityResultRows(rows);
    }
    return rows.getTopLevelRows();
}


public void handleResultForAsyncAvailabilityCheck_Error(PurchaseRowsList rows,Throwable throwable){
    if (throwable instanceof AvailabilityException) {
        final AvailabilityException availabilityException = AvailabilityException.cast(throwable);
        final List<DocumentId> changedRowIds = new ArrayList<>();
        for (final AvailabilityException.ErrorItem errorItem : availabilityException.getErrorItems()) {
            final TrackingId trackingId = errorItem.getTrackingId();
            final PurchaseRow lineRow = rows.getPurchaseRowByTrackingId(trackingId);
            if (lineRow == null) {
                logger.warn("No line row found for {}. Skip updating the row with availability errors: {}", trackingId, errorItem);
                continue;
            }
            final PurchaseRow availabilityResultRow = purchaseRowFactory.availabilityDetailErrorBuilder().lineRow(lineRow).throwable(errorItem.getError()).build();
            lineRow.setAvailabilityInfoRow(availabilityResultRow);
            changedRowIds.add(rows.getTopLevelDocumentIdByTrackingId(trackingId, lineRow.getId()));
        }
        notifyViewOfChanges(changedRowIds);
    } else {
        // TODO: display an error-message in the webui
        logger.warn("Got unknown exception while doing availability check. Ignored.", throwable);
    }
}


}