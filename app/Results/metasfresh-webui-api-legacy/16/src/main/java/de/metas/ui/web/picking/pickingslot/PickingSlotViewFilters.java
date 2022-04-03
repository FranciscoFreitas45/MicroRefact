package de.metas.ui.web.picking.pickingslot;
 import de.metas.i18n.IMsgBL;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Services;
import lombok.experimental.UtilityClass;
@UtilityClass
public class PickingSlotViewFilters {

 private  String PickingSlotBarcodeFilter_FilterId;

 private  String PARAM_Barcode;


public String getPickingSlotBarcode(DocumentFilterList filters){
    return filters.getParamValueAsString(PickingSlotBarcodeFilter_FilterId, PARAM_Barcode);
}


public DocumentFilterDescriptorsProvider createFilterDescriptorsProvider(){
    return ImmutableDocumentFilterDescriptorsProvider.of(createPickingSlotBarcodeFilters());
}


public DocumentFilterDescriptor createPickingSlotBarcodeFilters(){
    return DocumentFilterDescriptor.builder().setFilterId(PickingSlotBarcodeFilter_FilterId).setFrequentUsed(true).setParametersLayoutType(PanelLayoutType.SingleOverlayField).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_Barcode).setDisplayName(Services.get(IMsgBL.class).getTranslatableMsgText("webui.view.pickingSlot.filters.pickingSlotBarcodeFilter")).setMandatory(true).setWidgetType(DocumentFieldWidgetType.Text).barcodeScannerType(BarcodeScannerType.QRCode)).build();
}


}