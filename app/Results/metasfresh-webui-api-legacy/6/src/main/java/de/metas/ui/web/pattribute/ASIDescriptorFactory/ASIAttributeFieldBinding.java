package de.metas.ui.web.pattribute.ASIDescriptorFactory;
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
public class ASIAttributeFieldBinding implements DocumentFieldDataBindingDescriptor{

 private  int attributeId;

 private  String attributeName;

 private  boolean mandatory;

 private  Function<I_M_AttributeInstance,Object> readMethod;

 private  BiConsumer<I_M_AttributeInstance,IDocumentFieldView> writeMethod;


public Object readValue(I_M_AttributeInstance ai){
    return readMethod.apply(ai);
}


public void writeValueFromLookup(I_M_AttributeInstance ai,IDocumentFieldView field){
    final StringLookupValue lookupValue = field.getValueAs(StringLookupValue.class);
    final int attributeValueId = field.getDescriptor().getLookupDescriptor().get().cast(ASILookupDescriptor.class).getM_AttributeValue_ID(lookupValue);
    ai.setValue(lookupValue == null ? null : lookupValue.getIdAsString());
    ai.setM_AttributeValue_ID(attributeValueId);
}


@Override
public String getColumnName(){
    return attributeName;
}


public void writeValue(I_M_AttributeInstance ai,IDocumentFieldView field){
    writeMethod.accept(ai, field);
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