package de.metas.ui.web.view;
 import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.Language;
import de.metas.impexp.excel.AbstractExcelExporter;
import de.metas.impexp.excel.CellValue;
import de.metas.impexp.excel.CellValues;
import de.metas.impexp.excel.ExcelExportConstants;
import de.metas.impexp.excel.ExcelFormat;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.util.PageIndex;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
public class ViewExcelExporter extends AbstractExcelExporter{

 private  RowsSupplier rows;

 private  ViewLayout layout;

 private  JSONOptions jsonOpts;

 private  int rowNumber;

 private  int pageSize;

 private  IView view;

 private  JSONOptions jsonOpts;

 private  LoadingCache<PageIndex,ViewResult> cache;

 private  ImmutableList<IViewRow> rows;


@Override
public int getRowCount(){
    return rows.size();
}


@Override
public int getDisplayType(int rowIndex_NOTUSED,int columnIndex){
    return getWidgetType(columnIndex).getDisplayType();
}


public DocumentFieldWidgetType getWidgetType(int columnIndex){
    return layout.getElements().get(columnIndex).getWidgetType();
}


public ViewResult getPage(PageIndex pageIndex){
    try {
        return cache.get(pageIndex);
    } catch (ExecutionException e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


@Override
public int getColumnCount(){
    return layout.getElements().size();
}


@Override
public IViewRow getRow(int rowIndex){
    Check.assume(rowIndex >= 0, "rowIndex >= 0");
    final int rowsCount = rows.size();
    Check.assume(rowIndex < rowsCount, "rowIndex < {}", rowsCount);
    return rows.get(rowIndex);
}


public String getHeaderName(int col){
    return layout.getElements().get(col).getCaption(getLanguage().getAD_Language());
}


@Override
public boolean isColumnPrinted(int col){
    return true;
}


@Override
public boolean isPageBreak(int row,int col){
    return false;
}


@Override
public ViewResult load(PageIndex pageIndex){
    // default
    final ViewRowsOrderBy orderBys = ViewRowsOrderBy.empty(jsonOpts);
    return view.getPage(pageIndex.getFirstRow(), pageIndex.getPageLength(), orderBys);
}


@Override
public List<CellValue> getNextRow(){
    final ArrayList<CellValue> result = new ArrayList<>();
    for (int i = 0; i < getColumnCount(); i++) {
        result.add(getValueAt(rowNumber, i));
    }
    rowNumber++;
    return result;
}


public CellValue getValueAt(int rowIndex,int columnIndex){
    final String fieldName = getFieldName(columnIndex);
    final IViewRow row = getRow(rowIndex);
    if (row == null) {
        return null;
    }
    final Object value = row.getFieldValueAsJsonObject(fieldName, jsonOpts);
    if (JSONNullValue.isNull(value)) {
        return null;
    }
    final DocumentFieldWidgetType widgetType = getWidgetType(columnIndex);
    if (widgetType.isDateOrTime()) {
        return CellValue.ofDate(DateTimeConverters.fromObject(value, widgetType));
    } else if (value instanceof JSONLookupValue) {
        final String valueStr = ((JSONLookupValue) value).getCaption();
        return CellValues.toCellValue(valueStr, widgetType.getDisplayType());
    } else if (value instanceof JSONLookupValuesList) {
        final JSONLookupValuesList jsonLookupValuesList = (JSONLookupValuesList) value;
        final String valueStr = jsonLookupValuesList.getValues().stream().map(lookupValue -> lookupValue.getCaption()).collect(Collectors.joining(", "));
        return CellValue.ofString(valueStr);
    } else {
        return CellValues.toCellValue(value, widgetType.getDisplayType());
    }
}


@Override
public boolean hasNextRow(){
    return rowNumber < getRowCount();
}


@Override
public boolean isFunctionRow(int row){
    return false;
}


@Override
public List<CellValue> getHeaderNames(){
    final ArrayList<CellValue> result = new ArrayList<>();
    for (int i = 0; i < getColumnCount(); i++) {
        result.add(CellValues.toCellValue(getHeaderName(i)));
    }
    return result;
}


public String getFieldName(int columnIndex){
    final Set<DocumentLayoutElementFieldDescriptor> fields = layout.getElements().get(columnIndex).getFields();
    return fields.iterator().next().getField();
}


}