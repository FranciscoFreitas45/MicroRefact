package de.metas.ui.web.window.descriptor;
 import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import org.elasticsearch.client.Client;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.descriptor.sql.ISqlLookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Functions;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = { "elasticsearchClient", "lookupDataSourceFactory" })
public class FullTextSearchLookupDescriptorProvider implements LookupDescriptorProvider{

 private  Client elasticsearchClient;

 private  LookupDataSourceFactory lookupDataSourceFactory;

 private  String modelTableName;

 private  String esIndexName;

 private  ImmutableSet<String> esSearchFieldNames;

 private  LookupDescriptorProvider databaseLookupDescriptorProvider;

 private  Function<LookupScope,Optional<LookupDescriptor>> lookupDescriptorsByScope;


public Optional<LookupDescriptor> createLookupDescriptor(LookupScope scope){
    final LookupDescriptor databaseLookupDescriptor = databaseLookupDescriptorProvider.provideForScope(scope).orElse(null);
    if (databaseLookupDescriptor == null) {
        return Optional.empty();
    }
    final LookupDataSource databaseLookup = lookupDataSourceFactory.createLookupDataSource(databaseLookupDescriptor);
    final FullTextSearchLookupDescriptor lookupDescriptor = FullTextSearchLookupDescriptor.builder().elasticsearchClient(elasticsearchClient).modelTableName(modelTableName).esIndexName(esIndexName).esSearchFieldNames(esSearchFieldNames).sqlLookupDescriptor(databaseLookupDescriptor.castOrNull(ISqlLookupDescriptor.class)).databaseLookup(databaseLookup).build();
    return Optional.of(lookupDescriptor);
}


@Override
public Optional<LookupDescriptor> provideForScope(LookupScope scope){
    return lookupDescriptorsByScope.apply(scope);
}


}