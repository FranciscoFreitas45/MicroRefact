package de.metas.vertical.cables.webui.quickinput;
 import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.compiere.model.I_C_OrderLine;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.model.I_C_Order;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.lang.SOTrx;
import de.metas.ui.web.quickinput.IQuickInputDescriptorFactory;
import de.metas.ui.web.quickinput.QuickInputDescriptor;
import de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.util.Services;
import de.metas.vertical.cables.CablesConstants;
import lombok.NonNull;
@Component
@Profile(CablesConstants.PROFILE)
public class CableSalesOrderLineQuickInputDescriptorFactory implements IQuickInputDescriptorFactory{

 private  AdWindowId WINDOW_ID_SalesOrder_DEFAULT;

 private  AdWindowId WINDOW_ID_PurchaseOrder_DEFAULT;

 private  ProductLookupDescriptor productLookupDescriptor;


public DocumentEntityDescriptor.Builder createDescriptorBuilder(DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    return DocumentEntityDescriptor.builder().setDocumentType(DocumentType.QuickInput, documentTypeId).setIsSOTrx(soTrx).disableDefaultTableCallouts().setDetailId(detailId).setTableName(// TODO: figure out if it's needed
    I_C_OrderLine.Table_Name);
}


public DocumentFieldDescriptor.Builder createQuantityFieldBuilder(String fieldName){
    return DocumentFieldDescriptor.builder(fieldName).setCaption(Services.get(IMsgBL.class).translatable(fieldName)).setWidgetType(DocumentFieldWidgetType.Quantity).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCharacteristic(Characteristic.PublicField);
}


public DocumentFieldDescriptor.Builder createProductFieldBuilder(String fieldName){
    final IMsgBL msgBL = Services.get(IMsgBL.class);
    final ITranslatableString caption = msgBL.translatable(fieldName);
    return DocumentFieldDescriptor.builder(fieldName).setLookupDescriptorProvider(productLookupDescriptor).setCaption(caption).setWidgetType(DocumentFieldWidgetType.Lookup).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCallout(calloutField -> onProductChangedCallout(calloutField)).addCharacteristic(Characteristic.PublicField);
}


@Override
public QuickInputDescriptor createQuickInputDescriptor(DocumentType documentType,DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    final DocumentEntityDescriptor entityDescriptor = createDescriptorBuilder(documentTypeId, detailId, soTrx).addField(createProductFieldBuilder(ICablesOrderLineQuickInput.COLUMNNAME_Plug1_Product_ID).setMandatoryLogic(true)).addField(createProductFieldBuilder(ICablesOrderLineQuickInput.COLUMNNAME_Cable_Product_ID).setMandatoryLogic(false)).addField(createProductFieldBuilder(ICablesOrderLineQuickInput.COLUMNNAME_Plug2_Product_ID).setMandatoryLogic(false)).addField(createQuantityFieldBuilder(ICablesOrderLineQuickInput.COLUMNNAME_CableLength).setMandatoryLogic(false)).addField(createQuantityFieldBuilder(ICablesOrderLineQuickInput.COLUMNNAME_Qty).setMandatoryLogic(true)).build();
    final QuickInputLayoutDescriptor.Builder quickInputBuilder = QuickInputLayoutDescriptor.builder();
    DocumentLayoutElementDescriptor.builderOrEmpty(entityDescriptor, ICablesOrderLineQuickInput.COLUMNNAME_Plug1_Product_ID, ICablesOrderLineQuickInput.COLUMNNAME_Cable_Product_ID, ICablesOrderLineQuickInput.COLUMNNAME_Plug2_Product_ID).map(b -> b.setWidgetSize(WidgetSize.Large)).ifPresent(quickInputBuilder::element);
    DocumentLayoutElementDescriptor.builderOrEmpty(entityDescriptor, ICablesOrderLineQuickInput.COLUMNNAME_CableLength).map(b -> b.setWidgetSize(WidgetSize.Small)).ifPresent(quickInputBuilder::element);
    DocumentLayoutElementDescriptor.builderOrEmpty(entityDescriptor, ICablesOrderLineQuickInput.COLUMNNAME_Qty).map(b -> b.setWidgetSize(WidgetSize.Small)).ifPresent(quickInputBuilder::element);
    final QuickInputLayoutDescriptor layout = quickInputBuilder.build();
    return QuickInputDescriptor.of(entityDescriptor, layout, CableSalesOrderLineQuickInputProcessor.class);
}


public void onProductChangedCallout(ICalloutField calloutField){
// TODO
}


@Override
public Set<MatchingKey> getMatchingKeys(){
    final IADWindowDAO windowDAO = Services.get(IADWindowDAO.class);
    final AdWindowId salesOrderWindowId = windowDAO.getAdWindowId(I_C_OrderLine.Table_Name, SOTrx.SALES, WINDOW_ID_SalesOrder_DEFAULT);
    final AdWindowId purchaseOrderWindowId = windowDAO.getAdWindowId(I_C_OrderLine.Table_Name, SOTrx.PURCHASE, WINDOW_ID_PurchaseOrder_DEFAULT);
    return ImmutableSet.of(MatchingKey.includedDocument(DocumentType.Window, salesOrderWindowId.getRepoId(), I_C_OrderLine.Table_Name), MatchingKey.includedDocument(DocumentType.Window, purchaseOrderWindowId.getRepoId(), I_C_OrderLine.Table_Name));
}


}