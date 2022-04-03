package de.metas.ui.web.document.filter.provider;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class CompositeDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider{

 private  ImmutableList<DocumentFilterDescriptorsProvider> providers;


@Override
public Collection<DocumentFilterDescriptor> getAll(){
    return providers.stream().map(provider -> provider.getAll()).flatMap(descriptors -> descriptors.stream()).sorted(Comparator.comparing(DocumentFilterDescriptor::getSortNo)).collect(// make sure each filterId is unique!
    GuavaCollectors.toImmutableMapByKey(descriptor -> descriptor.getFilterId())).values();
}


public DocumentFilterDescriptorsProvider compose(List<DocumentFilterDescriptorsProvider> providers){
    if (providers.isEmpty()) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    final ImmutableList<DocumentFilterDescriptorsProvider> nonNullProviders = providers.stream().filter(NullDocumentFilterDescriptorsProvider::isNotNull).collect(ImmutableList.toImmutableList());
    if (nonNullProviders.isEmpty()) {
        return NullDocumentFilterDescriptorsProvider.instance;
    } else if (nonNullProviders.size() == 1) {
        return nonNullProviders.get(0);
    }
    return new CompositeDocumentFilterDescriptorsProvider(nonNullProviders);
}


@Override
public DocumentFilterDescriptor getByFilterIdOrNull(String filterId){
    return providers.stream().map(provider -> provider.getByFilterIdOrNull(filterId)).filter(descriptor -> descriptor != null).findFirst().orElse(null);
}


}