package de.metas.ui.web.quickinput.forecastline;
 import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.compiere.model.I_M_Forecast;
import org.compiere.util.DisplayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.model.I_M_ForecastLine;
import de.metas.handlingunits.order.api.IHUOrderBL;
import de.metas.i18n.IMsgBL;
import de.metas.lang.SOTrx;
import de.metas.product.ProductId;
import de.metas.ui.web.material.adapter.AvailableToPromiseAdapter;
import de.metas.ui.web.quickinput.IQuickInputDescriptorFactory;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.quickinput.QuickInputConstants;
import de.metas.ui.web.quickinput.QuickInputDescriptor;
import de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Builder;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor.ProductAndAttributes;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class ForecastLineQuickInputDescriptorFactory implements IQuickInputDescriptorFactory{

@Autowired
 private  AvailableToPromiseAdapter availableToPromiseAdapter;


public DocumentEntityDescriptor createEntityDescriptor(DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    return createDescriptorBuilder(documentTypeId, detailId).addField(createProductFieldBuilder()).addFieldIf(QuickInputConstants.isEnablePackingInstructionsField(), () -> createPackingInstructionFieldBuilder()).addField(createQuantityFieldBuilder()).setIsSOTrx(soTrx).build();
}


public DocumentEntityDescriptor.Builder createDescriptorBuilder(DocumentId documentTypeId,DetailId detailId){
    final DocumentEntityDescriptor.Builder entityDescriptor = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.QuickInput, documentTypeId).disableDefaultTableCallouts().setDetailId(detailId).setTableName(I_M_ForecastLine.Table_Name);
    return entityDescriptor;
}


public Builder createPackingInstructionFieldBuilder(){
    final Builder packingInstructionFieldBuilder = DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID).setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID)).setWidgetType(DocumentFieldWidgetType.Lookup).setLookupDescriptorProvider(SqlLookupDescriptor.builder().setCtxTableName(// ctxTableName
    null).setCtxColumnName(IForecastLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID).setDisplayType(DisplayType.TableDir).setAD_Val_Rule_ID(// FIXME: hardcoded "M_HU_PI_Item_Product_For_Org_and_Product_and_DatePromised"
    540365).buildProvider()).setValueClass(IntegerLookupValue.class).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.FALSE).setDisplayLogic(ConstantLogicExpression.TRUE).addCharacteristic(Characteristic.PublicField);
    return packingInstructionFieldBuilder;
}


public Builder createQuantityFieldBuilder(){
    final Builder qtyFieldBuilder = DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_Qty).setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_Qty)).setWidgetType(DocumentFieldWidgetType.Quantity).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCharacteristic(Characteristic.PublicField);
    return qtyFieldBuilder;
}


public Builder createProductFieldBuilder(){
    final Builder productFieldBuilder = DocumentFieldDescriptor.builder(IForecastLineQuickInput.COLUMNNAME_M_Product_ID).setCaption(Services.get(IMsgBL.class).translatable(IForecastLineQuickInput.COLUMNNAME_M_Product_ID)).setWidgetType(DocumentFieldWidgetType.Lookup).setLookupDescriptorProvider(ProductLookupDescriptor.builderWithStockInfo().bpartnerParamName(I_M_Forecast.COLUMNNAME_C_BPartner_ID).pricingDateParamName(I_M_Forecast.COLUMNNAME_DatePromised).availableStockDateParamName(I_M_Forecast.COLUMNNAME_DatePromised).availableToPromiseAdapter(availableToPromiseAdapter).build()).setReadonlyLogic(ConstantLogicExpression.FALSE).setAlwaysUpdateable(true).setMandatoryLogic(ConstantLogicExpression.TRUE).setDisplayLogic(ConstantLogicExpression.TRUE).addCallout(ForecastLineQuickInputDescriptorFactory::onProductChangedCallout).addCharacteristic(Characteristic.PublicField);
    return productFieldBuilder;
}


public QuickInputLayoutDescriptor createLayout(DocumentEntityDescriptor entityDescriptor){
    return QuickInputLayoutDescriptor.build(entityDescriptor, new String[][] { // 
    { "M_Product_ID", "M_HU_PI_Item_Product_ID" }, { "Qty" } });
}


@Override
public QuickInputDescriptor createQuickInputDescriptor(DocumentType documentType,DocumentId documentTypeId,DetailId detailId,Optional<SOTrx> soTrx){
    final DocumentEntityDescriptor entityDescriptor = createEntityDescriptor(documentTypeId, detailId, soTrx);
    final QuickInputLayoutDescriptor layout = createLayout(entityDescriptor);
    return QuickInputDescriptor.of(entityDescriptor, layout, ForecastLineQuickInputProcessor.class);
}


public void onProductChangedCallout(ICalloutField calloutField){
    final QuickInput quickInput = QuickInput.getQuickInputOrNull(calloutField);
    if (quickInput == null) {
        return;
    }
    final IForecastLineQuickInput quickInputModel = quickInput.getQuickInputDocumentAs(IForecastLineQuickInput.class);
    final LookupValue productLookupValue = quickInputModel.getM_Product_ID();
    if (productLookupValue == null) {
        return;
    }
    final ProductAndAttributes productAndAttributes = ProductLookupDescriptor.toProductAndAttributes(productLookupValue);
    final ProductId quickInputProduct = productAndAttributes.getProductId();
    final I_M_Forecast forecast = quickInput.getRootDocumentAs(I_M_Forecast.class);
    Services.get(IHUOrderBL.class).findM_HU_PI_Item_ProductForForecast(forecast, quickInputProduct, quickInputModel::setM_HU_PI_Item_Product);
}


@Override
public Set<MatchingKey> getMatchingKeys(){
    return ImmutableSet.of(MatchingKey.ofTableName(I_M_ForecastLine.Table_Name));
}


}