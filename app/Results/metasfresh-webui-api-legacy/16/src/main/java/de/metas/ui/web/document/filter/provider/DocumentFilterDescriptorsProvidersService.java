package de.metas.ui.web.document.filter.provider;
 import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdTabId;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import lombok.NonNull;
@Service
public class DocumentFilterDescriptorsProvidersService {

 private  Logger logger;

 private  ImmutableList<DocumentFilterDescriptorsProviderFactory> providerFactories;


public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId,String tableName,Collection<DocumentFieldDescriptor> fields){
    final ImmutableList<DocumentFilterDescriptorsProvider> providers = providerFactories.stream().filter(DocumentFilterDescriptorsProviderFactory::isActive).map(provider -> provider.createFiltersProvider(adTabId, tableName, fields)).filter(NullDocumentFilterDescriptorsProvider::isNotNull).collect(ImmutableList.toImmutableList());
    return CompositeDocumentFilterDescriptorsProvider.compose(providers);
}


}