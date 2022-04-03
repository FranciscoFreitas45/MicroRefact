package de.metas.ui.web.material.cockpit.filters;
 import org.compiere.util.Env;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.experimental.UtilityClass;
@UtilityClass
public class DateFilterUtil {

 private  AdMessageKey MSG_FILTER_CAPTION;


public DocumentFilterDescriptor createFilterDescriptor(){
    final DocumentFilterParamDescriptor.Builder standaloneParamDescriptor = DocumentFilterParamDescriptor.builder().setFieldName(DateFilterVO.PARAM_Date).setDisplayName(Services.get(IMsgBL.class).translatable(DateFilterVO.PARAM_Date)).setWidgetType(DocumentFieldWidgetType.LocalDate).setOperator(Operator.EQUAL).setMandatory(true).setShowIncrementDecrementButtons(true);
    return DocumentFilterDescriptor.builder().setFrequentUsed(true).setFilterId(DateFilterVO.FILTER_ID).setDisplayName(Services.get(IMsgBL.class).getTranslatableMsgText(MSG_FILTER_CAPTION)).addParameter(standaloneParamDescriptor).build();
}


public DocumentFilter createFilterToday(){
    return DocumentFilter.builder().setFilterId(DateFilterVO.FILTER_ID).setCaption(Services.get(IMsgBL.class).translatable(DateFilterVO.PARAM_Date)).addParameter(DocumentFilterParam.ofNameOperatorValue(DateFilterVO.PARAM_Date, Operator.EQUAL, Env.getDate(Env.getCtx()))).build();
}


public DateFilterVO extractDateFilterVO(DocumentFilter filter){
    Check.assume(DateFilterVO.FILTER_ID.equals(filter.getFilterId()), "Filter ID is {} but it was {}", DateFilterVO.FILTER_ID, filter);
    return DateFilterVO.builder().date(filter.getParameterValueAsLocalDateOrNull(DateFilterVO.PARAM_Date)).build();
}


}