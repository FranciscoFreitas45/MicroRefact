package de.metas.ui.web.picking.husToPick;
 import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import java.util.List;
import java.util.Objects;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.warehouse.api.IWarehouseDAO;
import org.compiere.util.DB;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.picking.IHUPickingSlotBL;
import de.metas.handlingunits.picking.IHUPickingSlotBL.PickingHUsQuery;
import de.metas.i18n.IMsgBL;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.experimental.UtilityClass;
@UtilityClass
public class HUsToPickViewFilters {

 private  String LocatorBarcode_FilterId;

 private  String PARAM_Barcode;

 private  String HU_IDS_FilterId;

 private  String PARAM_ConsiderAttributes;

 static  String PARAM_CurrentShipmentScheduleId;

 static  String PARAM_BestBeforePolicy;

 public  LocatorBarcodeFilterConverter instance;

 public  HUIdsFilterConverter instance;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    if (!HU_IDS_FilterId.equals(filter.getFilterId())) {
        throw new AdempiereException("Invalid filterId " + filter.getFilterId() + ". Expected: " + HU_IDS_FilterId);
    }
    final int shipmentScheduleId = context.getPropertyAsInt(PARAM_CurrentShipmentScheduleId, -1);
    if (shipmentScheduleId <= 0) {
        return "/* no shipment schedule */ 1=0";
    }
    final boolean considerAttributes = filter.getParameterValueAsBoolean(PARAM_ConsiderAttributes, false);
    final List<Integer> huIds = retrieveAvailableHuIdsForCurrentShipmentScheduleId(shipmentScheduleId, considerAttributes);
    if (huIds.isEmpty()) {
        return "/* no M_HU_IDs */ 1=0";
    }
    final String sql = sqlOpts.getTableNameOrAlias() + "." + I_M_HU.COLUMNNAME_M_HU_ID + " IN " + DB.buildSqlList(huIds);
    return sql;
}


public List<SqlDocumentFilterConverter> createFilterConverters(){
    return ImmutableList.<SqlDocumentFilterConverter>of(LocatorBarcodeFilterConverter.instance, HUIdsFilterConverter.instance);
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, HU_IDS_FilterId);
}


public List<Integer> retrieveAvailableHuIdsForCurrentShipmentScheduleId(int shipmentScheduleId,boolean considerAttributes){
    final IHUPickingSlotBL huPickingSlotBL = Services.get(IHUPickingSlotBL.class);
    final PickingHUsQuery query = PickingHUsQuery.builder().shipmentSchedule(loadOutOfTrx(shipmentScheduleId, I_M_ShipmentSchedule.class)).onlyTopLevelHUs(false).onlyIfAttributesMatchWithShipmentSchedules(considerAttributes).build();
    final List<Integer> availableHUIdsToPick = huPickingSlotBL.retrieveAvailableHUIdsToPick(query);
    return availableHUIdsToPick;
}


public DocumentFilter createHUIdsFilter(boolean considerAttributes){
    return DocumentFilter.singleParameterFilter(HU_IDS_FilterId, PARAM_ConsiderAttributes, Operator.EQUAL, considerAttributes);
}


public ImmutableList<DocumentFilterDescriptor> createFilterDescriptors(){
    return ImmutableList.of(createLocatorBarcodeFilterDescriptor(), createHUIdsFilterDescriptor());
}


public DocumentFilterDescriptor createHUIdsFilterDescriptor(){
    return DocumentFilterDescriptor.builder().setFilterId(HU_IDS_FilterId).setFrequentUsed(true).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_ConsiderAttributes).setDisplayName(Services.get(IMsgBL.class).translatable(PARAM_ConsiderAttributes)).setMandatory(false).setDefaultValue(true).setWidgetType(DocumentFieldWidgetType.YesNo)).build();
}


public DocumentFilterDescriptor createLocatorBarcodeFilterDescriptor(){
    return DocumentFilterDescriptor.builder().setFilterId(LocatorBarcode_FilterId).setFrequentUsed(true).setParametersLayoutType(PanelLayoutType.SingleOverlayField).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_Barcode).setDisplayName(Services.get(IMsgBL.class).getTranslatableMsgText("webui.view.husToPick.filters.locatorBarcodeFilter")).setMandatory(true).setWidgetType(DocumentFieldWidgetType.Text).barcodeScannerType(BarcodeScannerType.QRCode)).build();
}


}