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
public class ListRowsSupplier implements RowsSupplier{

 private  ImmutableList<IViewRow> rows;


@Override
public int getRowCount(){
    return rows.size();
}


@Override
public IViewRow getRow(int rowIndex){
    Check.assume(rowIndex >= 0, "rowIndex >= 0");
    final int rowsCount = rows.size();
    Check.assume(rowIndex < rowsCount, "rowIndex < {}", rowsCount);
    return rows.get(rowIndex);
}


}