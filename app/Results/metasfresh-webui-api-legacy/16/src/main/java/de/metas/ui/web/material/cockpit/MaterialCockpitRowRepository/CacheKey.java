package de.metas.ui.web.material.cockpit.MaterialCockpitRowRepository;
 import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_M_Product;
import org.compiere.util.Env;
import org.springframework.stereotype.Repository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.cache.CCache;
import de.metas.cache.CCache.CacheMapType;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.material.cockpit.filters.MaterialCockpitFilters;
import de.metas.ui.web.material.cockpit.filters.ProductFilterUtil;
import de.metas.ui.web.material.cockpit.filters.ProductFilterVO;
import de.metas.ui.web.material.cockpit.filters.StockFilters;
import de.metas.ui.web.material.cockpit.rowfactory.MaterialCockpitRowFactory;
import de.metas.ui.web.material.cockpit.rowfactory.MaterialCockpitRowFactory.CreateRowsRequest;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
@Value
public class CacheKey {

@NonNull
 private OrgId orgId;

@NonNull
 private ProductFilterVO productFilterVO;

 private int limit;


}