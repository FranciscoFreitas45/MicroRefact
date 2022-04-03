package de.metas.ui.web.pattribute;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeId;
import org.adempiere.mm.attributes.api.IAttributesBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.I_M_AttributeInstance;
import org.compiere.model.I_M_AttributeSet;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.X_M_Attribute;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util.ArrayKey;
import org.springframework.stereotype.Component;
import de.metas.cache.CCache;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
@Component
public class ASIDescriptorFactory {

 private  CCache<ArrayKey,ASIDescriptor> asiDescriptorById;

 private  CCache<AttributeId,ASILookupDescriptor> asiLookupDescriptorsByAttributeId;

 private  ASIDataBindingDescriptorBuilder _asiBindingsBuilder;

 private  DocumentEntityDataBindingDescriptor dataBinding;

 private  int attributeId;

 private  String attributeName;

 private  boolean mandatory;

 private  Function<I_M_AttributeInstance,Object> readMethod;

 private  BiConsumer<I_M_AttributeInstance,IDocumentFieldView> writeMethod;


public DocumentEntityDescriptor createDocumentEntityDescriptor(DocumentId asiDescriptorId,String name,String description,List<I_M_Attribute> attributes){
    if (attributes.isEmpty()) {
        throw new AdempiereException("No attributes are configured").setParameter("asiDescriptorId", asiDescriptorId);
    }
    final DocumentEntityDescriptor.Builder attributeSetDescriptor = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.ProductAttributes, asiDescriptorId).setCaption(name).setDescription(description).setDataBinding(getASIBindingsBuilder()).disableCallouts().setDetailId(null);
    for (final I_M_Attribute attribute : attributes) {
        final DocumentFieldDescriptor.Builder fieldDescriptor = createDocumentFieldDescriptor(attribute);
        attributeSetDescriptor.addField(fieldDescriptor);
    }
    return attributeSetDescriptor.build();
}


public Object readValue(I_M_AttributeInstance ai){
    return readMethod.apply(ai);
}


public void writeValueFromLookup(I_M_AttributeInstance ai,IDocumentFieldView field){
    final StringLookupValue lookupValue = field.getValueAs(StringLookupValue.class);
    final int attributeValueId = field.getDescriptor().getLookupDescriptor().get().cast(ASILookupDescriptor.class).getM_AttributeValue_ID(lookupValue);
    ai.setValue(lookupValue == null ? null : lookupValue.getIdAsString());
    ai.setM_AttributeValue_ID(attributeValueId);
}


public ASILayout createLayout(DocumentId asiDescriptorId,DocumentEntityDescriptor entityDescriptor){
    final ASILayout.Builder layout = ASILayout.builder().setCaption(entityDescriptor.getCaption()).setASIDescriptorId(asiDescriptorId);
    entityDescriptor.getFields().stream().map(fieldDescriptor -> createLayoutElement(fieldDescriptor)).forEach(layoutElement -> layout.addElement(layoutElement));
    return layout.build();
}


@Override
public String getColumnName(){
    return attributeName;
}


@Override
public DocumentsRepository getDocumentsRepository(){
    throw new IllegalStateException("No repository available for " + this);
}


public ASIDescriptor createASIDescriptor(WebuiASIEditingInfo info){
    final DocumentId asiDescriptorId = DocumentId.ofString(info.getContextWindowType() + "_" + info.getAttributeSetId().getRepoId());
    final DocumentEntityDescriptor entityDescriptor = createDocumentEntityDescriptor(asiDescriptorId, // name
    info.getAttributeSetName(), // description
    info.getAttributeSetDescription(), // attributes
    info.getAttributes());
    final ASILayout layout = createLayout(asiDescriptorId, entityDescriptor);
    return ASIDescriptor.builder().attributeSetId(info.getAttributeSetId()).entityDescriptor(entityDescriptor).layout(layout).contextDocumentPath(info.getContextDocumentPath()).build();
}


public LookupDescriptor getLookupDescriptor(I_M_Attribute attribute){
    final AttributeId attributeId = AttributeId.ofRepoId(attribute.getM_Attribute_ID());
    return asiLookupDescriptorsByAttributeId.getOrLoad(attributeId, () -> ASILookupDescriptor.of(attribute));
}


public DocumentLayoutElementDescriptor.Builder createLayoutElement(DocumentFieldDescriptor fieldDescriptor){
    return DocumentLayoutElementDescriptor.builder().setCaption(fieldDescriptor.getCaption()).setWidgetType(fieldDescriptor.getWidgetType()).addField(DocumentLayoutElementFieldDescriptor.builder(fieldDescriptor.getFieldName()).setLookupInfos(fieldDescriptor.getLookupDescriptor().orElse(null)).setPublicField(true).setSupportZoomInto(fieldDescriptor.isSupportZoomInto()));
}


@Override
public DocumentEntityDataBindingDescriptor getOrBuild(){
    return dataBinding;
}


public DocumentFieldDescriptor.Builder createDocumentFieldDescriptor(I_M_Attribute attribute){
    final int attributeId = attribute.getM_Attribute_ID();
    final String fieldName = attribute.getValue();
    final String attributeValueType = attribute.getAttributeValueType();
    final Class<?> valueClass;
    final DocumentFieldWidgetType widgetType;
    final Function<I_M_AttributeInstance, Object> readMethod;
    final BiConsumer<I_M_AttributeInstance, IDocumentFieldView> writeMethod;
    LookupDescriptor lookupDescriptor = null;
    if (X_M_Attribute.ATTRIBUTEVALUETYPE_Date.equals(attributeValueType)) {
        valueClass = java.util.Date.class;
        widgetType = DocumentFieldWidgetType.LocalDate;
        readMethod = I_M_AttributeInstance::getValueDate;
        writeMethod = (aiRecord, field) -> aiRecord.setValueDate(TimeUtil.asTimestamp(field.getValueAs(java.util.Date.class)));
    } else if (X_M_Attribute.ATTRIBUTEVALUETYPE_List.equals(attributeValueType)) {
        valueClass = StringLookupValue.class;
        widgetType = DocumentFieldWidgetType.List;
        readMethod = I_M_AttributeInstance::getValue;
        writeMethod = ASIAttributeFieldBinding::writeValueFromLookup;
        lookupDescriptor = getLookupDescriptor(attribute);
    } else if (X_M_Attribute.ATTRIBUTEVALUETYPE_Number.equals(attributeValueType)) {
        final int displayType = Services.get(IAttributesBL.class).getNumberDisplayType(attribute);
        if (displayType == DisplayType.Integer) {
            valueClass = Integer.class;
            widgetType = DocumentFieldWidgetType.Integer;
            readMethod = ai -> NumberUtils.asInteger(ai.getValueNumber(), null);
            writeMethod = (aiRecord, field) -> aiRecord.setValueNumber(field.getValueAs(BigDecimal.class));
        } else {
            valueClass = BigDecimal.class;
            widgetType = DocumentFieldWidgetType.Number;
            readMethod = I_M_AttributeInstance::getValueNumber;
            writeMethod = (aiRecord, field) -> aiRecord.setValueNumber(field.getValueAs(BigDecimal.class));
        }
    } else if (X_M_Attribute.ATTRIBUTEVALUETYPE_StringMax40.equals(attributeValueType)) {
        valueClass = String.class;
        widgetType = DocumentFieldWidgetType.Text;
        readMethod = I_M_AttributeInstance::getValue;
        writeMethod = (aiRecord, field) -> aiRecord.setValue(field.getValueAs(String.class));
    } else {
        throw new IllegalArgumentException("@NotSupported@ @AttributeValueType@=" + attributeValueType + ", @M_Attribute_ID@=" + attribute);
    }
    final ILogicExpression readonlyLogic = ConstantLogicExpression.of(attribute.isReadOnlyValues());
    final ILogicExpression displayLogic = ConstantLogicExpression.TRUE;
    final ILogicExpression mandatoryLogic = ConstantLogicExpression.of(attribute.isMandatory());
    final Optional<IExpression<?>> defaultValueExpr = Optional.empty();
    return DocumentFieldDescriptor.builder(fieldName).setCaption(attribute.getName()).setDescription(attribute.getDescription()).setValueClass(valueClass).setWidgetType(widgetType).setLookupDescriptorProvider(lookupDescriptor).setDefaultValueExpression(defaultValueExpr).setReadonlyLogic(readonlyLogic).setDisplayLogic(displayLogic).setMandatoryLogic(mandatoryLogic).addCharacteristic(Characteristic.PublicField).setDataBinding(new ASIAttributeFieldBinding(attributeId, fieldName, attribute.isMandatory(), readMethod, writeMethod));
}


public ASIDescriptor getASIDescriptor(WebuiASIEditingInfo request){
    final ArrayKey key = createASIDescriptorCachingKey(request);
    return asiDescriptorById.getOrLoad(key, () -> createASIDescriptor(request));
}


public ArrayKey createASIDescriptorCachingKey(WebuiASIEditingInfo request){
    return ArrayKey.builder().append(request.getContextWindowType()).append(request.getContextDocumentPath()).append(request.getAttributeSetId()).append(request.getAttributeIds()).build();
}


public void writeValue(I_M_AttributeInstance ai,IDocumentFieldView field){
    writeMethod.accept(ai, field);
}


public ASIDataBindingDescriptorBuilder getASIBindingsBuilder(){
    return _asiBindingsBuilder;
}


@Override
public boolean isMandatory(){
    return mandatory;
}


public void createAndSaveM_AttributeInstance(I_M_AttributeSetInstance asiRecord,IDocumentFieldView asiField){
    final I_M_AttributeInstance aiRecord = InterfaceWrapperHelper.newInstance(I_M_AttributeInstance.class, asiRecord);
    aiRecord.setM_AttributeSetInstance(asiRecord);
    aiRecord.setM_Attribute_ID(attributeId);
    writeValue(aiRecord, asiField);
    InterfaceWrapperHelper.save(aiRecord);
}


}