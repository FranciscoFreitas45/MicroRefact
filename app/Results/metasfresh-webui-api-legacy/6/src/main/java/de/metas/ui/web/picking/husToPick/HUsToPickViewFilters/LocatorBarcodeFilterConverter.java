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
public class LocatorBarcodeFilterConverter implements SqlDocumentFilterConverter{

 public  LocatorBarcodeFilterConverter instance;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    if (!LocatorBarcode_FilterId.equals(filter.getFilterId())) {
        throw new AdempiereException("Invalid filterId " + filter.getFilterId() + ". Expected: " + LocatorBarcode_FilterId);
    }
    final String barcode = filter.getParameterValueAsString(PARAM_Barcode);
    final int locatorId = Services.get(IWarehouseDAO.class).retrieveLocatorIdByBarcode(barcode);
    final String sql = sqlOpts.getTableNameOrAlias() + "." + I_M_HU.COLUMNNAME_M_Locator_ID + "=" + sqlParamsOut.placeholder(locatorId);
    return sql;
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, LocatorBarcode_FilterId);
}


}