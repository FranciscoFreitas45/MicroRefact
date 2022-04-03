package de.metas.ui.web.window.descriptor.factory.standard;
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
public class GenericDocumentSummaryValueProvider implements IDocumentFieldValueProvider{

 private  Logger logger;

 private  GenericDocumentSummaryValueProvider EMPTY;

 private  ImmutableList<FieldValueExtractor> fieldValueExtractors;

 private  ImmutableSet<String> fieldNames;

 private  String fieldName;

 private  String fieldName;

 private  DocumentFieldWidgetType widgetType;

 private  String fieldName;

 private  String formatPattern;

 private  int displayType;


public DecimalFormat getDecimalFormat(){
    final Language language = Env.getLanguage(Env.getCtx());
    return DisplayType.getNumberFormat(displayType, language, formatPattern);
}


public FieldValueExtractor createFieldValueExtractorFromLookupDisplayColumn(ILookupDisplayColumn lookupDisplayColumn,DocumentEntityDescriptor.Builder entityDescriptor){
    final String fieldName = lookupDisplayColumn.getColumnName();
    final DocumentFieldDescriptor.Builder field = entityDescriptor.getFieldBuilder(fieldName);
    if (field == null) {
        return null;
    }
    final DocumentFieldWidgetType widgetType = field.getWidgetType();
    if (widgetType.isDateOrTime()) {
        return new DateFieldValueExtractor(fieldName, widgetType);
    } else if (widgetType.isNumeric()) {
        return new NumericFieldValueExtractor(fieldName, widgetType, lookupDisplayColumn.getFormatPattern());
    }
    return new GenericFieldValueExtractor(fieldName);
}


public List<FieldValueExtractor> extractFieldNamesFromDocumentNo(DocumentEntityDescriptor.Builder entityDescriptor){
    if (entityDescriptor.hasField(WindowConstants.FIELDNAME_DocumentNo)) {
        return ImmutableList.of(new GenericFieldValueExtractor(WindowConstants.FIELDNAME_DocumentNo));
    }
    return ImmutableList.of();
}


public GenericDocumentSummaryValueProvider of(DocumentEntityDescriptor.Builder entityDescriptor){
    List<FieldValueExtractor> fieldValuesExtractors = extractFieldNamesFromLookup(entityDescriptor);
    if (fieldValuesExtractors != null && !fieldValuesExtractors.isEmpty()) {
        return new GenericDocumentSummaryValueProvider(fieldValuesExtractors);
    }
    fieldValuesExtractors = extractFieldNamesFromDocumentNo(entityDescriptor);
    if (fieldValuesExtractors != null && !fieldValuesExtractors.isEmpty()) {
        return new GenericDocumentSummaryValueProvider(fieldValuesExtractors);
    }
    fieldValuesExtractors = extractFieldNamesFromValueName(entityDescriptor);
    if (fieldValuesExtractors != null && !fieldValuesExtractors.isEmpty()) {
        return new GenericDocumentSummaryValueProvider(fieldValuesExtractors);
    }
    return EMPTY;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(fieldValueExtractors).toString();
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
public Set<String> getDependsOnFieldNames(){
    return fieldNames;
}


@Override
public String getFieldName(){
    return fieldName;
}


public List<FieldValueExtractor> extractFieldNamesFromLookup(DocumentEntityDescriptor.Builder entityDescriptor){
    try {
        final DocumentFieldDescriptor.Builder idField = entityDescriptor.getSingleIdFieldBuilderOrNull();
        if (idField == null || idField.isVirtualField()) {
            return ImmutableList.of();
        }
        final ILookupDAO lookupDAO = Services.get(ILookupDAO.class);
        final ITableRefInfo tableRefInfo = lookupDAO.retrieveTableDirectRefInfo(idField.getFieldName());
        final ILookupDisplayInfo displayInfo = lookupDAO.retrieveLookupDisplayInfo(tableRefInfo);
        final ImmutableList<FieldValueExtractor> displayColumnNames = displayInfo.getLookupDisplayColumns().stream().map(lookupDisplayColumn -> createFieldValueExtractorFromLookupDisplayColumn(lookupDisplayColumn, entityDescriptor)).filter(fieldValueExtractor -> fieldValueExtractor != null).collect(ImmutableList.toImmutableList());
        return displayColumnNames;
    } catch (final Exception ex) {
        logger.warn("Failed extracting summary field names for record's lookup for {}. Ignored.", entityDescriptor, ex);
        return ImmutableList.of();
    }
}


public List<FieldValueExtractor> extractFieldNamesFromValueName(DocumentEntityDescriptor.Builder entityDescriptor){
    final ImmutableList.Builder<FieldValueExtractor> fieldNames = ImmutableList.builder();
    if (entityDescriptor.hasField(WindowConstants.FIELDNAME_Value)) {
        fieldNames.add(new GenericFieldValueExtractor(WindowConstants.FIELDNAME_Value));
    }
    if (entityDescriptor.hasField(WindowConstants.FIELDNAME_Name)) {
        fieldNames.add(new GenericFieldValueExtractor(WindowConstants.FIELDNAME_Name));
    }
    return fieldNames.build();
}


@Override
public Object calculateValue(Document document){
    if (fieldValueExtractors.isEmpty()) {
        return null;
    }
    final String summary = fieldValueExtractors.stream().map(valueExtractor -> valueExtractor.extractFieldValueToString(document)).map(// convert empty strings to null
    fieldValue -> Check.isEmpty(fieldValue, true) ? null : fieldValue.trim()).filter(// skip null strings
    fieldValue -> fieldValue != null).collect(// join all field values
    Collectors.joining(" "));
    if (Check.isEmpty(summary, true)) {
        return "";
    }
    return summary;
// return " / " + summary; // don't prefix with "/". That shall be done by frontend if and when needed
}


}