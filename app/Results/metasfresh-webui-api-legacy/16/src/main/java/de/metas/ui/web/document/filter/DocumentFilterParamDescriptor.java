package de.metas.ui.web.document.filter;
 import java.util.Optional;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.factory.standard.DescriptorsFactoryHelper;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
@Value
public class DocumentFilterParamDescriptor {

 private  Logger logger;

 private  boolean joinAnd;

 private  String parameterName;

 private  String fieldName;

 private  DocumentFieldWidgetType widgetType;

 private  Class<?> valueClass;

 private  ITranslatableString displayName;

 private  boolean showIncrementDecrementButtons;

 private  Operator operator;

 private  Object defaultValue;

 private  Object defaultValueTo;

 private  boolean mandatory;

 private  Optional<LookupDescriptor> lookupDescriptor;

 public  String AUTOFILTER_INITIALVALUE_DATE_NOW;

 private  Object autoFilterInitialValue;

 private  BarcodeScannerType barcodeScannerType;

 private  boolean joinAnd;

 private  String fieldName;

 private  String parameterName;

 private  DocumentFieldWidgetType widgetType;

 private  BarcodeScannerType barcodeScannerType;

 private  ITranslatableString displayName;

 private  Operator operator;

 private  Object defaultValue;

 private  Object defaultValueTo;

 private  Optional<LookupDescriptor> lookupDescriptor;

 private  boolean mandatory;

 private  boolean showIncrementDecrementButtons;

 private  Object autoFilterInitialValue;


public Builder setDefaultValue(Object defaultValue){
    this.defaultValue = defaultValue;
    return this;
}


public Builder setMandatory(boolean mandatory){
    this.mandatory = mandatory;
    return this;
}


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public Builder setParameterName(String parameterName){
    this.parameterName = parameterName;
    return this;
}


public Builder setShowIncrementDecrementButtons(boolean showIncrementDecrementButtons){
    this.showIncrementDecrementButtons = showIncrementDecrementButtons;
    return this;
}


public Builder setDisplayName(String displayName){
    this.displayName = TranslatableStrings.constant(displayName);
    return this;
}


public boolean isAutoFilterInitialValueIsDateNow(){
    return widgetType.isDateOrTime() && AUTOFILTER_INITIALVALUE_DATE_NOW.equals(autoFilterInitialValue);
}


public Builder builder(){
    return new Builder();
}


public Builder setOperator(Operator operator){
    Check.assumeNotNull(operator, "Parameter operator is not null");
    this.operator = operator;
    return this;
}


public Builder barcodeScannerType(BarcodeScannerType barcodeScannerType){
    this.barcodeScannerType = barcodeScannerType;
    return this;
}


public Builder setDefaultValueTo(Object defaultValueTo){
    this.defaultValueTo = defaultValueTo;
    return this;
}


public boolean isAutoFilter(){
    return autoFilterInitialValue != null;
}


public Builder setAutoFilterInitialValue(Object autoFilterInitialValue){
    this.autoFilterInitialValue = autoFilterInitialValue;
    return this;
}


public Object getDefaultValueToConverted(){
    return convertValueToFieldType(getDefaultValueTo());
}


public DocumentFieldWidgetType getWidgetType(){
    return widgetType;
}


public Object convertValueFromJson(Object jsonValue){
    return convertValueToFieldType(jsonValue);
}


public Optional<LookupDataSource> getLookupDataSource(){
    return lookupDescriptor.map(LookupDataSourceFactory.instance::getLookupDataSource);
}


public Builder setLookupDescriptor(LookupDescriptor lookupDescriptor){
    return setLookupDescriptor(Optional.ofNullable(lookupDescriptor));
}


public boolean isRange(){
    return operator != null && operator.isRangeOperator();
}


public Object convertValueToFieldType(Object value){
    Object valueConv = DataTypes.convertToValueClass(getFieldName(), value, getWidgetType(), getValueClass(), getLookupDataSource().orElse(null));
    // Convert empty string to null
    // This is a workaround until task https://github.com/metasfresh/metasfresh-webui-frontend/issues/2040 is done.
    if (valueConv instanceof String && ((String) valueConv).isEmpty()) {
        valueConv = null;
        logger.warn("Converted empty string to null for value={}, descriptor={}. \n This issue shall be solved by https://github.com/metasfresh/metasfresh-webui-frontend/issues/2040.", value, this);
    }
    return valueConv;
}


public ITranslatableString getDisplayName(){
    return displayName;
}


public Object getDefaultValueConverted(){
    return convertValueToFieldType(getDefaultValue());
}


public DocumentFilterParamDescriptor build(){
    return new DocumentFilterParamDescriptor(this);
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    this.widgetType = widgetType;
    return this;
}


public Builder setJoinAnd(boolean joinAnd){
    this.joinAnd = joinAnd;
    return this;
}


public String getFieldName(){
    return fieldName;
}


}