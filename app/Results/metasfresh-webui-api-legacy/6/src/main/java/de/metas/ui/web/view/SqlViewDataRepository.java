package de.metas.ui.web.view;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.PlainContextAware;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewKeyColumnNamesMap;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding.SqlViewRowFieldLoader;
import de.metas.ui.web.view.descriptor.SqlViewSelectData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class SqlViewDataRepository implements IViewDataRepository{

 private  Logger logger;

 private  String tableName;

 private  String tableAlias;

 private  SqlViewKeyColumnNamesMap keyColumnNamesMap;

 private  Map<String,DocumentFieldWidgetType> widgetTypesByFieldName;

 private  SqlViewSelectData sqlViewSelect;

 private  ViewRowIdsOrderedSelectionFactory viewRowIdsOrderedSelectionFactory;

 private  DocumentFilterDescriptorsProvider viewFilterDescriptors;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  boolean hasIncludedRows;

 private  ImmutableMap<String,SqlViewRowFieldLoader> rowFieldLoaders;

 private  ViewRowCustomizer rowCustomizer;

 private  SqlDocumentFilterConverter filterConverters;


@Override
public void scheduleDeleteSelections(Set<String> viewIds){
    viewRowIdsOrderedSelectionFactory.scheduleDeleteSelections(viewIds);
}


public DocumentId retrieveRowId_MultiKey(ResultSet rs,String adLanguage){
    final List<Object> rowIdParts = new ArrayList<>(keyColumnNamesMap.getKeyPartsCount());
    boolean onlyNullValues = true;
    for (final String keyColumnName : keyColumnNamesMap.getKeyColumnNames()) {
        final SqlViewRowFieldLoader fieldLoader = rowFieldLoaders.get(keyColumnName);
        // Check.assumeNotNull(fieldLoader, "fieldLoader shall exist for {}", keyColumnName);
        final Object rowIdPartObj = fieldLoader.retrieveValue(rs, adLanguage);
        if (JSONNullValue.isNull(rowIdPartObj)) {
            rowIdParts.add(null);
        } else {
            rowIdParts.add(convertToRowIdPart(rowIdPartObj));
            onlyNullValues = false;
        }
    }
    if (onlyNullValues) {
        return null;
    }
    return DocumentId.ofComposedKeyParts(rowIdParts);
}


public String convertToRowIdPart(Object rowIdPartObj){
    if (rowIdPartObj instanceof Integer) {
        return rowIdPartObj.toString();
    } else if (rowIdPartObj instanceof String) {
        return rowIdPartObj.toString();
    } else if (rowIdPartObj instanceof LookupValue) {
        return ((LookupValue) rowIdPartObj).getIdAsString();
    } else if (rowIdPartObj instanceof JSONLookupValue) {
        return ((JSONLookupValue) rowIdPartObj).getKey();
    } else {
        throw new AdempiereException("Cannot convert " + rowIdPartObj + " (" + rowIdPartObj.getClass() + ") to rowId part");
    }
}


public List<IViewRow> retrieveRowLines(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentIdsSelection rowIds){
    logger.debug("Getting row lines: rowId={} - {}", rowIds, this);
    logger.debug("Using: {}", viewId);
    final SqlAndParams sqlAndParams = sqlViewSelect.selectIncludedLines().viewEvalCtx(viewEvalCtx).viewId(viewId).rowIds(rowIds).build();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final List<IViewRow> lines = loadViewRows(rs, viewEvalCtx, viewId, -1);
        return lines;
    } catch (final SQLException | DBException e) {
        throw DBException.wrapIfNeeded(e).setSqlIfAbsent(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


@Override
public List<Object> retrieveFieldValues(ViewEvaluationCtx viewEvalCtx,String selectionId,String fieldName,int limit){
    final SqlViewRowFieldLoader fieldLoader = rowFieldLoaders.get(fieldName);
    final SqlAndParams sql = sqlViewSelect.selectFieldValues(viewEvalCtx, selectionId, fieldName, limit);
    final String adLanguage = viewEvalCtx.getAdLanguage();
    return DB.retrieveRows(sql.getSql(), sql.getSqlParams(), rs -> fieldLoader.retrieveValue(rs, adLanguage));
}


@Override
public ViewRowIdsOrderedSelection removeRowIdsNotMatchingFilters(ViewRowIdsOrderedSelection selection,DocumentFilterList filters,Set<DocumentId> rowIds){
    if (rowIds.isEmpty()) {
        return selection;
    }
    final ViewId viewId = selection.getViewId();
    final Set<DocumentId> matchingRowIds = retrieveRowIdsMatchingFilters(viewId, filters, rowIds);
    final Set<DocumentId> notMatchingRowIds = Sets.difference(rowIds, matchingRowIds);
    if (notMatchingRowIds.isEmpty()) {
        return selection;
    }
    return viewRowIdsOrderedSelectionFactory.removeRowIdsFromSelection(selection, DocumentIdsSelection.of(notMatchingRowIds));
}


public DocumentId convertToRowId(Object rowIdObj){
    if (JSONNullValue.isNull(rowIdObj)) {
        return null;
    } else if (rowIdObj instanceof DocumentId) {
        return (DocumentId) rowIdObj;
    } else if (rowIdObj instanceof Integer) {
        return DocumentId.of((Integer) rowIdObj);
    } else if (rowIdObj instanceof String) {
        return DocumentId.of(rowIdObj.toString());
    } else if (rowIdObj instanceof LookupValue) {
        // case: usually this is happening when a view's column which is Lookup is also marked as KEY.
        final JSONLookupValue jsonLookupValue = (JSONLookupValue) rowIdObj;
        return DocumentId.of(jsonLookupValue.getKey());
    } else if (rowIdObj instanceof JSONLookupValue) {
        // case: usually this is happening when a view's column which is Lookup is also marked as KEY.
        final JSONLookupValue jsonLookupValue = (JSONLookupValue) rowIdObj;
        return DocumentId.of(jsonLookupValue.getKey());
    } else {
        throw new IllegalArgumentException("Cannot convert id '" + rowIdObj + "' (" + rowIdObj.getClass() + ") to " + DocumentId.class);
    }
}


public Set<DocumentId> retrieveRowIdsMatchingFilters(ViewId viewId,DocumentFilterList filters,Set<DocumentId> rowIds){
    if (rowIds.isEmpty()) {
        return rowIds;
    }
    final String sqlWhereClause = getSqlWhereClause(viewId, filters, DocumentIdsSelection.of(rowIds), SqlOptions.usingTableName(getTableName()));
    final String sql = "SELECT " + keyColumnNamesMap.getKeyColumnNamesCommaSeparated() + "\n FROM " + getTableName() + "\n WHERE " + sqlWhereClause;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, ITrx.TRXNAME_ThreadInherited);
        rs = pstmt.executeQuery();
        final HashSet<DocumentId> matchingRowIds = new HashSet<>();
        while (rs.next()) {
            DocumentId rowId = keyColumnNamesMap.retrieveRowId(rs);
            if (rowId != null) {
                matchingRowIds.add(rowId);
            }
        }
        return matchingRowIds;
    } catch (Exception ex) {
        throw new DBException(ex, sql);
    } finally {
        DB.close(rs, pstmt);
    }
}


@Override
public String getSqlWhereClause(ViewId viewId,DocumentFilterList filters,DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    final StringBuilder sqlWhereClause = new StringBuilder();
    // Convert filters to SQL
    {
        final SqlDocumentFilterConverterContext context = SqlDocumentFilterConverterContext.EMPTY;
        final String sqlFilters = filterConverters.getSql(SqlParamsCollector.notCollecting(), filters, sqlOpts, context);
        if (!Check.isEmpty(sqlFilters, true)) {
            if (sqlWhereClause.length() > 0) {
                sqlWhereClause.append(" AND ");
            }
            sqlWhereClause.append("(").append(sqlFilters).append(")");
        }
    }
    // Filter by rowIds
    {
        final String sqlFilterByRowIds = viewRowIdsOrderedSelectionFactory.getSqlWhereClause(viewId, rowIds);
        if (!Check.isEmpty(sqlFilterByRowIds, true)) {
            if (sqlWhereClause.length() > 0) {
                sqlWhereClause.append(" AND ");
            }
            sqlWhereClause.append("(").append(sqlFilterByRowIds).append(")");
        }
    }
    return sqlWhereClause.toString();
}


@Override
public ViewRowIdsOrderedSelection createOrderedSelection(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentFilterList filters,boolean applySecurityRestrictions,SqlDocumentFilterConverterContext context){
    return viewRowIdsOrderedSelectionFactory.createOrderedSelection(viewEvalCtx, viewId, filters, defaultOrderBys, applySecurityRestrictions, context);
}


@Override
public List<DocumentId> retrieveRowIdsByPage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection orderedSelection,int firstRow,int pageLength){
    logger.debug("Getting page: firstRow={}, pageLength={} - {}", firstRow, pageLength, this);
    logger.debug("Using: {}", orderedSelection);
    final ViewId viewId = orderedSelection.getViewId();
    final SqlAndParams sqlAndParams = sqlViewSelect.selectRowIdsByPage().viewEvalCtx(viewEvalCtx).viewId(viewId).firstRowZeroBased(firstRow).pageLength(pageLength).build();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        pstmt.setMaxRows(pageLength);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final ImmutableList.Builder<DocumentId> rowIds = ImmutableList.builder();
        // not important
        final JSONOptions jsonOpts = JSONOptions.newInstance();
        while (rs.next()) {
            final DocumentId rowId = retrieveRowId(rs, jsonOpts);
            if (rowId == null) {
                continue;
            }
            rowIds.add(rowId);
        }
        return rowIds.build();
    } catch (final SQLException | DBException e) {
        throw DBException.wrapIfNeeded(e).setSqlIfAbsent(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


public DocumentId retrieveRowId(ResultSet rs,JSONOptions jsonOpts){
    if (keyColumnNamesMap.isSingleKey()) {
        return retrieveRowId_SingleKey(rs, jsonOpts);
    } else {
        return retrieveRowId_MultiKey(rs, jsonOpts.getAdLanguage());
    }
}


@Override
public String getTableName(){
    return tableName;
}


public DocumentId retrieveRowId_SingleKey(ResultSet rs,JSONOptions jsonOpts){
    final String keyColumnName = keyColumnNamesMap.getSingleKeyColumnName();
    final SqlViewRowFieldLoader fieldLoader = rowFieldLoaders.get(keyColumnName);
    final Object rowIdObj = fieldLoader.retrieveValue(rs, jsonOpts.getAdLanguage());
    return convertToRowId(rowIdObj);
}


@Override
public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return viewFilterDescriptors;
}


public ViewRow.Builder loadViewRow(ResultSet rs,WindowId windowId,JSONOptions jsonOpts){
    final boolean isRecordMissing = DisplayType.toBoolean(rs.getString(SqlViewSelectData.COLUMNNAME_IsRecordMissing));
    if (isRecordMissing) {
        return null;
    }
    final ViewRow.Builder viewRowBuilder = ViewRow.builder(windowId);
    final DocumentId parentRowId = keyColumnNamesMap.retrieveRowId(rs, SqlViewSelectData.COLUMNNAME_Paging_Parent_Prefix, false);
    if (parentRowId != null) {
        viewRowBuilder.setParentRowId(parentRowId);
        viewRowBuilder.setType(DefaultRowType.Line);
    } else {
        viewRowBuilder.setType(DefaultRowType.Row);
    }
    final DocumentId rowId = retrieveRowId(rs, jsonOpts);
    if (rowId == null) {
        logger.warn("No ID found for current row. Skipping the row.");
        return null;
    }
    viewRowBuilder.setRowId(rowId);
    for (final Map.Entry<String, SqlViewRowFieldLoader> fieldNameAndLoader : rowFieldLoaders.entrySet()) {
        final String fieldName = fieldNameAndLoader.getKey();
        final SqlViewRowFieldLoader fieldLoader = fieldNameAndLoader.getValue();
        final Object value = fieldLoader.retrieveValue(rs, jsonOpts.getAdLanguage());
        viewRowBuilder.putFieldValue(fieldName, value);
    }
    if (rowCustomizer != null) {
        rowCustomizer.customizeViewRow(viewRowBuilder);
    }
    return viewRowBuilder;
}


public ImmutableList<IViewRow> loadViewRows(ResultSet rs,ViewEvaluationCtx viewEvalCtx,ViewId viewId,int limit){
    final JSONOptions jsonOpts = viewEvalCtx.toJSONOptions();
    final Map<DocumentId, ViewRow.Builder> rowBuilders = new LinkedHashMap<>();
    final Set<DocumentId> rootRowIds = new HashSet<>();
    while (rs.next()) {
        final ViewRow.Builder rowBuilder = loadViewRow(rs, viewId.getWindowId(), jsonOpts);
        if (rowBuilder == null) {
            continue;
        }
        final DocumentId rowId = rowBuilder.getRowId();
        rowBuilders.put(rowId, rowBuilder);
        if (rowBuilder.isRootRow()) {
            rootRowIds.add(rowId);
        }
        // Enforce limit
        if (limit > 0 && limit <= rowBuilders.size()) {
            break;
        }
    }
    // 
    // Load lines
    if (hasIncludedRows && !rootRowIds.isEmpty()) {
        retrieveRowLines(viewEvalCtx, viewId, DocumentIdsSelection.of(rootRowIds)).forEach(line -> {
            final DocumentId parentId = ViewRow.cast(line).getParentId();
            final ViewRow.Builder rowBuilder = rowBuilders.get(parentId);
            if (rowBuilder == null) {
                // shall not happen
                return;
            }
            rowBuilder.addIncludedRow(line);
        });
    }
    return rowBuilders.values().stream().map(rowBuilder -> rowBuilder.build()).collect(ImmutableList.toImmutableList());
}


@Override
public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(){
    return widgetTypesByFieldName;
}


public String getTableAlias(){
    return tableAlias;
}


@Override
public List<IViewRow> retrievePage(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection orderedSelection,int firstRow,int pageLength){
    logger.debug("Getting page: firstRow={}, pageLength={} - {}", firstRow, pageLength, this);
    logger.debug("Using: {}", orderedSelection);
    final ViewId viewId = orderedSelection.getViewId();
    final SqlAndParams sqlAndParams = sqlViewSelect.selectByPage().viewEvalCtx(viewEvalCtx).viewId(viewId).firstRowZeroBased(firstRow).pageLength(pageLength).build();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        pstmt.setMaxRows(pageLength);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final List<IViewRow> page = loadViewRows(rs, viewEvalCtx, viewId, pageLength);
        return page;
    } catch (final SQLException | DBException e) {
        throw DBException.wrapIfNeeded(e).setSqlIfAbsent(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


@Override
public ViewRowIdsOrderedSelection createOrderedSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewRowIdsOrderedSelection fromSelection,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx){
    return viewRowIdsOrderedSelectionFactory.createOrderedSelectionFromSelection(viewEvalCtx, fromSelection, filters, orderBys, filterConverterCtx);
}


@Override
public void deleteSelection(String selectionId){
    viewRowIdsOrderedSelectionFactory.deleteSelection(selectionId);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("tableName", tableName).toString();
}


@Override
public List<T> retrieveModelsByIds(ViewId viewId,DocumentIdsSelection rowIds,Class<T> modelClass){
    if (rowIds.isEmpty()) {
        return ImmutableList.of();
    }
    final String sqlWhereClause = getSqlWhereClause(viewId, DocumentFilterList.EMPTY, rowIds, SqlOptions.usingTableAlias(getTableAlias()));
    if (Check.isEmpty(sqlWhereClause, true)) {
        logger.warn("Could get the SQL where clause for {}/{}. Returning empty", viewId, rowIds);
        return ImmutableList.of();
    }
    return Services.get(IQueryBL.class).createQueryBuilder(modelClass, getTableName(), PlainContextAware.createUsingOutOfTransaction()).filter(TypedSqlQueryFilter.of(sqlWhereClause)).create().list(modelClass);
}


@Override
public IViewRow retrieveById(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentId rowId){
    final SqlAndParams sqlAndParams = sqlViewSelect.selectById().viewEvalCtx(viewEvalCtx).viewId(viewId).rowId(rowId).build();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        final int limit = 2;
        pstmt = DB.prepareStatement(sqlAndParams.getSql(), ITrx.TRXNAME_ThreadInherited);
        pstmt.setMaxRows(limit);
        DB.setParameters(pstmt, sqlAndParams.getSqlParams());
        rs = pstmt.executeQuery();
        final List<IViewRow> documents = loadViewRows(rs, viewEvalCtx, viewId, limit);
        if (documents.isEmpty()) {
            throw new EntityNotFoundException("No document found for rowId=" + rowId + " in viewId=" + viewId);
        } else if (documents.size() > 1) {
            logger.warn("More than one document found for rowId={} in {}. Returning only the first one from: {}", rowId, this, documents);
            return documents.get(0);
        } else {
            return documents.get(0);
        }
    } catch (final SQLException | DBException e) {
        throw DBException.wrapIfNeeded(e).setSqlIfAbsent(sqlAndParams.getSql(), sqlAndParams.getSqlParams());
    } finally {
        DB.close(rs, pstmt);
    }
}


}