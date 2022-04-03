package de.metas.ui.web.material.cockpit.filters;
 import java.time.LocalDate;
import java.util.function.Predicate;
import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.impl.CompareQueryFilter;
import org.compiere.model.IQuery;
import org.compiere.model.I_M_Product;
import org.springframework.stereotype.Service;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class MaterialCockpitFilters {

 private  DocumentFilterDescriptorsProvider filterDescriptors;


public boolean augmentQueryBuilder(IQueryBuilder<I_MD_Cockpit> queryBuilder,ProductFilterVO productFilterVO){
    final IQuery<I_M_Product> productQuery = ProductFilterUtil.createProductQueryOrNull(productFilterVO);
    if (productQuery == null) {
        return false;
    }
    queryBuilder.addInSubQueryFilter(I_MD_Cockpit.COLUMN_M_Product_ID, I_M_Product.COLUMN_M_Product_ID, productQuery);
    return true;
}


public DocumentFilterList extractDocumentFilters(CreateViewRequest request){
    final DocumentFilterDescriptorsProvider provider = getFilterDescriptors();
    return request.getFiltersUnwrapped(provider);
}


public IQuery<I_MD_Cockpit> createQuery(DocumentFilterList filters){
    final IQueryBuilder<I_MD_Cockpit> queryBuilder = createInitialQueryBuilder();
    boolean anyRestrictionAdded = false;
    if (augmentQueryBuilder(queryBuilder, DateFilterUtil.extractDateFilterVO(filters))) {
        anyRestrictionAdded = true;
    }
    if (augmentQueryBuilder(queryBuilder, ProductFilterUtil.extractProductFilterVO(filters))) {
        anyRestrictionAdded = true;
    }
    if (anyRestrictionAdded) {
        final IQuery<I_MD_Cockpit> query = augmentQueryBuilderWithOrderBy(queryBuilder).create();
        return query;
    } else {
        // avoid memory problems in case the filters are accidentally empty
        return queryBuilder.filter(ConstantQueryFilter.of(false)).create();
    }
}


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    if (filterDescriptors == null) {
        filterDescriptors = createFilterDescriptors();
    }
    return filterDescriptors;
}


public DocumentFilterList createAutoFilters(){
    return DocumentFilterList.of(DateFilterUtil.createFilterToday());
}


public IQueryBuilder<I_MD_Cockpit> augmentQueryBuilderWithOrderBy(IQueryBuilder<I_MD_Cockpit> queryBuilder){
    return queryBuilder.orderBy().addColumn(I_MD_Cockpit.COLUMN_DateGeneral).addColumn(I_MD_Cockpit.COLUMN_M_Product_ID).addColumn(I_MD_Cockpit.COLUMN_AttributesKey).endOrderBy();
}


public Predicate<I_M_Product> toProductFilterPredicate(DocumentFilterList filters){
    return ProductFilterUtil.toPredicate(filters);
}


public DocumentFilterDescriptorsProvider createFilterDescriptors(){
    return ImmutableDocumentFilterDescriptorsProvider.of(DateFilterUtil.createFilterDescriptor(), ProductFilterUtil.createFilterDescriptor());
}


public IQueryBuilder<I_MD_Cockpit> createInitialQueryBuilder(){
    final IQueryBL queryBL = Services.get(IQueryBL.class);
    final IQueryBuilder<I_MD_Cockpit> queryBuilder = queryBL.createQueryBuilder(I_MD_Cockpit.class).addOnlyActiveRecordsFilter();
    return queryBuilder;
}


public LocalDate getFilterByDate(DocumentFilterList filters){
    final DateFilterVO dateFilterVO = DateFilterUtil.extractDateFilterVO(filters);
    return dateFilterVO.getDate();
}


}