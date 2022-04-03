package de.metas.ui.web.handlingunits.trace;
 import org.springframework.stereotype.Component;
import de.metas.handlingunits.trace.HUTraceRepository;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
@Component
public class HUTraceSqlConverterDecorator implements SqlDocumentFilterConverterDecorator{

 private  HUTraceRepository huTraceRepository;


public SqlDocumentFilterConverter decorate(SqlDocumentFilterConverter converter){
    return HUTraceResultExtender.createForRepositoryAndconverter(huTraceRepository, converter);
}


@Override
public WindowId getWindowId(){
    return WEBUI_HU_Constants.WEBUI_HU_Trace_Window_ID;
}


}