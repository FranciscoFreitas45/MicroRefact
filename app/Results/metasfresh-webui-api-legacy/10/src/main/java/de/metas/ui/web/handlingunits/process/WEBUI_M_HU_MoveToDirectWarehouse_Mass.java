package de.metas.ui.web.handlingunits.process;
 import java.sql.Timestamp;
import java.util.Iterator;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.util.api.IRangeAwareParams;
import org.adempiere.warehouse.WarehouseId;
import org.compiere.model.IQuery;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Check;
import de.metas.util.Services;
public class WEBUI_M_HU_MoveToDirectWarehouse_Mass extends HUEditorProcessTemplate{

 private  IHandlingUnitsDAO handlingUnitsDAO;

@Autowired
 private  DocumentCollection documentsCollection;

 private  int p_M_Warehouse_ID;

 private  String p_huWhereClause;

 private  Timestamp p_MovementDate;

 private  String p_Description;


@Override
public void prepare(){
    final IRangeAwareParams parameterAsIParams = getParameterAsIParams();
    p_M_Warehouse_ID = parameterAsIParams.getParameterAsInt("M_Warehouse_ID", -1);
    p_huWhereClause = parameterAsIParams.getParameterAsString("WhereClause");
    p_MovementDate = parameterAsIParams.getParameterAsTimestamp("MovementDate");
    p_Description = parameterAsIParams.getParameterAsString("Description");
}


public Iterator<I_M_HU> retrieveHUs(){
    final IHUQueryBuilder huQueryBuilder = handlingUnitsDAO.createHUQueryBuilder().setContext(getCtx(), ITrx.TRXNAME_None);
    // Only top level HUs
    huQueryBuilder.setOnlyTopLevelHUs();
    // Only Active HUs
    huQueryBuilder.addHUStatusToInclude(X_M_HU.HUSTATUS_Active);
    // Only for preselected warehouse
    if (p_M_Warehouse_ID > 0) {
        huQueryBuilder.addOnlyInWarehouseId(WarehouseId.ofRepoId(p_M_Warehouse_ID));
    }
    // Only for given SQL where clause
    if (!Check.isEmpty(p_huWhereClause, true)) {
        huQueryBuilder.addFilter(TypedSqlQueryFilter.of(p_huWhereClause));
    }
    // Fetch the HUs iterator
    return huQueryBuilder.createQuery().setOption(IQuery.OPTION_GuaranteedIteratorRequired, // because we might change the hu's locator
    true).setOption(IQuery.OPTION_IteratorBufferSize, 1000).iterate(I_M_HU.class);
}


@Override
@RunOutOfTrx
public String doIt(){
    HUMoveToDirectWarehouseService.newInstance().setDocumentsCollection(documentsCollection).setHUView(getView()).setMovementDate(p_MovementDate).setDescription(p_Description).setFailOnFirstError(false).setLoggable(this).move(retrieveHUs());
    return MSG_OK;
}


}