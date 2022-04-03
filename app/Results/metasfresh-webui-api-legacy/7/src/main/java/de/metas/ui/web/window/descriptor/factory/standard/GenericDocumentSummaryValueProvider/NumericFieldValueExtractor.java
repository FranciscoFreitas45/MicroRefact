package de.metas.ui.web.window.descriptor.factory.standard.GenericDocumentSummaryValueProvider;
 import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.ad.service.ILookupDAO;
import org.adempiere.ad.service.ILookupDAO.ILookupDisplayInfo;
import org.adempiere.ad.service.ILookupDAO.ITableRefInfo;
import org.compiere.model.ILookupDisplayColumn;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.Language;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.IDocumentFieldValueProvider;
import de.metas.util.Services;
import lombok.Data;
public class NumericFieldValueExtractor implements FieldValueExtractor{

 private  String fieldName;

 private  String formatPattern;

 private  int displayType;


public DecimalFormat getDecimalFormat(){
    final Language language = Env.getLanguage(Env.getCtx());
    return DisplayType.getNumberFormat(displayType, language, formatPattern);
}


@Override
public String extractFieldValueToString(Document document){
    final Object fieldValue = document.getFieldView(fieldName).getValue();
    if (fieldValue == null) {
        return null;
    }
    try {
        return getDecimalFormat().format(fieldValue);
    } catch (final Exception ex) {
        logger.warn("Failed formatting date field value '{}' using {}. Returning toString().", fieldValue, this, ex);
        return fieldValue.toString();
    }
}


@Override
public String getFieldName(){
    return fieldName;
}


}