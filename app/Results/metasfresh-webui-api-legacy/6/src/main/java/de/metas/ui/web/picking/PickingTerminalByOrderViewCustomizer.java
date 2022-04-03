package de.metas.ui.web.picking;
 import org.adempiere.ad.service.IADReferenceDAO;
import org.springframework.stereotype.Component;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.picking.model.X_M_Picking_Config;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewRow;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewGroupingBinding;
import de.metas.ui.web.view.descriptor.SqlViewRowIdsConverters;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.util.Services;
@Component
public class PickingTerminalByOrderViewCustomizer implements SqlViewCustomizer{

 private  ViewProfileId PROFILE_ID;

 private  ViewProfile PROFILE;

 private  String FIELDNAME_OrderOrBPLocation;


@Override
public void customizeViewRow(ViewRow.Builder rowBuilder){
    final LookupValue orderOrBPLocationLV = createOrderOrBPLocation(rowBuilder);
    rowBuilder.putFieldValue(FIELDNAME_OrderOrBPLocation, orderOrBPLocationLV);
}


public SqlViewGroupingBinding createSqlViewGroupingBinding(){
    return SqlViewGroupingBinding.builder().groupBy(I_M_Packageable_V.COLUMNNAME_C_OrderSO_ID).groupBy(I_M_Packageable_V.COLUMNNAME_C_BPartner_Customer_ID).groupBy(I_M_Packageable_V.COLUMNNAME_M_Warehouse_ID).columnSql(I_M_Packageable_V.COLUMNNAME_DeliveryDate, SqlSelectValue.builder().virtualColumnSql("MIN(DeliveryDate)").columnNameAlias(I_M_Packageable_V.COLUMNNAME_DeliveryDate).build()).columnSql(I_M_Packageable_V.COLUMNNAME_PreparationDate, SqlSelectValue.builder().virtualColumnSql("IF_MIN(DeliveryDate, PreparationDate)").columnNameAlias(I_M_Packageable_V.COLUMNNAME_PreparationDate).build()).rowIdsConverter(SqlViewRowIdsConverters.TO_INT_EXCLUDING_STRINGS).build();
}


public LookupValue createOrderOrBPLocation(ViewRow.Builder rowBuilder){
    // Grouping row
    if (rowBuilder.isRootRow()) {
        final LookupValue orderLV = rowBuilder.getFieldValueAsLookupValue(I_M_Packageable_V.COLUMNNAME_C_OrderSO_ID);
        final LookupValue bpartnerLV = rowBuilder.getFieldValueAsLookupValue(I_M_Packageable_V.COLUMNNAME_C_BPartner_Customer_ID);
        return LookupValue.concat(orderLV, bpartnerLV);
    } else // Detail/included row
    {
        final LookupValue bpLocationLV = rowBuilder.getFieldValueAsLookupValue(I_M_Packageable_V.COLUMNNAME_C_BPartner_Location_ID);
        return bpLocationLV;
    }
}


@Override
public void customizeViewLayout(ViewLayout.ChangeBuilder viewLayoutBuilder){
    viewLayoutBuilder.element(DocumentLayoutElementDescriptor.builder().setWidgetType(DocumentFieldWidgetType.Lookup).addField(DocumentLayoutElementFieldDescriptor.builder(FIELDNAME_OrderOrBPLocation).setPublicField(true)).build());
    viewLayoutBuilder.elementsOrder(FIELDNAME_OrderOrBPLocation, I_M_Packageable_V.COLUMNNAME_M_Product_ID, I_M_Packageable_V.COLUMNNAME_QtyOrdered, I_M_Packageable_V.COLUMNNAME_QtyPickedOrDelivered, I_M_Packageable_V.COLUMNNAME_M_Warehouse_ID, I_M_Packageable_V.COLUMNNAME_PreparationDate);
}


@Override
public ViewProfile getProfile(){
    return PROFILE;
}


@Override
public WindowId getWindowId(){
    return PickingConstants.WINDOWID_PackageableView;
}


@Override
public void customizeSqlViewBinding(SqlViewBinding.Builder sqlViewBindingBuilder){
    sqlViewBindingBuilder.groupingBinding(createSqlViewGroupingBinding()).clearDefaultOrderBys().defaultOrderBy(DocumentQueryOrderBy.byFieldName(FIELDNAME_OrderOrBPLocation, true)).orderByAliasFieldNames(FIELDNAME_OrderOrBPLocation, I_M_Packageable_V.COLUMNNAME_C_OrderSO_ID, I_M_Packageable_V.COLUMNNAME_C_BPartner_Customer_ID, I_M_Packageable_V.COLUMNNAME_C_BPartner_Location_ID);
}


}