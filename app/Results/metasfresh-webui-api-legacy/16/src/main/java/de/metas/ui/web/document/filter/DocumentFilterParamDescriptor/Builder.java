package de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
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
public class Builder {

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


public Builder setAutoFilterInitialValue(Object autoFilterInitialValue){
    this.autoFilterInitialValue = autoFilterInitialValue;
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


public DocumentFieldWidgetType getWidgetType(){
    return widgetType;
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


public Builder setLookupDescriptor(LookupDescriptor lookupDescriptor){
    return setLookupDescriptor(Optional.ofNullable(lookupDescriptor));
}


public DocumentFilterParamDescriptor build(){
    return new DocumentFilterParamDescriptor(this);
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    this.widgetType = widgetType;
    return this;
}


public ITranslatableString getDisplayName(){
    return displayName;
}


public Builder setOperator(Operator operator){
    Check.assumeNotNull(operator, "Parameter operator is not null");
    this.operator = operator;
    return this;
}


public Builder setJoinAnd(boolean joinAnd){
    this.joinAnd = joinAnd;
    return this;
}


public String getFieldName(){
    return fieldName;
}


public Builder barcodeScannerType(BarcodeScannerType barcodeScannerType){
    this.barcodeScannerType = barcodeScannerType;
    return this;
}


public Builder setDefaultValueTo(Object defaultValueTo){
    this.defaultValueTo = defaultValueTo;
    return this;
}


}