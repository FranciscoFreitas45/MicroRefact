package de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseRowsLoader;
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
@VisibleForTesting
public class PurchaseRowsList {

@Getter
 private  ImmutableList<PurchaseRow> topLevelRows;

 private  ImmutableMap<TrackingId,PurchaseRowId> trackingIdsByTopLevelRowIds;

@Getter
 private  ImmutableMap<TrackingId,PurchaseCandidatesGroup> purchaseCandidatesGroups;

 private  ImmutableMap<TrackingId,PurchaseRow> purchaseCandidateRows;


public PurchaseRow getPurchaseRowByTrackingId(TrackingId trackingId){
    return purchaseCandidateRows.get(trackingId);
}


public DocumentId getTopLevelDocumentIdByTrackingId(TrackingId trackingId,DocumentId defaultValue){
    final PurchaseRowId purchaseRowId = trackingIdsByTopLevelRowIds.get(trackingId);
    return purchaseRowId != null ? purchaseRowId.toDocumentId() : defaultValue;
}


}