package de.metas.ui.web.pickingslotsClearing;
 import org.compiere.util.DisplayType;
import de.metas.bpartner.BPartnerId;
import de.metas.document.archive.model.I_C_BPartner;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFilters;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Services;
import lombok.experimental.UtilityClass;
@UtilityClass
public class PickingSlotsClearingViewFilters {

 private  String FILTER_ID_BPartner;

 private  String PARAM_C_BPartner_ID;


public DocumentFilterDescriptor createBPartnerFilter(){
    final LookupDescriptor bpartnerLookupDescriptor = SqlLookupDescriptor.builder().setCtxColumnName(I_C_BPartner.COLUMNNAME_C_BPartner_ID).setDisplayType(DisplayType.Search).buildForDefaultScope();
    return DocumentFilterDescriptor.builder().setFilterId(FILTER_ID_BPartner).setFrequentUsed(true).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_C_BPartner_ID).setDisplayName(Services.get(IMsgBL.class).translatable(PARAM_C_BPartner_ID)).setMandatory(true).setWidgetType(DocumentFieldWidgetType.Lookup).setLookupDescriptor(bpartnerLookupDescriptor)).build();
}


public String getPickingSlotBarcode(DocumentFilterList filters){
    return PickingSlotViewFilters.getPickingSlotBarcode(filters);
}


public DocumentFilterDescriptorsProvider createFilterDescriptorsProvider(){
    return ImmutableDocumentFilterDescriptorsProvider.of(PickingSlotViewFilters.createPickingSlotBarcodeFilters(), createBPartnerFilter());
}


public BPartnerId getBPartnerId(DocumentFilterList filters){
    return BPartnerId.ofRepoIdOrNull(filters.getParamValueAsInt(FILTER_ID_BPartner, PARAM_C_BPartner_ID, -1));
}


}