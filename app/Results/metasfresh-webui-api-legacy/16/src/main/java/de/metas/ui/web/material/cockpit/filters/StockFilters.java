package de.metas.ui.web.material.cockpit.filters;
 import java.math.BigDecimal.ZERO;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.impl.CompareQueryFilter.Operator;
import org.compiere.model.IQuery;
import org.compiere.model.I_M_Product;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.util.Services;
import lombok.NonNull;
public class StockFilters {


public boolean augmentQueryBuilder(IQueryBuilder<I_MD_Stock> queryBuilder,ProductFilterVO productFilterVO){
    final IQuery<I_M_Product> productQuery = ProductFilterUtil.createProductQueryOrNull(productFilterVO);
    if (productQuery == null) {
        return false;
    }
    queryBuilder.addInSubQueryFilter(I_MD_Stock.COLUMN_M_Product_ID, I_M_Product.COLUMN_M_Product_ID, productQuery);
    return true;
}


public IQuery<I_MD_Stock> createStockQueryFor(DocumentFilterList filters){
    final IQueryBL queryBL = Services.get(IQueryBL.class);
    final IQueryBuilder<I_MD_Stock> queryBuilder = queryBL.createQueryBuilder(I_MD_Stock.class).addOnlyActiveRecordsFilter().addCompareFilter(I_MD_Stock.COLUMN_QtyOnHand, Operator.GREATER, ZERO);
    augmentQueryBuilder(queryBuilder, ProductFilterUtil.extractProductFilterVO(filters));
    // note: need to afford loading *all* MD_Stock records for the material cockpit; afaik there isn't a product-related restriction there.
    // it's OK because they are aggregated on product, locator and attributesKey, so we won't have a million of them
    return queryBuilder.create();
}


}