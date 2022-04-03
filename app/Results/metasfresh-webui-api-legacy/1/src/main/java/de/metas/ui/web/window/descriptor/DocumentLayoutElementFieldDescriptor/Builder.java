package de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
 import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.ImmutableTranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.devices.JSONDeviceDescriptor;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElementField.JSONFieldType;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElementField.JSONLookupSource;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  Logger logger;

 private  ITranslatableString HARDCODED_FIELD_EMPTY_TEXT;

 private  String fieldName;

 private  ITranslatableString caption;

 private  FieldType fieldType;

 private  String tooltipIconName;

 private  ITranslatableString listNullItemCaption;

 private  ITranslatableString emptyFieldText;

 private  boolean publicField;

 private  List<JSONDeviceDescriptor> _devices;

 private  LookupSource lookupSource;

 private  Optional<String> lookupTableName;

 private  int lookupSearchStringMinLength;

 private  Optional<Duration> lookupSearchStartDelay;

 private  boolean consumed;

 private  DocumentFieldDescriptor.Builder documentFieldBuilder;

 private  boolean supportZoomInto;


public Builder setLookupInfos(LookupDescriptor lookupDescriptor){
    if (lookupDescriptor != null) {
        this.lookupSource = lookupDescriptor.getLookupSourceType();
        this.lookupTableName = lookupDescriptor.getTableName();
        this.lookupSearchStringMinLength = lookupDescriptor.getSearchStringMinLength();
        this.lookupSearchStartDelay = lookupDescriptor.getSearchStartDelay();
    } else {
        this.lookupSource = null;
        this.lookupTableName = Optional.empty();
        this.lookupSearchStringMinLength = -1;
        this.lookupSearchStartDelay = Optional.empty();
    }
    return this;
}


public List<JSONDeviceDescriptor> getDevices(){
    if (_devices == null) {
        return ImmutableList.of();
    }
    return ImmutableList.copyOf(_devices);
}


public Builder setListNullItemCaption(ITranslatableString listNullItemCaption){
    this.listNullItemCaption = listNullItemCaption;
    return this;
}


public boolean isPublicField(){
    return publicField;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setLookupSourceAsText(){
    this.lookupSource = LookupSource.text;
    this.lookupTableName = Optional.empty();
    this.lookupSearchStringMinLength = -1;
    this.lookupSearchStartDelay = Optional.empty();
    return this;
}


public Builder addDevices(List<JSONDeviceDescriptor> devices){
    if (devices == null || devices.isEmpty()) {
        return this;
    }
    if (_devices == null) {
        _devices = new ArrayList<>();
    }
    _devices.addAll(devices);
    return this;
}


public ITranslatableString getCaption(){
    return caption;
}


public boolean isSupportZoomInto(){
    return supportZoomInto;
}


public Builder setConsumed(){
    consumed = true;
    return this;
}


public boolean isConsumed(){
    return consumed;
}


public Builder trackField(DocumentFieldDescriptor.Builder field){
    documentFieldBuilder = field;
    return this;
}


public DocumentLayoutElementFieldDescriptor build(){
    setConsumed();
    final DocumentLayoutElementFieldDescriptor result = new DocumentLayoutElementFieldDescriptor(this);
    logger.trace("Build {} for {}", result, this);
    return result;
}


public Optional<String> getLookupTableName(){
    if (lookupTableName != null) {
        return lookupTableName;
    } else if (documentFieldBuilder != null) {
        return documentFieldBuilder.getLookupTableName();
    } else {
        return Optional.empty();
    }
}


public boolean isSpecialFieldToExcludeFromLayout(){
    return documentFieldBuilder != null && documentFieldBuilder.isSpecialFieldToExcludeFromLayout();
}


public Builder setFieldType(FieldType fieldType){
    this.fieldType = fieldType;
    return this;
}


public Builder setTooltipIconName(String tooltipIconName){
    this.tooltipIconName = tooltipIconName;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("fieldName", fieldName).add("publicField", publicField).add("fieldActions", (_devices == null || _devices.isEmpty()) ? null : _devices).add("consumed", consumed).toString();
}


public Builder setPublicField(boolean publicField){
    this.publicField = publicField;
    return this;
}


public Builder setSupportZoomInto(boolean supportZoomInto){
    this.supportZoomInto = supportZoomInto;
    return this;
}


public String getFieldName(){
    Check.assumeNotEmpty(fieldName, "fieldName is not empty");
    return fieldName;
}


public boolean isRegularField(){
    return fieldType == null;
}


public Builder setEmptyFieldText(ITranslatableString emptyFieldText){
    this.emptyFieldText = emptyFieldText;
    return this;
}


}