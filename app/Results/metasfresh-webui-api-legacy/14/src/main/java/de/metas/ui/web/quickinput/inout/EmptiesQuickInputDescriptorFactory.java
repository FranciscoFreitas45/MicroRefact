package de.metas.ui.web.quickinput.inout;
 import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.compiere.model.I_M_InOutLine;
import org.compiere.util.DisplayType;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.IMsgBL;
import de.metas.lang.SOTrx;
import de.metas.ui.web.quickinput.IQuickInputDescriptorFactory;
import de.metas.ui.web.quickinput.QuickInputDescriptor;
import de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class EmptiesQuickInputDescriptorFactory implements IQuickInputDescriptorFactory{

 public  int CustomerReturns_Window_ID;

 public  int VendorReturns_Window_ID;


public DocumentEntityDescriptor createEntityDescriptor(DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    final IMsgBL msgBL = Services.get(IMsgBL.class);
    final DocumentEntityDescriptor.Builder entityDescriptor = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.QuickInput, documentTypeId).setIsSOTrx(soTrx).disableDefaultTableCallouts().setDataBinding(DocumentEntityDataBindingDescriptorBuilder.NULL).setDetailId(detailId);
    entityDescriptor.addField(DocumentFieldDescriptor.builder(IEmptiesQuickInput.COLUMNNAME_M_HU_PackingMaterial_ID).setCaption(msgBL.translatable(IEmptiesQuickInput.COLUMNNAME_M_HU_PackingMaterial_ID)).setWidgetType(DocumentFieldWidgetType.Lookup).setLookupDescriptorProvider(SqlLookupDescriptor.builder().setCtxTableName(// ctxTableName
    null).setCtxColumnName(IEmptiesQuickInput.COLUMNNAME_M_HU_PackingMaterial_ID).setDisplayType(DisplayType.Search).buildProvider()).setValueClass(IntegerLookupValue.class).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCharacteristic(Characteristic.PublicField));
    entityDescriptor.addField(DocumentFieldDescriptor.builder(IEmptiesQuickInput.COLUMNNAME_Qty).setCaption(msgBL.translatable(IEmptiesQuickInput.COLUMNNAME_Qty)).setWidgetType(DocumentFieldWidgetType.Integer).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCharacteristic(Characteristic.PublicField));
    return entityDescriptor.build();
}


public QuickInputLayoutDescriptor createLayout(DocumentEntityDescriptor entityDescriptor){
    return QuickInputLayoutDescriptor.build(entityDescriptor, new String[][] { // 
    { IEmptiesQuickInput.COLUMNNAME_M_HU_PackingMaterial_ID }, { IEmptiesQuickInput.COLUMNNAME_Qty } });
}


@Override
public QuickInputDescriptor createQuickInputDescriptor(DocumentType documentType,DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    final DocumentEntityDescriptor entityDescriptor = createEntityDescriptor(documentTypeId, detailId, soTrx);
    final QuickInputLayoutDescriptor layout = createLayout(entityDescriptor);
    return QuickInputDescriptor.of(entityDescriptor, layout, EmptiesQuickInputProcessor.class);
}


@Override
public Set<MatchingKey> getMatchingKeys(){
    return ImmutableSet.<MatchingKey>builder().add(MatchingKey.includedDocument(DocumentType.Window, CustomerReturns_Window_ID, I_M_InOutLine.Table_Name)).add(MatchingKey.includedDocument(DocumentType.Window, VendorReturns_Window_ID, I_M_InOutLine.Table_Name)).build();
}


}