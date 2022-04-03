package de.metas.ui.web.view;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.slf4j.Logger;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.security.IUserRolePermissions;
import de.metas.security.IUserRolePermissionsDAO;
import de.metas.security.UserRolePermissionsKey;
import de.metas.security.permissions.WindowMaxQueryRecordsConstraint;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewKeyColumnNamesMap;
import de.metas.ui.web.view.descriptor.SqlViewSelectionQueryBuilder;
import de.metas.ui.web.view.descriptor.SqlViewSelectionQueryBuilder.SqlCreateSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Services;
import lombok.NonNull;
public class SqlViewRowIdsOrderedSelectionFactory implements ViewRowIdsOrderedSelectionFactory{

 private  Logger logger;

 private  IUserRolePermissionsDAO userRolePermissionsRepo;

 private  SqlViewBinding viewBinding;


@Override
public String getSqlWhereClause(ViewId viewId,DocumentIdsSelection rowIds){
    return newSqlViewSelectionQueryBuilder().buildSqlWhereClause(viewId.getViewId(), rowIds);
}


@Override
public ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,boolean applySecurityRestrictions,SqlDocumentFilterConverterContext context){
    final int queryLimit = extractQueryLimit(viewEvalCtx);
    // 
    // 
    final SqlCreateSelection sqlCreates = newSqlViewSelectionQueryBuilder().applySecurityRestrictions(applySecurityRestrictions).buildSqlCreateSelectionFrom(viewEvalCtx, viewId, filters, orderBys, queryLimit, context);
    logger.trace("Creating selection using {}", sqlCreates);
    // 
    // Create selection lines if any => insert into T_WEBUI_ViewSelectionLine
    if (sqlCreates.getSqlCreateSelectionLines() != null) {
        final SqlAndParams sqlCreateSelectionLines = sqlCreates.getSqlCreateSelectionLines();
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final long linesCount = DB.executeUpdateEx(sqlCreateSelectionLines.getSql(), sqlCreateSelectionLines.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        logger.trace("Created selection lines {}, linesCount={}, duration={}", viewId, linesCount, stopwatch);
    }
    // 
    // Create selection rows => insert into T_WEBUI_ViewSelection
    final long rowsCount;
    {
        final SqlAndParams sqlCreateSelection = sqlCreates.getSqlCreateSelection();
        final Stopwatch stopwatch = Stopwatch.createStarted();
        rowsCount = DB.executeUpdateEx(sqlCreateSelection.getSql(), sqlCreateSelection.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        logger.trace("Created selection {}, rowsCount={}, duration={}", viewId, rowsCount, stopwatch);
    }
    return ViewRowIdsOrderedSelection.builder().viewId(viewId).size(rowsCount).orderBys(orderBys).queryLimit(queryLimit).build();
}


@Override
public void scheduleDeleteSelections(Set<String> selectionIds){
    SqlViewSelectionToDeleteHelper.scheduleDeleteSelections(selectionIds);
}


@Override
public boolean containsAnyOfRowIds(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        return false;
    }
    final SqlAndParams sqlCount = newSqlViewSelectionQueryBuilder().buildSqlCount(selection.getSelectionId(), rowIds);
    final int count = DB.getSQLValueEx(ITrx.TRXNAME_ThreadInherited, sqlCount.getSql(), sqlCount.getSqlParamsArray());
    return count > 0;
}


public int retrieveSize(String selectionId){
    final SqlAndParams sqlCount = newSqlViewSelectionQueryBuilder().buildSqlRetrieveSize(selectionId);
    final int size = DB.getSQLValueEx(ITrx.TRXNAME_ThreadInherited, sqlCount.getSql(), sqlCount.getSqlParams());
    return size <= 0 ? 0 : size;
}


public Set<DocumentId> retrieveRowIdsForLineIds(SqlViewKeyColumnNamesMap keyColumnNamesMap,ViewId viewId,Set<Integer> lineIds){
    final SqlAndParams sqlAndParams = SqlViewSelectionQueryBuilder.buildSqlSelectRowIdsForLineIds(keyColumnNamesMap, viewId.getViewId(), lineIds);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final ImmutableSet.Builder<DocumentId> rowIds = ImmutableSet.builder();
        while (rs.next()) {
            final DocumentId rowId = keyColumnNamesMap.retrieveRowId(rs, "", false);
            rowIds.add(rowId);
        }
        return rowIds.build();
    } catch (final SQLException ex) {
        throw new DBException(ex, sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


public SqlViewRowIdsOrderedSelectionFactory of(SqlViewBinding viewBinding){
    return new SqlViewRowIdsOrderedSelectionFactory(viewBinding);
}


@Override
public ViewRowIdsOrderedSelection removeRowIdsFromSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        // nothing changed
        return selection;
    }
    // 
    // Delete
    {
        final SqlAndParams sqlDelete = newSqlViewSelectionQueryBuilder().buildSqlDeleteRowIdsFromSelection(selection.getSelectionId(), rowIds);
        final int deleted = DB.executeUpdateEx(sqlDelete.getSql(), sqlDelete.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        if (deleted <= 0) {
            // nothing changed
            return selection;
        }
    }
    // 
    // Retrieve current size
    // NOTE: we are querying it instead of subtracting "deleted" from current "size" because it might be that the size is staled
    final int size = retrieveSize(selection.getSelectionId());
    return selection.withSize(size);
}


@Override
public ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx){
    final WindowId windowId = fromSelection.getWindowId();
    final String fromSelectionId = fromSelection.getSelectionId();
    final ViewId newViewId = ViewId.random(windowId);
    final int rowsCount;
    final SqlViewSelectionQueryBuilder viewQueryBuilder = newSqlViewSelectionQueryBuilder();
    if (viewQueryBuilder.hasGroupingFields()) {
        // TODO: implement filters support when using groupping fields
        if (!filters.isEmpty()) {
            throw new AdempiereException("Filters are not yet supported when using groupping fields").appendParametersToMessage().setParameter("filters", filters);
        }
        final SqlAndParams sqlCreateSelectionLines = viewQueryBuilder.buildSqlCreateSelectionLinesFromSelectionLines(viewEvalCtx, newViewId, fromSelectionId);
        final int linesCount = DB.executeUpdateEx(sqlCreateSelectionLines.getSql(), sqlCreateSelectionLines.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        if (linesCount > 0) {
            final SqlAndParams sqlCreateSelection = viewQueryBuilder.buildSqlCreateSelectionFromSelectionLines(viewEvalCtx, newViewId, orderBys);
            rowsCount = DB.executeUpdateEx(sqlCreateSelection.getSql(), sqlCreateSelection.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        } else {
            rowsCount = 0;
        }
    } else {
        final SqlAndParams sqlCreateSelection = viewQueryBuilder.buildSqlCreateSelectionFromSelection(viewEvalCtx, newViewId, fromSelectionId, filters, orderBys, filterConverterCtx);
        rowsCount = DB.executeUpdateEx(sqlCreateSelection.getSql(), sqlCreateSelection.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
    }
    return ViewRowIdsOrderedSelection.builder().viewId(newViewId).size(rowsCount).orderBys(orderBys).queryLimit(fromSelection.getQueryLimit()).build();
}


@Override
public ViewRowIdsOrderedSelection addRowIdsToSelection(ViewRowIdsOrderedSelection selection,DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        // nothing changed
        return selection;
    } else if (rowIds.isAll()) {
        throw new IllegalArgumentException("Cannot add ALL to selection");
    }
    // 
    // Add
    boolean hasChanges = false;
    final String selectionId = selection.getSelectionId();
    // TODO: add all rowIds in one query!!! Not so urgent because usually there are added just a couple of rowIds, not much
    for (final DocumentId rowId : rowIds.toSet()) {
        final SqlAndParams sqlAdd = newSqlViewSelectionQueryBuilder().buildSqlAddRowIdsFromSelection(selectionId, rowId);
        final int added = DB.executeUpdateEx(sqlAdd.getSql(), sqlAdd.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        if (added <= 0) {
            continue;
        }
        hasChanges = true;
    }
    if (!hasChanges) {
        // nothing changed
        return selection;
    }
    // 
    // Retrieve current size
    // NOTE: we are querying it instead of adding how many we added to current "size" because it might be that the size is staled
    final int size = retrieveSize(selectionId);
    return selection.withSize(size);
}


public SqlViewSelectionQueryBuilder newSqlViewSelectionQueryBuilder(){
    return SqlViewSelectionQueryBuilder.newInstance(viewBinding);
}


public int extractQueryLimit(ViewEvaluationCtx viewEvalCtx){
    final UserRolePermissionsKey permissionsKey = viewEvalCtx.getPermissionsKey();
    final IUserRolePermissions permissions = userRolePermissionsRepo.getUserRolePermissions(permissionsKey);
    return permissions.getConstraint(WindowMaxQueryRecordsConstraint.class).or(WindowMaxQueryRecordsConstraint.DEFAULT).getMaxQueryRecordsPerRole();
}


@Override
public void deleteSelections(Set<String> selectionIds){
    if (selectionIds.isEmpty()) {
        return;
    }
    final SqlViewSelectionQueryBuilder viewQueryBuilder = newSqlViewSelectionQueryBuilder();
    // Delete selection lines
    {
        final SqlAndParams sql = viewQueryBuilder.buildSqlDeleteSelectionLines(selectionIds);
        final int countDeleted = DB.executeUpdateEx(sql.getSql(), sql.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        logger.trace("Delete {} selection lines for {}", countDeleted, selectionIds);
    }
    // Delete selection rows
    {
        final SqlAndParams sql = viewQueryBuilder.buildSqlDeleteSelection(selectionIds);
        final int countDeleted = DB.executeUpdateEx(sql.getSql(), sql.getSqlParamsArray(), ITrx.TRXNAME_ThreadInherited);
        logger.trace("Delete {} selection rows for {}", countDeleted, selectionIds);
    }
}


}