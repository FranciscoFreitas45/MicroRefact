package de.metas.ui.web.handlingunits;
 import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.adempiere.mm.attributes.api.AttributeConstants;
import org.adempiere.mm.attributes.spi.IAttributeValueContext;
import org.adempiere.mm.attributes.spi.impl.DefaultAttributeValueContext;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.X_M_Attribute;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.IHUAware;
import de.metas.handlingunits.attribute.HUAttributeConstants;
import de.metas.handlingunits.attribute.IAttributeValue;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageListener;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.product.ProductId;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IViewRowAttributes;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.view.json.JSONViewRowAttributes;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.exceptions.DocumentFieldReadonlyException;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.MutableDocumentFieldChangedEvent;
import de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
public class HUEditorRowAttributes implements IViewRowAttributes{

 private  DocumentPath documentPath;

 private  IAttributeStorage attributesStorage;

 private  Supplier<ViewRowAttributesLayout> layoutSupplier;

 private  ImmutableSet<String> readonlyAttributeNames;

 private  ImmutableSet<String> hiddenAttributeNames;

@Getter
 private  ImmutableSet<String> mandatoryAttributeNames;

 private  DocumentPath documentPath;


public boolean isReadonly(String attributeName){
    return readonlyAttributeNames.contains(attributeName);
}


public boolean extractIsReadonly(IAttributeStorage attributesStorage){
    final I_M_HU hu = IHUAware.getM_HUOrNull(attributesStorage);
    if (hu == null) {
        return true;
    }
    if (!hu.isActive()) {
        return true;
    }
    final String huStatus = hu.getHUStatus();
    if (!X_M_HU.HUSTATUS_Planning.equals(huStatus)) {
        return true;
    }
    // not readonly
    return false;
}


public String getValueAsString(String attributeName){
    return attributesStorage.getValueAsString(attributeName);
}


public Object convertFromJson(I_M_Attribute attribute,Object jsonValue){
    if (jsonValue == null) {
        return null;
    }
    final String attributeValueType = attributesStorage.getAttributeValueType(attribute);
    if (X_M_Attribute.ATTRIBUTEVALUETYPE_Date.equals(attributeValueType)) {
        final LocalDate localDate = DateTimeConverters.fromObjectToLocalDate(jsonValue.toString());
        if (localDate == null) {
            return null;
        }
        // convert the LocalDate to ZonedDateTime using session's time zone,
        // because later on the date is converted to Timestamp using system's default time zone.
        // And we want to have a valid date for session's timezone.
        final ZoneId zoneId = UserSession.getTimeZoneOrSystemDefault();
        return localDate.atStartOfDay(zoneId);
    } else {
        return jsonValue;
    }
}


public HUEditorRowAttributes cast(IViewRowAttributes attributes){
    return (HUEditorRowAttributes) attributes;
}


@Override
public ViewRowAttributesLayout getLayout(){
    return layoutSupplier.get();
}


@Override
public LookupValuesList getAttributeTypeahead(String attributeName,String query){
    final I_M_Attribute attribute = attributesStorage.getAttributeByValueKeyOrNull(attributeName);
    return attributesStorage.getAttributeValue(attribute).getAvailableValues().stream().map(itemNP -> LookupValue.fromNamePair(itemNP)).collect(LookupValuesList.collect()).filter(LookupValueFilterPredicates.of(query), 0, 10);
}


public void bind(IAttributeStorage storage,DocumentPath documentPath){
    final AttributeStorage2ExecutionEventsForwarder forwarder = new AttributeStorage2ExecutionEventsForwarder(documentPath);
    storage.addListener(forwarder);
}


public Optional<LocalDate> getBestBeforeDate(){
    if (!attributesStorage.hasAttribute(AttributeConstants.ATTR_BestBeforeDate)) {
        return Optional.empty();
    }
    final LocalDate bestBeforeDate = attributesStorage.getValueAsLocalDate(AttributeConstants.ATTR_BestBeforeDate);
    return Optional.ofNullable(bestBeforeDate);
}


@Override
public void onAttributeValueChanged(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue,Object valueOld){
    forwardEvent(storage, attributeValue);
}


public boolean isMandatory(String fieldName){
    return mandatoryAttributeNames.contains(fieldName);
}


@Override
public void onAttributeValueDeleted(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue){
    throw new UnsupportedOperationException();
}


public void forwardEvent(IAttributeStorage storage,IAttributeValue attributeValue){
    final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollector();
    final String attributeName = HUEditorRowAttributesHelper.extractAttributeName(attributeValue);
    final Object jsonValue = HUEditorRowAttributesHelper.extractJSONValue(storage, attributeValue, JSONOptions.newInstance());
    final DocumentFieldWidgetType widgetType = HUEditorRowAttributesHelper.extractWidgetType(attributeValue);
    changesCollector.collectEvent(MutableDocumentFieldChangedEvent.of(documentPath, attributeName, widgetType).setValue(jsonValue));
}


public JSONDocumentField toJSONDocumentField(IAttributeValue attributeValue,JSONOptions jsonOpts){
    final String fieldName = HUEditorRowAttributesHelper.extractAttributeName(attributeValue);
    final Object jsonValue = HUEditorRowAttributesHelper.extractJSONValue(attributesStorage, attributeValue, jsonOpts);
    final DocumentFieldWidgetType widgetType = HUEditorRowAttributesHelper.extractWidgetType(attributeValue);
    return JSONDocumentField.ofNameAndValue(fieldName, jsonValue).setDisplayed(isDisplayed(fieldName)).setMandatory(isMandatory(fieldName)).setReadonly(isReadonly(fieldName)).setWidgetType(JSONLayoutWidgetType.fromNullable(widgetType));
}


public boolean isDisplayed(String attributeName){
    return !hiddenAttributeNames.contains(attributeName);
}


@Override
public LookupValuesList getAttributeDropdown(String attributeName){
    final I_M_Attribute attribute = attributesStorage.getAttributeByValueKeyOrNull(attributeName);
    return attributesStorage.getAttributeValue(attribute).getAvailableValues().stream().map(itemNP -> LookupValue.fromNamePair(itemNP)).collect(LookupValuesList.collect());
}


public boolean hasAttribute(String attributeName){
    return attributesStorage.hasAttribute(attributeName);
}


@Override
public void onAttributeValueCreated(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue){
    forwardEvent(storage, attributeValue);
}


@Override
public void processChanges(List<JSONDocumentChangedEvent> events){
    if (events == null || events.isEmpty()) {
        return;
    }
    events.forEach(this::processChange);
}


public void processChange(JSONDocumentChangedEvent event){
    if (JSONDocumentChangedEvent.JSONOperation.replace == event.getOperation()) {
        final String attributeName = event.getPath();
        if (isReadonly(attributeName)) {
            throw new DocumentFieldReadonlyException(attributeName, event.getValue());
        }
        final I_M_Attribute attribute = attributesStorage.getAttributeByValueKeyOrNull(attributeName);
        final Object value = convertFromJson(attribute, event.getValue());
        attributesStorage.setValue(attribute, value);
    } else {
        throw new IllegalArgumentException("Unknown operation: " + event);
    }
}


public Object getValue(String attributeName){
    return attributesStorage.getValue(attributeName);
}


@Override
public JSONViewRowAttributes toJson(JSONOptions jsonOpts){
    final JSONViewRowAttributes jsonDocument = new JSONViewRowAttributes(documentPath);
    final List<JSONDocumentField> jsonFields = attributesStorage.getAttributeValues().stream().map(attributeValue -> toJSONDocumentField(attributeValue, jsonOpts)).collect(Collectors.toList());
    jsonDocument.setFields(jsonFields);
    return jsonDocument;
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


public Optional<String> getSSCC18(){
    if (!attributesStorage.hasAttribute(HUAttributeConstants.ATTR_SSCC18_Value)) {
        return Optional.empty();
    }
    final String sscc18 = attributesStorage.getValueAsString(HUAttributeConstants.ATTR_SSCC18_Value);
    if (Check.isEmpty(sscc18, true)) {
        return Optional.empty();
    }
    return Optional.of(sscc18.trim());
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("documentPath", documentPath).add("attributesStorage", attributesStorage).toString();
}


}