package de.metas.ui.web.material.cockpit.rowfactory.MaterialCockpitRowFactory;
 import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.adempiere.ad.dao.IQueryBL;
import org.compiere.model.I_S_Resource;
import org.compiere.model.X_S_Resource;
import org.springframework.stereotype.Service;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import de.metas.dimension.DimensionSpec;
import de.metas.dimension.DimensionSpecGroup;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.product.ProductId;
import de.metas.ui.web.material.cockpit.MaterialCockpitRow;
import de.metas.ui.web.material.cockpit.MaterialCockpitUtil;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
@Value
@lombok.Builder
public class CreateRowsRequest {

@NonNull
 private LocalDate date;

@NonNull
 private ImmutableSet<ProductId> productIdsToListEvenIfEmpty;

@NonNull
 private List<I_MD_Cockpit> cockpitRecords;

@NonNull
 private List<I_MD_Stock> stockRecords;

 private boolean includePerPlantDetailRows;


}