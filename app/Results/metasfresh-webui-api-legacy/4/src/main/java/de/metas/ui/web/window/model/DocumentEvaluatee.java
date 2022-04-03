package de.metas.ui.web.window.model;
 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import org.adempiere.ad.validationRule.IValidationContext;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.CtxName;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee2;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.NonNull;
public class DocumentEvaluatee implements IDocumentAware,IDocumentEvaluatee{

 private  Logger logger;

 private  Document _document;

 private  String _fieldNameInScope;

 private  ImmutableSet<String> _fieldNamesToExclude;


public boolean isFieldInScope(String fieldName){
    return Objects.equals(_fieldNameInScope, fieldName);
}


@Override
public Boolean get_ValueAsBoolean(String variableName,Boolean defaultValue){
    Object valueObj = get_ValueIfExists(variableName, Boolean.class).orElse(null);
    if (valueObj == null && defaultValue == null) {
        valueObj = getDefaultValue(variableName).orElse(null);
    }
    return DisplayType.toBoolean(valueObj, defaultValue);
}


public Date convertToDate(String variableName,Object valueObj){
    if (valueObj == null) {
        return null;
    } else if (valueObj instanceof Date) {
        return (Date) valueObj;
    } else {
        return TimeUtil.asDate(DateTimeConverters.fromObject(valueObj, DocumentFieldWidgetType.ZonedDateTime));
    }
}


public boolean isExcludedField(String fieldName){
    return _fieldNamesToExclude.contains(fieldName);
}


@Override
public Document getDocument(){
    return _document;
}


public Integer convertToInteger(String variableName,Object valueObj){
    if (valueObj == null) {
        return null;
    } else if (valueObj instanceof Integer) {
        return (Integer) valueObj;
    } else if (valueObj instanceof Number) {
        return ((Number) valueObj).intValue();
    } else if (valueObj instanceof IntegerLookupValue) {
        return ((IntegerLookupValue) valueObj).getIdAsInt();
    } else {
        final String valueStr = valueObj.toString().trim();
        if (valueStr.isEmpty()) {
            return null;
        }
        return Integer.parseInt(valueStr);
    }
}


@Override
public IDocumentEvaluatee excludingFields(String fieldNamesToExclude){
    return new DocumentEvaluatee(_document, _fieldNameInScope, ImmutableSet.copyOf(fieldNamesToExclude));
}


@Override
public IDocumentEvaluatee fieldInScope(String fieldNameInScope){
    if (isFieldInScope(fieldNameInScope)) {
        return this;
    }
    return new DocumentEvaluatee(_document, fieldNameInScope, ImmutableSet.of());
}


@Override
public Integer get_ValueAsInt(String variableName,Integer defaultValue){
    Object valueObj = get_ValueIfExists(variableName, Integer.class).orElse(null);
    if (valueObj == null && defaultValue == null) {
        valueObj = getDefaultValue(variableName).orElse(null);
    }
    return valueObj == null ? defaultValue : convertToInteger(variableName, valueObj);
}


public String convertToString(String variableName,Object value){
    if (value == null) {
        return null;
    } else if (value instanceof Boolean) {
        return DisplayType.toBooleanString((Boolean) value);
    } else if (value instanceof String) {
        return value.toString();
    } else if (value instanceof LookupValue) {
        return ((LookupValue) value).getIdAsString();
    } else if (value instanceof java.util.Date) {
        final java.util.Date valueDate = (java.util.Date) value;
        return Env.toString(valueDate);
    } else {
        return value.toString();
    }
}


@Override
public BigDecimal get_ValueAsBigDecimal(String variableName,BigDecimal defaultValue){
    Object valueObj = get_ValueIfExists(variableName, BigDecimal.class).orElse(null);
    if (valueObj == null && defaultValue == null) {
        valueObj = getDefaultValue(variableName).orElse(null);
    }
    return valueObj == null ? defaultValue : convertToBigDecimal(variableName, valueObj);
}


public Optional<Object> getDefaultValue(String variableName){
    // FIXME: hardcoded default to avoid a lot of warnings
    if (variableName.endsWith("_ID")) {
        return Optional.of(InterfaceWrapperHelper.getFirstValidIdByColumnName(variableName) - 1);
    }
    // TODO: find some defaults?
    return Optional.empty();
}


public IDocumentFieldView getDocumentFieldOrNull(String name){
    return _document.getFieldViewOrNull(name);
}


@Override
public java.util.Date get_ValueAsDate(String variableName,java.util.Date defaultValue){
    Object valueObj = get_ValueIfExists(variableName, java.util.Date.class).orElse(null);
    if (valueObj == null && defaultValue == null) {
        valueObj = getDefaultValue(variableName).orElse(null);
    }
    return valueObj == null ? defaultValue : convertToDate(variableName, valueObj);
}


public IDocumentEvaluatee getParentEvaluateeOrNull(){
    return _document.getParentDocumentEvaluateeOrNull();
}


public Properties getCtx(){
    return _document.getCtx();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("fieldInScope", _fieldNameInScope).addValue(_document).toString();
}


@Override
public String get_ValueAsString(String variableName){
    Object valueObj = get_ValueIfExists(variableName, String.class).orElse(null);
    if (valueObj == null) {
        valueObj = getDefaultValue(variableName).orElse(null);
    }
    return valueObj == null ? null : convertToString(variableName, valueObj);
}


public Object getGlobalContext(String variableName,Class<?> targetType){
    if (Integer.class.equals(targetType)) {
        return Env.getContextAsInt(getCtx(), variableName);
    } else if (java.util.Date.class.equals(targetType) || Timestamp.class.equals(targetType)) {
        return Env.getContextAsDate(getCtx(), variableName);
    } else if (Integer.class.equals(targetType) || int.class.equals(targetType)) {
        return Env.getContextAsInt(getCtx(), variableName);
    } else if (Boolean.class.equals(targetType)) {
        final String valueStr = Env.getContext(getCtx(), variableName);
        return DisplayType.toBoolean(valueStr, null);
    }
    final String valueStr = Env.getContext(getCtx(), variableName);
    // 
    // Use some default value
    if (Check.isEmpty(valueStr)) {
        // FIXME hardcoded. when we will do a proper login, this won't be necessary
        if (variableName.startsWith("$Element_")) {
            return Boolean.FALSE;
        }
    }
    return valueStr;
}


public BigDecimal convertToBigDecimal(String variableName,Object valueObj){
    if (valueObj == null) {
        return null;
    } else if (valueObj instanceof BigDecimal) {
        return (BigDecimal) valueObj;
    } else {
        final String valueStr = valueObj.toString().trim();
        if (valueStr.isEmpty()) {
            return null;
        }
        return new BigDecimal(valueStr);
    }
}


@Override
public Optional<Object> get_ValueIfExists(String variableName,Class<?> targetType){
    if (variableName == null) {
        return Optional.empty();
    }
    if (isExcludedField(variableName)) {
        return Optional.empty();
    }
    if (WindowConstants.CONTEXTVAR_NextLineNo.equals(variableName)) {
        final Document parentDocument = _document.getParentDocument();
        if (parentDocument != null) {
            final DetailId detailId = _document.getEntityDescriptor().getDetailId();
            final int nextLineNo = parentDocument.getIncludedDocumentsCollection(detailId).getNextLineNo();
            return Optional.of(nextLineNo);
        }
    }
    if (IValidationContext.PARAMETER_ContextTableName.equals(variableName)) {
        return Optional.of(_document.getEntityDescriptor().getTableName());
    }
    // 
    // Check global context variable if this is an explicit global variable
    boolean globalContextChecked = false;
    if (CtxName.isExplicitGlobal(variableName)) {
        final Object value = getGlobalContext(variableName, targetType);
        globalContextChecked = true;
        if (value != null) {
            return Optional.of(value);
        }
    }
    // 
    // Document field
    final IDocumentFieldView documentField = getDocumentFieldOrNull(variableName);
    boolean inScopeField = false;
    if (documentField != null) {
        inScopeField = isFieldInScope(documentField.getFieldName());
        if (inScopeField) {
            logger.trace("Skip evaluation of {} because it's actually the field for whom we do the whole evaluation", documentField);
        } else {
            final Object documentFieldValue = documentField.getValue();
            if (documentFieldValue != null) {
                return Optional.of(documentFieldValue);
            }
        }
    }
    // 
    // Document's dynamic attribute
    if (_document.hasDynAttribute(variableName)) {
        final Object value = _document.getDynAttribute(variableName);
        if (value != null) {
            return Optional.of(value);
        }
    }
    // 
    // Check parent
    final IDocumentEvaluatee parentEvaluatee = getParentEvaluateeOrNull();
    final boolean hasParentEvaluatee = parentEvaluatee != null;
    if (hasParentEvaluatee) {
        final Optional<Object> value = parentEvaluatee.get_ValueIfExists(variableName, targetType);
        if (value.isPresent()) {
            return value;
        }
    }
    // 
    // If it's about the field in scope, try checking the global context
    if (inScopeField) {
        if (!globalContextChecked) {
            final Object value = getGlobalContext(variableName, targetType);
            globalContextChecked = true;
            if (value != null) {
                return Optional.of(value);
            }
        }
    }
    // 
    // Fallback: Check again the documentField and assume some defaults
    if (!hasParentEvaluatee) {
        if (documentField != null) {
            final Class<?> valueClass = documentField.getValueClass();
            if (StringLookupValue.class.equals(valueClass)) {
                // corner case: e.g. Field: C_Order.IncotermLocation's DisplayLogic=@Incoterm@!''
                return Optional.of("");
            }
        }
        final Optional<Object> defaultValue = getDefaultValue(variableName);
        if (defaultValue.isPresent()) {
            return defaultValue;
        }
    }
    // 
    // Value not found
    if (logger.isTraceEnabled()) {
        logger.trace("Variable '{}' not found.", variableName);
        logger.trace("Existing properties are: {}", _document.getFieldNames());
        logger.trace("Existing dyn attributes are: {}", _document.getAvailableDynAttributes());
        logger.trace("Was field in scope: {} (fieldInScope={})", inScopeField, _fieldNameInScope);
    }
    return Optional.empty();
}


}