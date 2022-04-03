package de.metas.ui.web.dashboard;
 import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryOrderBy.Direction;
import org.adempiere.ad.dao.IQueryOrderBy.Nulls;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ClientId;
import org.adempiere.util.lang.Mutable;
import org.compiere.model.IQuery.Aggregate;
import org.compiere.util.DB;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.Language;
import de.metas.i18n.po.POTrlInfo;
import de.metas.i18n.po.POTrlRepository;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.base.model.I_WEBUI_Dashboard;
import de.metas.ui.web.base.model.I_WEBUI_DashboardItem;
import de.metas.ui.web.base.model.I_WEBUI_KPI;
import de.metas.ui.web.dashboard.UserDashboardItemChangeResult.UserDashboardItemChangeResultBuilder;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
@Service
public class UserDashboardRepository {

 private  Logger logger;

 private  IQueryBL queryBL;

@Autowired
 private  KPIRepository kpisRepo;

 private  CCache<UserDashboardKey,Integer> key2dashboardId;

 private  CCache<Integer,UserDashboard> dashboadsCache;

@Nullable
 private ClientId adClientId;


public UserDashboardItemChangeResult changeUserDashboardItem(UserDashboard dashboard,UserDashboardItemChangeRequest request){
    final int dashboardId = dashboard.getId();
    final DashboardWidgetType dashboardWidgetType = request.getWidgetType();
    final int itemId = request.getItemId();
    dashboard.assertItemIdExists(dashboardWidgetType, itemId);
    // 
    // Execute the change request
    return executeChangeActionAndInvalidateAndReturn(dashboardId, () -> {
        final UserDashboardItemChangeResultBuilder resultBuilder = UserDashboardItemChangeResult.builder().dashboardId(dashboardId).dashboardWidgetType(dashboardWidgetType).itemId(itemId);
        // 
        // Actually change the item content
        changeUserDashboardItemAndSave(request);
        // 
        // Change item's position
        final int position = request.getPosition();
        if (position >= 0) {
            final List<Integer> allItemIdsOrdered = new ArrayList<>(retrieveDashboardItemIdsOrdered(dashboardId, dashboardWidgetType));
            if (position == Integer.MAX_VALUE || position > allItemIdsOrdered.size() - 1) {
                allItemIdsOrdered.remove((Object) itemId);
                allItemIdsOrdered.add(itemId);
            } else {
                allItemIdsOrdered.remove((Object) itemId);
                allItemIdsOrdered.add(position, itemId);
            }
            updateUserDashboardItemsOrder(dashboardId, allItemIdsOrdered);
            resultBuilder.dashboardOrderedItemIds(ImmutableList.copyOf(allItemIdsOrdered));
        }
        return resultBuilder.build();
    });
}


public void executeChangeActionsAndInvalidate(int dashboardId,List<Runnable> changeActions){
    Services.get(ITrxManager.class).run(ITrx.TRXNAME_ThreadInherited, () -> {
        changeActions.forEach(Runnable::run);
    });
    invalidateUserDashboard(dashboardId);
}


public void executeChangeActionAndInvalidate(int dashboardId,Runnable changeAction){
    executeChangeActionsAndInvalidate(dashboardId, ImmutableList.of(changeAction));
}


public void deleteUserDashboardItem(UserDashboard dashboard,DashboardWidgetType dashboardWidgetType,int itemId){
    dashboard.assertItemIdExists(dashboardWidgetType, itemId);
    executeChangeActionAndInvalidate(dashboard.getId(), () -> {
        final I_WEBUI_DashboardItem item = InterfaceWrapperHelper.load(itemId, I_WEBUI_DashboardItem.class);
        InterfaceWrapperHelper.delete(item);
    });
}


public UserDashboard createUserDashboard(I_WEBUI_Dashboard webuiDashboard){
    final int webuiDashboardId = webuiDashboard.getWEBUI_Dashboard_ID();
    final UserDashboard.Builder userDashboardBuilder = UserDashboard.builder().setId(webuiDashboardId).setAdClientId(webuiDashboard.getAD_Client_ID());
    retrieveWEBUI_DashboardItemsQuery(webuiDashboardId).create().list(I_WEBUI_DashboardItem.class).stream().map(this::createUserDashboardItem).forEach(userDashboardBuilder::addItem);
    return userDashboardBuilder.build();
}


public UserDashboard getUserDashboard(UserDashboardKey userDashboardKey){
    final Integer dashboardId = key2dashboardId.getOrLoad(userDashboardKey, () -> retrieveUserDashboardId(userDashboardKey));
    if (dashboardId == null || dashboardId <= 0) {
        return UserDashboard.EMPTY;
    }
    return getUserDashboardById(dashboardId);
}


public void updateUserDashboardItemsOrder(int dashboardId,List<Integer> allItemIdsOrdered){
    final String sql = "UPDATE " + I_WEBUI_DashboardItem.Table_Name + " SET " + I_WEBUI_DashboardItem.COLUMNNAME_SeqNo + "=?" + " WHERE " + I_WEBUI_DashboardItem.COLUMNNAME_WEBUI_Dashboard_ID + "=?" + " AND " + I_WEBUI_DashboardItem.COLUMNNAME_WEBUI_DashboardItem_ID + "=?";
    PreparedStatement pstmt = null;
    try {
        for (int newSeqNo = 0; newSeqNo < allItemIdsOrdered.size(); newSeqNo++) {
            final int itemId = allItemIdsOrdered.get(newSeqNo);
            if (pstmt == null) {
                pstmt = DB.prepareStatement(sql, ITrx.TRXNAME_ThreadInherited);
            }
            // convert 0-based index to "10, 20, 30.." sequence number (starting from 10)
            final int sqlNewSeqNo = newSeqNo * 10 + 10;
            DB.setParameters(pstmt, new Object[] { sqlNewSeqNo, dashboardId, itemId });
            pstmt.addBatch();
        }
        if (pstmt == null) {
            return;
        }
        pstmt.executeBatch();
    } catch (final SQLException ex) {
        throw DBException.wrapIfNeeded(ex);
    } finally {
        DB.close(pstmt);
    }
}


public List<Integer> retrieveDashboardItemIdsOrdered(int dashboardId,DashboardWidgetType dashboardWidgetType){
    return retrieveWEBUI_DashboardItemsQuery(dashboardId).addEqualsFilter(I_WEBUI_DashboardItem.COLUMN_WEBUI_DashboardWidgetType, dashboardWidgetType.getCode()).orderBy().addColumn(I_WEBUI_DashboardItem.COLUMN_SeqNo, Direction.Ascending, Nulls.First).addColumn(I_WEBUI_DashboardItem.COLUMN_WEBUI_DashboardItem_ID).endOrderBy().create().listIds();
}


public R executeChangeActionAndInvalidateAndReturn(int dashboardId,Callable<R> changeAction){
    final Mutable<R> result = new Mutable<>();
    final Runnable changeActionRunnable = () -> {
        try {
            result.setValue(changeAction.call());
        } catch (final Exception ex) {
            throw AdempiereException.wrapIfNeeded(ex);
        }
    };
    executeChangeActionsAndInvalidate(dashboardId, ImmutableList.of(changeActionRunnable));
    return result.getValue();
}


public IQueryBuilder<I_WEBUI_DashboardItem> retrieveWEBUI_DashboardItemsQuery(int webuiDashboardId){
    return queryBL.createQueryBuilder(I_WEBUI_DashboardItem.class).addOnlyActiveRecordsFilter().addEqualsFilter(I_WEBUI_DashboardItem.COLUMN_WEBUI_Dashboard_ID, webuiDashboardId).orderBy().addColumn(I_WEBUI_DashboardItem.COLUMN_SeqNo, Direction.Ascending, Nulls.First).addColumn(I_WEBUI_DashboardItem.COLUMN_WEBUI_DashboardItem_ID).endOrderBy();
}


public void invalidateUserDashboard(int dashboardId){
    dashboadsCache.remove(dashboardId);
}


public int createUserDashboardItemAndSave(int dashboardId,UserDashboardItemAddRequest request){
    // 
    // Get the KPI
    final int kpiId = request.getKpiId();
    if (kpiId <= 0) {
        throw new AdempiereException("kpiId is not set").setParameter("request", request);
    }
    final I_WEBUI_KPI kpi = InterfaceWrapperHelper.loadOutOfTrx(kpiId, I_WEBUI_KPI.class);
    final DashboardWidgetType widgetType = request.getWidgetType();
    final int seqNo = retrieveLastSeqNo(dashboardId, widgetType) + 10;
    // 
    final I_WEBUI_DashboardItem webuiDashboardItem = InterfaceWrapperHelper.newInstance(I_WEBUI_DashboardItem.class);
    webuiDashboardItem.setWEBUI_Dashboard_ID(dashboardId);
    webuiDashboardItem.setIsActive(true);
    webuiDashboardItem.setName(kpi.getName());
    webuiDashboardItem.setSeqNo(seqNo);
    webuiDashboardItem.setWEBUI_KPI_ID(kpiId);
    webuiDashboardItem.setWEBUI_DashboardWidgetType(widgetType.getCode());
    // will be set by change request:
    // webuiDashboardItem.setES_TimeRange(esTimeRange);
    // webuiDashboardItem.setES_TimeRange_End(esTimeRangeEnd);
    InterfaceWrapperHelper.save(webuiDashboardItem);
    logger.trace("Created {} for dashboard {}", webuiDashboardItem, dashboardId);
    // TODO: copy trl but also consider the request.getCaption() and use it only for the current language trl.
    // 
    // Apply the change request
    if (request.getChangeRequest() != null) {
        changeUserDashboardItemAndSave(webuiDashboardItem, request.getChangeRequest());
    }
    final int itemId = webuiDashboardItem.getWEBUI_DashboardItem_ID();
    return itemId;
}


public int retrieveLastSeqNo(int dashboardId,DashboardWidgetType dashboardWidgetType){
    final Integer maxSeqNo = queryBL.createQueryBuilder(I_WEBUI_DashboardItem.class).addEqualsFilter(I_WEBUI_DashboardItem.COLUMN_WEBUI_Dashboard_ID, dashboardId).addEqualsFilter(I_WEBUI_DashboardItem.COLUMN_WEBUI_DashboardWidgetType, dashboardWidgetType.getCode()).create().aggregate(I_WEBUI_DashboardItem.COLUMN_SeqNo, Aggregate.MAX, Integer.class);
    return maxSeqNo != null ? maxSeqNo : 0;
}


public Collection<KPI> getKPIsAvailableToAdd(){
    return kpisRepo.getKPIs();
}


public UserDashboard retrieveUserDashboard(int dashboardId){
    final I_WEBUI_Dashboard webuiDashboard = queryBL.createQueryBuilder(I_WEBUI_Dashboard.class).addOnlyActiveRecordsFilter().addEqualsFilter(I_WEBUI_Dashboard.COLUMN_WEBUI_Dashboard_ID, dashboardId).create().firstOnlyNotNull(I_WEBUI_Dashboard.class);
    return createUserDashboard(webuiDashboard);
}


public int addUserDashboardItem(UserDashboard userDashboard,UserDashboardItemAddRequest request){
    final int dashboardId = userDashboard.getId();
    final DashboardWidgetType dashboardWidgetType = request.getWidgetType();
    final Set<Integer> allItemIds = userDashboard.getItemIds(dashboardWidgetType);
    logger.trace("Adding to dashboard {}: type={}, request={}", dashboardId, dashboardWidgetType, request);
    logger.trace("Current ordered itemIds: {}", allItemIds);
    return executeChangeActionAndInvalidateAndReturn(dashboardId, () -> {
        // 
        // Create dashboard item in database (will be added last).
        final int itemId = createUserDashboardItemAndSave(dashboardId, request);
        // 
        // Calculate item's position
        final List<Integer> orderedItemIds = new ArrayList<>(allItemIds);
        if (request.getPosition() >= 0 && request.getPosition() < orderedItemIds.size()) {
            orderedItemIds.add(request.getPosition(), itemId);
        } else {
            orderedItemIds.add(itemId);
        }
        logger.trace("New ordered itemIds: {}", allItemIds);
        // 
        // Update dashboard items order
        changeDashboardItemsOrder(dashboardId, dashboardWidgetType, orderedItemIds);
        // 
        return itemId;
    });
}


public int retrieveUserDashboardId(UserDashboardKey key){
    final ClientId adClientId = key.getAdClientId();
    final int dashboardId = queryBL.createQueryBuilder(I_WEBUI_Dashboard.class).addOnlyActiveRecordsFilter().addInArrayFilter(I_WEBUI_Dashboard.COLUMN_AD_Client_ID, ClientId.SYSTEM, adClientId).orderBy().addColumn(I_WEBUI_Dashboard.COLUMN_AD_Client_ID, Direction.Descending, Nulls.Last).addColumn(I_WEBUI_Dashboard.COLUMN_IsDefault, Direction.Descending, Nulls.Last).addColumn(I_WEBUI_Dashboard.COLUMN_WEBUI_Dashboard_ID).endOrderBy().create().firstId();
    return dashboardId > 0 ? dashboardId : -1;
}


public UserDashboardItem createUserDashboardItem(I_WEBUI_DashboardItem itemPO){
    final IModelTranslationMap trlsMap = InterfaceWrapperHelper.getModelTranslationMap(itemPO);
    return UserDashboardItem.builder().setId(itemPO.getWEBUI_DashboardItem_ID()).setCaption(trlsMap.getColumnTrl(I_WEBUI_DashboardItem.COLUMNNAME_Name, itemPO.getName())).setUrl(itemPO.getURL()).setSeqNo(itemPO.getSeqNo()).setWidgetType(DashboardWidgetType.ofCode(itemPO.getWEBUI_DashboardWidgetType())).setKPI(() -> kpisRepo.getKPIOrNull(itemPO.getWEBUI_KPI_ID())).setTimeRangeDefaults(KPITimeRangeDefaults.builder().defaultTimeRangeFromString(itemPO.getES_TimeRange()).defaultTimeRangeEndOffsetFromString(itemPO.getES_TimeRange_End()).build()).build();
}


public UserDashboard getUserDashboardById(int dashboardId){
    return dashboadsCache.getOrLoad(dashboardId, () -> retrieveUserDashboard(dashboardId));
}


public void changeDashboardItemsOrder(int dashboardId,DashboardWidgetType dashboardWidgetType,List<Integer> requestOrderedItemIds){
    // Retrieve all itemIds ordered
    final List<Integer> allItemIds = retrieveDashboardItemIdsOrdered(dashboardId, dashboardWidgetType);
    // 
    // Start building the orderedIds list by adding all the IDs from the request
    final List<Integer> orderedIds = requestOrderedItemIds.stream().filter(// skip those IDs which are not present in our "all" ids list
    allItemIds::contains).collect(// mutable list
    Collectors.toCollection(ArrayList::new));
    // At the end of orderedIds all all those IDs which where not present in provided request (those might exist only in database).
    allItemIds.forEach(id -> {
        if (!orderedIds.contains(id)) {
            orderedIds.add(id);
        }
    });
    // If there was no change then stop here
    if (allItemIds.equals(orderedIds)) {
        return;
    }
    // 
    // Persist changes
    updateUserDashboardItemsOrder(dashboardId, orderedIds);
}


public void changeUserDashboardItemAndSave(UserDashboardItemChangeRequest request){
    final I_WEBUI_DashboardItem itemPO = InterfaceWrapperHelper.load(request.getItemId(), I_WEBUI_DashboardItem.class);
    if (itemPO == null) {
        throw new AdempiereException("no item found for itemId=" + request.getItemId());
    }
    changeUserDashboardItemAndSave(itemPO, request);
}


}