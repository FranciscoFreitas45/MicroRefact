package de.metas.ui.web.handlingunits.trace;
 import de.metas.handlingunits.trace.HUTraceEventQuery;
import de.metas.handlingunits.trace.HUTraceRepository;
import de.metas.process.PInstanceId;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
public class HUTraceResultExtender implements SqlDocumentFilterConverter{

 private  String WHERE_IN_T_SELECTION;

 private  HUTraceRepository huTraceRepository;

 private  SqlDocumentFilterConverter converter;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    if (!filter.hasParameters()) {
        // do whatever the system usually does
        return converter.getSql(sqlParamsOut, filter, sqlOpts, context);
    } else {
        final HUTraceEventQuery huTraceQuery = HuTraceQueryCreator.createTraceQueryFromDocumentFilter(filter);
        final PInstanceId selectionId = huTraceRepository.queryToSelection(huTraceQuery);
        final String sqlPlaceHolder = sqlParamsOut.placeholder(selectionId);
        return String.format(WHERE_IN_T_SELECTION, sqlPlaceHolder);
    }
}


@Override
public boolean canConvert(String filterId){
    return true;
}


public HUTraceResultExtender createForRepositoryAndconverter(HUTraceRepository huTraceRepository,SqlDocumentFilterConverter converter){
    return new HUTraceResultExtender(huTraceRepository, converter);
}


}