package DTO;
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

 private  ImmutableSet<String> mandatoryAttributeNames;

 private  DocumentPath documentPath;


public String getValueAsString(String attributeName){
    return attributesStorage.getValueAsString(attributeName);
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


public Optional<LocalDate> getBestBeforeDate(){
    if (!attributesStorage.hasAttribute(AttributeConstants.ATTR_BestBeforeDate)) {
        return Optional.empty();
    }
    final LocalDate bestBeforeDate = attributesStorage.getValueAsLocalDate(AttributeConstants.ATTR_BestBeforeDate);
    return Optional.ofNullable(bestBeforeDate);
}


@Override
public LookupValuesList getAttributeDropdown(String attributeName){
    final I_M_Attribute attribute = attributesStorage.getAttributeByValueKeyOrNull(attributeName);
    return attributesStorage.getAttributeValue(attribute).getAvailableValues().stream().map(itemNP -> LookupValue.fromNamePair(itemNP)).collect(LookupValuesList.collect());
}


public Object getValue(String attributeName){
    return attributesStorage.getValue(attributeName);
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


}