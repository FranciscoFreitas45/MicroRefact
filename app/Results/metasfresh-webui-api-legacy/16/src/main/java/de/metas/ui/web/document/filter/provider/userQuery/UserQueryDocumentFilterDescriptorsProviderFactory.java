package de.metas.ui.web.document.filter.provider.userQuery;
 import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProviderFactory;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.Services;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.table.api.AdTableId;
import org.adempiere.ad.table.api.IADTableDAO;
import org.compiere.apps.search.IUserQueryField;
import org.compiere.apps.search.UserQueryRepository;
import org.compiere.model.POInfo;
import org.springframework.stereotype.Component;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
@Component
public class UserQueryDocumentFilterDescriptorsProviderFactory implements DocumentFilterDescriptorsProviderFactory{

 private  IADTableDAO adTablesRepo;


@Override
public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId,String tableName,Collection<DocumentFieldDescriptor> fields){
    if (tableName == null || adTabId == null) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    Check.assumeNotEmpty(tableName, "tableName is not empty");
    final int adTableId = adTablesRepo.retrieveTableId(tableName);
    final List<IUserQueryField> searchFields = fields.stream().map(field -> createUserQueryField(field)).collect(ImmutableList.toImmutableList());
    final UserQueryRepository repository = UserQueryRepository.builder().setAD_Tab_ID(adTabId.getRepoId()).setAD_Table_ID(adTableId).setAD_User_ID(// FIXME: hardcoded, see https://github.com/metasfresh/metasfresh-webui/issues/162
    UserId.METASFRESH.getRepoId()).setSearchFields(searchFields).setColumnDisplayTypeProvider(POInfo.getPOInfo(AdTableId.ofRepoId(adTableId))).build();
    return new UserQueryDocumentFilterDescriptorsProvider(repository);
}


public UserQueryField createUserQueryField(DocumentFieldDescriptor field){
    return UserQueryField.builder().columnName(field.getFieldName()).displayName(field.getCaption()).widgetType(field.getWidgetType()).lookupDescriptor(field.getLookupDescriptor()).build();
}


}