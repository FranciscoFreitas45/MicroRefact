package de.metas.ui.web.view.ViewExcelExporter;
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
public class AllRowsSupplier implements RowsSupplier{

 private  int pageSize;

 private  IView view;

 private  JSONOptions jsonOpts;

 private  LoadingCache<PageIndex,ViewResult> cache;


@Override
public int getRowCount(){
    return (int) view.size();
}


@Override
public ViewResult load(PageIndex pageIndex){
    // default
    final ViewRowsOrderBy orderBys = ViewRowsOrderBy.empty(jsonOpts);
    return view.getPage(pageIndex.getFirstRow(), pageIndex.getPageLength(), orderBys);
}


public ViewResult getPage(PageIndex pageIndex){
    try {
        return cache.get(pageIndex);
    } catch (ExecutionException e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


@Override
public IViewRow getRow(int rowIndex){
    final ViewResult page = getPage(PageIndex.getPageContainingRow(rowIndex, pageSize));
    final int rowIndexInPage = rowIndex - page.getFirstRow();
    if (rowIndexInPage < 0) {
        // shall not happen
        return null;
    }
    final List<IViewRow> rows = page.getPage();
    if (rowIndexInPage >= rows.size()) {
        return null;
    }
    return rows.get(rowIndexInPage);
}


}