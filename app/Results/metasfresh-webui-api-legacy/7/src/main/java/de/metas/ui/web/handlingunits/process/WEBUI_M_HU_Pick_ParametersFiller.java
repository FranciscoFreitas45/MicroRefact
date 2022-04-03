package de.metas.ui.web.handlingunits.process;
 import org.adempiere.model.InterfaceWrapperHelper.load;
import java.util.List;
import java.util.Set;
import org.adempiere.ad.validationRule.IValidationRule;
import org.adempiere.ad.validationRule.IValidationRuleFactory;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.IShipmentSchedulePA;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.order.OrderLineId;
import de.metas.picking.api.IPickingSlotDAO;
import de.metas.picking.api.PickingSlotQuery;
import de.metas.picking.model.I_M_PickingSlot;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class WEBUI_M_HU_Pick_ParametersFiller {

 public  String PARAM_M_PickingSlot_ID;

 public  String PARAM_M_ShipmentSchedule_ID;

 private  OrderLineId salesOrderLineId;

 private  LookupDataSource shipmentScheduleDataSource;

 private  ShipmentScheduleId shipmentScheduleId;


public LookupValuesList getShipmentScheduleValues(LookupDataSourceContext context){
    final LookupValuesList result = shipmentScheduleDataSource.findEntities(context, context.getFilter(), context.getOffset(0), context.getLimit(10));
    return result;
}


public IValidationRule createShipmentSchedulesValidationRule(HuId huId){
    final StringBuilder sqlWhereClause = new StringBuilder();
    sqlWhereClause.append(I_M_ShipmentSchedule.COLUMNNAME_Processed).append("=").append(DB.TO_BOOLEAN(false));
    final ProductId productId = getSingleProductId(huId);
    sqlWhereClause.append(" AND ").append(I_M_ShipmentSchedule.COLUMNNAME_M_Product_ID).append("=").append(productId.getRepoId());
    final I_M_HU hu = load(huId, I_M_HU.class);
    if (hu.getC_BPartner_ID() > 0) {
        sqlWhereClause.append(" AND COALESCE(").append(I_M_ShipmentSchedule.COLUMNNAME_C_BPartner_Override_ID).append(", ").append(I_M_ShipmentSchedule.COLUMNNAME_C_BPartner_ID).append(") = ").append(hu.getC_BPartner_ID());
    }
    return Services.get(IValidationRuleFactory.class).createSQLValidationRule(sqlWhereClause.toString());
}


public LookupValuesList getPickingSlotValues(LookupDataSourceContext context){
    if (shipmentScheduleId == null) {
        return LookupValuesList.EMPTY;
    }
    final IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL = Services.get(IShipmentScheduleEffectiveBL.class);
    final I_M_ShipmentSchedule shipmentSchedule = Services.get(IShipmentSchedulePA.class).getById(shipmentScheduleId);
    final PickingSlotQuery pickingSlotQuery = PickingSlotQuery.builder().availableForBPartnerId(shipmentScheduleEffectiveBL.getBPartnerId(shipmentSchedule)).availableForBPartnerLocationId(shipmentScheduleEffectiveBL.getBPartnerLocationId(shipmentSchedule)).build();
    final List<I_M_PickingSlot> availablePickingSlots = Services.get(IPickingSlotDAO.class).retrievePickingSlots(pickingSlotQuery);
    return availablePickingSlots.stream().map(pickingSlot -> IntegerLookupValue.of(pickingSlot.getM_PickingSlot_ID(), createPickingSlotLabel(pickingSlot))).collect(LookupValuesList.collect());
}


public String createPickingSlotLabel(I_M_PickingSlot pickingslot){
    final StringBuilder result = new StringBuilder(pickingslot.getPickingSlot());
    final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(pickingslot.getC_BPartner_ID());
    if (bpartnerId != null) {
        final IBPartnerDAO bpartnersRepo = Services.get(IBPartnerDAO.class);
        final I_C_BPartner bpartner = bpartnersRepo.getById(bpartnerId);
        result.append("_").append(bpartner.getName()).append("_").append(bpartner.getValue());
    }
    return result.toString();
}


public ProductId getSingleProductId(HuId huId){
    final I_M_HU hu = Services.get(IHandlingUnitsDAO.class).getById(huId);
    final Set<ProductId> productIds = Services.get(IHUContextFactory.class).createMutableHUContext().getHUStorageFactory().getStorage(hu).getProductStorages().stream().map(IHUProductStorage::getProductId).distinct().collect(ImmutableSet.toImmutableSet());
    if (productIds.isEmpty()) {
        throw new AdempiereException("Empty HUs are not allowed");
    }
    if (productIds.size() > 1) {
        throw new AdempiereException("Multi product HUs are not allowed");
    }
    return productIds.iterator().next();
}


public LookupDataSource createShipmentScheduleDataSource(HuId huId){
    final LookupDescriptor lookupDescriptor = SqlLookupDescriptor.builder().setCtxTableName(null).setCtxColumnName(I_M_ShipmentSchedule.COLUMNNAME_M_ShipmentSchedule_ID).setDisplayType(DisplayType.Search).addValidationRule(createShipmentSchedulesValidationRule(huId)).buildForDefaultScope();
    return LookupDataSourceFactory.instance.createLookupDataSource(lookupDescriptor);
}


public Object getDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_M_ShipmentSchedule_ID.equals(parameter.getColumnName())) {
        final ShipmentScheduleId defaultShipmentScheduleId = getDefaultShipmentScheduleId();
        if (defaultShipmentScheduleId != null) {
            return defaultShipmentScheduleId.getRepoId();
        }
    }
    // Fallback
    return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
}


public ShipmentScheduleId getDefaultShipmentScheduleId(){
    if (salesOrderLineId == null) {
        return null;
    }
    return Services.get(IShipmentSchedulePA.class).getShipmentScheduleIdByOrderLineId(salesOrderLineId);
}


}