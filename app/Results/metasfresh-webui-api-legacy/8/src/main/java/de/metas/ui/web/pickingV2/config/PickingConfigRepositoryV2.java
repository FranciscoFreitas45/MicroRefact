package de.metas.ui.web.pickingV2.config;
 import org.adempiere.ad.dao.IQueryBL;
import org.springframework.stereotype.Repository;
import de.metas.cache.CCache;
import de.metas.picking.model.I_M_Picking_Config_V2;
import de.metas.util.Services;
@Repository
public class PickingConfigRepositoryV2 {

 private  CCache<Integer,PickingConfigV2> pickingConfigCache;


public PickingConfigV2 getPickingConfig(){
    return pickingConfigCache.getOrLoad(0, this::createPickingConfig);
}


public PickingConfigV2 createPickingConfig(){
    final I_M_Picking_Config_V2 record = Services.get(IQueryBL.class).createQueryBuilder(I_M_Picking_Config_V2.class).addOnlyActiveRecordsFilter().create().firstOnlyNotNull(I_M_Picking_Config_V2.class);
    return PickingConfigV2.builder().pickingReviewRequired(record.isPickingReviewRequired()).considerAttributes(record.isConsiderAttributes()).build();
}


}