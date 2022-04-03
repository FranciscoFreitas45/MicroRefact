package de.metas.ui.web.handlingunits.HUEditorRowAttributes;
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
@EqualsAndHashCode
public class AttributeStorage2ExecutionEventsForwarder implements IAttributeStorageListener{

 private  DocumentPath documentPath;


public void forwardEvent(IAttributeStorage storage,IAttributeValue attributeValue){
    final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollector();
    final String attributeName = HUEditorRowAttributesHelper.extractAttributeName(attributeValue);
    final Object jsonValue = HUEditorRowAttributesHelper.extractJSONValue(storage, attributeValue, JSONOptions.newInstance());
    final DocumentFieldWidgetType widgetType = HUEditorRowAttributesHelper.extractWidgetType(attributeValue);
    changesCollector.collectEvent(MutableDocumentFieldChangedEvent.of(documentPath, attributeName, widgetType).setValue(jsonValue));
}


public void bind(IAttributeStorage storage,DocumentPath documentPath){
    final AttributeStorage2ExecutionEventsForwarder forwarder = new AttributeStorage2ExecutionEventsForwarder(documentPath);
    storage.addListener(forwarder);
}


@Override
public void onAttributeValueCreated(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue){
    forwardEvent(storage, attributeValue);
}


@Override
public void onAttributeValueChanged(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue,Object valueOld){
    forwardEvent(storage, attributeValue);
}


@Override
public void onAttributeValueDeleted(IAttributeValueContext attributeValueContext,IAttributeStorage storage,IAttributeValue attributeValue){
    throw new UnsupportedOperationException();
}


}