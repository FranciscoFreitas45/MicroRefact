package de.metas.ui.web.window.model;
 import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.exceptions.DocumentFieldNotLookupException;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.util.NumberUtils;
import lombok.NonNull;
public class DocumentField implements IDocumentField{

 private  Logger logger;

 private  DocumentFieldDescriptor descriptor;

 private  Document _document;

 private  Optional<LookupDataSource> _lookupDataSource;

 private  boolean lookupValuesStaled;

 private  ICalloutField _calloutField;

 private  Object _initialValue;

 private  Object _valueOnCheckout;

 private  Object _value;

 private  LogicExpressionResult MANDATORY_InitialValue;

 private  LogicExpressionResult _mandatory;

 private  LogicExpressionResult READONLY_InitialValue;

 private  LogicExpressionResult _readonly;

 private  LogicExpressionResult DISPLAYED_InitialValue;

 private  LogicExpressionResult _displayed;

 private  DocumentValidStatus _validStatus;


@Override
public DocumentFieldDescriptor getDescriptor(){
    return descriptor;
}


@Override
public Object getInitialValue(){
    return _initialValue;
}


@Override
public int getValueAsInt(int defaultValueWhenNull){
    final Integer valueInt = convertToValueClass(_value, DocumentFieldWidgetType.Integer, Integer.class);
    return valueInt == null ? defaultValueWhenNull : valueInt;
}


@Override
public void setMandatory(LogicExpressionResult mandatory,IDocumentChangesCollector changesCollector){
    final LogicExpressionResult mandatoryOld = _mandatory;
    _mandatory = mandatory;
    // 
    // Update validStatus if mandatory flag changed
    if (!mandatoryOld.equalsByValue(mandatory)) {
        updateValid(changesCollector);
    }
}


@Override
public DocumentZoomIntoInfo getZoomIntoInfo(){
    final IntegerLookupValue lookupValue = getValueAs(IntegerLookupValue.class);
    return getLookupDataSource().getDocumentZoomInto(lookupValue != null ? lookupValue.getIdAsInt() : -1).overrideWindowIdIfPossible(getZoomIntoWindowId());
}


@Override
public ICalloutField asCalloutField(){
    if (_calloutField == null) {
        _calloutField = new DocumentFieldAsCalloutField(this);
    }
    return _calloutField;
}


@Override
public void setInitialValue(Object initialValue,IDocumentChangesCollector changesCollector){
    final Object initialValueConv = convertToValueClassAndCorrect(initialValue);
    if (logger.isTraceEnabled()) {
        logger.trace("setInitialValue: {} = {}", getFieldName(), initialValueConv);
    }
    _initialValue = initialValueConv;
    // 
    // Update checkout value too because we consider initialization as first checkout
    _valueOnCheckout = initialValueConv;
    // 
    // Update the current value too
    // final Object valueOld = _value;
    _value = initialValueConv;
    // Update valid status
    // NOTE: usually this method is called on initialization
    updateValid(changesCollector);
}


@Override
public LogicExpressionResult getDisplayed(){
    return _displayed;
}


@Override
public LogicExpressionResult getReadonly(){
    return _readonly;
}


@Override
public DocumentValidStatus updateStatusIfInitialInvalidAndGet(IDocumentChangesCollector changesCollector){
    if (_validStatus.isInitialInvalid()) {
        updateValid(changesCollector);
    }
    return _validStatus;
}


public DocumentValidStatus computeValidStatus(){
    // Consider virtual fields as valid because there is nothing we can do about them
    if (isVirtualField()) {
        return DocumentValidStatus.validField(getFieldName(), isInitialValue());
    }
    // Check mandatory constraint
    if (isMandatory() && getValue() == null) {
        return DocumentValidStatus.invalidFieldMandatoryNotFilled(getFieldName(), isInitialValue());
    }
    return DocumentValidStatus.validField(getFieldName(), isInitialValue());
}


@Override
public T getValueAs(Class<T> returnType){
    Preconditions.checkNotNull(returnType, "returnType shall not be null");
    // N/A
    final DocumentFieldWidgetType widgetType = null;
    final T value = convertToValueClass(_value, widgetType, returnType);
    return value;
}


@Override
public boolean hasChangesToSave(){
    if (isVirtualField()) {
        return false;
    }
    return !isInitialValue();
}


@Override
public DocumentField copy(Document document,CopyMode copyMode){
    return new DocumentField(this, document, copyMode);
}


public Object convertToValueClassAndCorrect(Object value){
    final Object valueConv = convertToValueClass(value);
    // 
    // Apply corrections if needed
    // 
    // Apply number precision
    if (valueConv instanceof BigDecimal) {
        final Integer precision = getWidgetType().getStandardNumberPrecision();
        if (precision != null) {
            final BigDecimal valueBDCorrected = NumberUtils.setMinimumScale((BigDecimal) valueConv, precision);
            return valueBDCorrected;
        } else {
            final BigDecimal valueBDCorrected = NumberUtils.stripTrailingDecimalZeros((BigDecimal) valueConv);
            return valueBDCorrected;
        }
    } else if (valueConv instanceof String) {
        if (((String) valueConv).isEmpty()) {
            return null;
        }
    }
    return valueConv;
}


@Override
public void setDisplayed(LogicExpressionResult displayed){
    _displayed = displayed;
}


@Override
public LookupValuesList getLookupValues(){
    final LookupDataSource lookupDataSource = getLookupDataSource();
    final Evaluatee ctx = getDocument().asEvaluatee();
    final LookupValuesList values = lookupDataSource.findEntities(ctx);
    lookupValuesStaled = false;
    return values == null ? LookupValuesList.EMPTY : values;
}


@Override
public LookupValuesList getLookupValuesForQuery(String query){
    final LookupDataSource lookupDataSource = getLookupDataSource();
    final Evaluatee ctx = getDocument().asEvaluatee();
    final LookupValuesList values = lookupDataSource.findEntities(ctx, query);
    lookupValuesStaled = false;
    return values;
}


@Override
public Object getValueAsJsonObject(JSONOptions jsonOpts){
    Object value = getValue();
    if (value == null) {
        return null;
    }
    // 
    // If we are dealing with a lookup value, make, sure it's translated (see https://github.com/metasfresh/metasfresh-webui-api/issues/311 )
    final LookupDataSource lookupDataSource = getLookupDataSourceOrNull();
    if (lookupDataSource != null && value instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) value;
        final ITranslatableString displayNameTrl = lookupValue.getDisplayNameTrl();
        if (!displayNameTrl.isTranslatedTo(jsonOpts.getAdLanguage())) {
            final LookupValue lookupValueNew = lookupDataSource.findById(lookupValue.getId());
            value = lookupValueNew;
        }
    }
    return Values.valueToJsonObject(value, jsonOpts);
}


@Override
public boolean getValueAsBoolean(){
    final Boolean valueBoolean = convertToValueClass(_value, DocumentFieldWidgetType.YesNo, Boolean.class);
    return valueBoolean != null && valueBoolean.booleanValue();
}


@Override
public boolean isLookupValuesStale(){
    final LookupDataSource lookupDataSource = getLookupDataSourceOrNull();
    if (lookupDataSource == null) {
        return false;
    }
    return lookupValuesStaled;
}


public T convertToValueClass(Object value,DocumentFieldWidgetType widgetType,Class<T> targetType){
    return descriptor.convertToValueClass(value, widgetType, targetType, getLookupDataSourceOrNull());
}


@Override
public LogicExpressionResult getMandatory(){
    return _mandatory;
}


@Override
public boolean setLookupValuesStaled(String triggeringFieldName){
    final LookupDataSource lookupDataSource = getLookupDataSourceOrNull();
    if (lookupDataSource == null) {
        return false;
    }
    lookupValuesStaled = true;
    logger.trace("Marked lookup values as staled (->{}): {}", triggeringFieldName, this);
    return true;
}


@Override
public Document getDocument(){
    return _document;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    final LookupDataSource lookupDataSource = getLookupDataSourceOrNull();
    if (lookupDataSource == null) {
        return Optional.empty();
    }
    return lookupDataSource.getZoomIntoWindowId();
}


@Override
public DocumentValidStatus getValidStatus(){
    return _validStatus;
}


public LookupDataSource getLookupDataSource(){
    return _lookupDataSource.orElseThrow(() -> new DocumentFieldNotLookupException(getFieldName()));
}


@Override
public Object getOldValue(){
    return _valueOnCheckout;
}


@Override
public void setReadonly(LogicExpressionResult readonly){
    _readonly = readonly;
}


@Override
public Object getValue(){
    return _value;
}


@Override
public void setValue(Object value,IDocumentChangesCollector changesCollector){
    final Object valueNew = convertToValueClassAndCorrect(value);
    final Object valueOld = _value;
    _value = valueNew;
    if (logger.isTraceEnabled() && !DataTypes.equals(valueNew, valueOld)) {
        logger.trace("setValue: {} = {} <- {}", getFieldName(), valueNew, valueOld);
    }
    updateValid(changesCollector);
}


public boolean isInitialValue(){
    return DataTypes.equals(_value, _initialValue);
}


public void updateValid(IDocumentChangesCollector changesCollector){
    final DocumentValidStatus validStatusOld = _validStatus;
    final DocumentValidStatus validStatusNew = computeValidStatus();
    _validStatus = validStatusNew;
    // Collect validStatus changed event
    if (!NullDocumentChangesCollector.isNull(changesCollector) && !Objects.equals(validStatusOld, validStatusNew)) {
        // logger.debug("updateValid: {}: {} <- {}", getFieldName(), validNew, validOld);
        changesCollector.collectValidStatus(this);
    }
}


@Override
public String toString(){
    // NOTE: try keeping this string short...
    final String tableName = getDocument().getEntityDescriptor().getTableNameOrNull();
    final String fieldName = getFieldName();
    final String fieldNameFQ = tableName == null ? fieldName : tableName + "." + fieldName;
    return MoreObjects.toStringHelper(this).add("fieldName", fieldNameFQ).add("value", _value).add("initalValue", _initialValue).toString();
}


public LookupDataSource getLookupDataSourceOrNull(){
    return _lookupDataSource.orElse(null);
}


}