package de.metas.ui.web.picking.husToPick.HUsToPickViewFilters;
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
public class HUIdsFilterConverter implements SqlDocumentFilterConverter{

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


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, HU_IDS_FilterId);
}


}