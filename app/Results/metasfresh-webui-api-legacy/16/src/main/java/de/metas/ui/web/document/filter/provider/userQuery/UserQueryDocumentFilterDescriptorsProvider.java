package de.metas.ui.web.document.filter.provider.userQuery;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import org.compiere.apps.search.IUserQuery;
import org.compiere.apps.search.IUserQueryRestriction;
import org.compiere.apps.search.IUserQueryRestriction.Join;
import org.compiere.apps.search.UserQueryRepository;
import org.compiere.model.I_AD_UserQuery;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.cache.CachedSuppliers;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsConstants;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.util.StringUtils;
import lombok.NonNull;
public class UserQueryDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider{

 private  Logger logger;

 private  UserQueryRepository repository;

 private  Supplier<ImmutableMap<String,DocumentFilterDescriptor>> filtersSupplier;


public DocumentFilterDescriptor createFilterDescriptor0(IUserQuery userQuery,int sortNo){
    final DocumentFilterDescriptor.Builder filter = DocumentFilterDescriptor.builder().setFilterId("userquery-" + userQuery.getId()).setSortNo(sortNo).setDisplayName(userQuery.getCaption()).setFrequentUsed(false);
    if (WindowConstants.isProtocolDebugging()) {
        filter.putDebugProperty("userQuery", userQuery.toString());
    }
    for (final IUserQueryRestriction queryRestriction : userQuery.getRestrictions()) {
        final Join join = queryRestriction.getJoin();
        final UserQueryField searchField = UserQueryField.cast(queryRestriction.getSearchField());
        final String fieldName = searchField.getColumnName();
        final Operator operator = MQueryDocumentFilterHelper.fromMQueryOperator(queryRestriction.getOperator());
        final Object value = queryRestriction.getValue();
        final Object valueTo = queryRestriction.getValueTo();
        if (!queryRestriction.isInternalParameter()) {
            final ITranslatableString displayName = searchField.getDisplayName();
            final DocumentFieldWidgetType widgetType = searchField.getWidgetType();
            final Optional<LookupDescriptor> lookupDescriptor = searchField.getLookupDescriptor();
            filter.addParameter(DocumentFilterParamDescriptor.builder().setJoinAnd(join == Join.AND).setDisplayName(displayName).setFieldName(fieldName).setWidgetType(widgetType).setOperator(operator).setDefaultValue(value).setDefaultValueTo(valueTo).setMandatory(queryRestriction.isMandatory()).setLookupDescriptor(lookupDescriptor));
        } else {
            filter.addInternalParameter(DocumentFilterParam.builder().setJoinAnd(join == Join.AND).setFieldName(fieldName).setOperator(operator).setValue(value).setValueTo(valueTo).build());
        }
    }
    return filter.build();
}


@Override
public Collection<DocumentFilterDescriptor> getAll(){
    return filtersSupplier.get().values();
}


public DocumentFilterDescriptor createFilterDescriptorOrNull(IUserQuery userQuery,int sortNo){
    try {
        return createFilterDescriptor0(userQuery, sortNo);
    } catch (final RuntimeException e) {
        final String message = StringUtils.formatMessage("Unable to create a DocumentFilterDescriptor for the userQuery with {}={}; Deactivating this query; Exception={}", I_AD_UserQuery.Table_Name, userQuery.getId(), e);
        logger.error(message, e);
        repository.deactivateUserQuery(userQuery);
        return null;
    }
}


@Override
public DocumentFilterDescriptor getByFilterIdOrNull(String filterId){
    return filtersSupplier.get().get(filterId);
}


public ImmutableMap<String,DocumentFilterDescriptor> retrieveAllByFilterId(){
    final ArrayList<DocumentFilterDescriptor> filters = new ArrayList<>();
    for (final IUserQuery userQuery : repository.getUserQueries()) {
        final int sortNo = DocumentFilterDescriptorsConstants.SORT_NO_USER_QUERY_START + filters.size() + 1;
        final DocumentFilterDescriptor filter = createFilterDescriptorOrNull(userQuery, sortNo);
        if (filter != null) {
            filters.add(filter);
        }
    }
    return Maps.uniqueIndex(filters, DocumentFilterDescriptor::getFilterId);
}


}