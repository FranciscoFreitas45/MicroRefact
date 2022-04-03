package de.metas.ui.web.handlingunits;
 import java.util.List;
import org.adempiere.mm.attributes.spi.IAttributeValuesProvider;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_M_Attribute;
import org.compiere.util.Evaluatee;
import org.compiere.util.NamePair;
import de.metas.device.adempiere.AttributesDevicesHub.AttributeDeviceAccessor;
import de.metas.device.adempiere.IDevicesHubFactory;
import de.metas.handlingunits.attribute.IAttributeValue;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.devices.DeviceWebSocketProducerFactory;
import de.metas.ui.web.devices.JSONDeviceDescriptor;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
public class HUEditorRowAttributesHelper {


public String extractAttributeName(org.compiere.model.I_M_Attribute attribute){
    return attribute.getValue();
}


public DocumentLayoutElementDescriptor createLayoutElement(IAttributeValue attributeValue,int warehouseId){
    final I_M_Attribute attribute = attributeValue.getM_Attribute();
    final IModelTranslationMap attributeTrlMap = InterfaceWrapperHelper.getModelTranslationMap(attribute);
    final ITranslatableString caption = attributeTrlMap.getColumnTrl(I_M_Attribute.COLUMNNAME_Name, attribute.getName());
    final ITranslatableString description = attributeTrlMap.getColumnTrl(I_M_Attribute.COLUMNNAME_Description, attribute.getDescription());
    final String attributeName = HUEditorRowAttributesHelper.extractAttributeName(attributeValue);
    final DocumentFieldWidgetType widgetType = HUEditorRowAttributesHelper.extractWidgetType(attributeValue);
    return DocumentLayoutElementDescriptor.builder().setCaption(caption).setDescription(description).setWidgetType(widgetType).addField(DocumentLayoutElementFieldDescriptor.builder(attributeName).setPublicField(true).addDevices(createDevices(attribute.getValue(), warehouseId))).build();
}


public Object extractJSONValue(IAttributeStorage attributesStorage,IAttributeValue attributeValue,JSONOptions jsonOpts){
    final Object value = extractValueAndResolve(attributesStorage, attributeValue);
    final Object jsonValue = Values.valueToJsonObject(value, jsonOpts);
    return jsonValue;
}


public ViewRowAttributesLayout createLayout(IAttributeStorage attributeStorage){
    final int warehouseId = attributeStorage.getM_Warehouse_ID();
    final List<DocumentLayoutElementDescriptor> elements = attributeStorage.getAttributeValues().stream().map(av -> createLayoutElement(av, warehouseId)).collect(GuavaCollectors.toImmutableList());
    return ViewRowAttributesLayout.of(elements);
}


public Object extractValueAndResolve(IAttributeStorage attributesStorage,IAttributeValue attributeValue){
    final Object value = attributeValue.getValue();
    if (!attributeValue.isList()) {
        return value;
    }
    final IAttributeValuesProvider valuesProvider = attributeValue.getAttributeValuesProvider();
    final Evaluatee evalCtx = valuesProvider.prepareContext(attributesStorage);
    final NamePair valueNP = valuesProvider.getAttributeValueOrNull(evalCtx, value);
    return LookupValue.fromNamePair(valueNP);
}


public JSONDeviceDescriptor createDevice(AttributeDeviceAccessor attributeDeviceAccessor){
    final String deviceId = attributeDeviceAccessor.getPublicId();
    return JSONDeviceDescriptor.builder().setDeviceId(deviceId).setCaption(attributeDeviceAccessor.getDisplayName()).setWebsocketEndpoint(DeviceWebSocketProducerFactory.buildDeviceTopicName(deviceId)).build();
}


public List<JSONDeviceDescriptor> createDevices(String attributeCode,int warehouseId){
    return Services.get(IDevicesHubFactory.class).getDefaultAttributesDevicesHub().getAttributeDeviceAccessors(attributeCode).stream(warehouseId).map(attributeDeviceAccessor -> createDevice(attributeDeviceAccessor)).collect(GuavaCollectors.toImmutableList());
}


public DocumentFieldWidgetType extractWidgetType(IAttributeValue attributeValue){
    if (attributeValue.isList()) {
        final I_M_Attribute attribute = attributeValue.getM_Attribute();
        if (attribute.isHighVolume()) {
            return DocumentFieldWidgetType.Lookup;
        } else {
            return DocumentFieldWidgetType.List;
        }
    } else if (attributeValue.isStringValue()) {
        return DocumentFieldWidgetType.Text;
    } else if (attributeValue.isNumericValue()) {
        return DocumentFieldWidgetType.Number;
    } else if (attributeValue.isDateValue()) {
        return DocumentFieldWidgetType.LocalDate;
    } else {
        throw new IllegalArgumentException("Cannot extract widgetType from " + attributeValue);
    }
}


}