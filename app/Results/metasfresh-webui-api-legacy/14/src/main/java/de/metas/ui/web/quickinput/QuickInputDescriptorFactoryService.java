package de.metas.ui.web.quickinput;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import de.metas.cache.CCache;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.NonNull;
@Service
public class QuickInputDescriptorFactoryService {

 private  Logger logger;

 private  ImmutableListMultimap<IQuickInputDescriptorFactory.MatchingKey,IQuickInputDescriptorFactory> factories;

 private  CCache<QuickInputDescriptorKey,QuickInputDescriptor> descriptors;

@NonNull
 private DocumentType documentType;

@NonNull
 private DocumentId documentTypeId;

@NonNull
 private Optional<String> includedTableName;

@NonNull
 private DetailId includedTabId;

@NonNull
 private Optional<SOTrx> soTrx;


public IQuickInputDescriptorFactory getQuickInputDescriptorFactory(IQuickInputDescriptorFactory.MatchingKey matchingKey){
    final ImmutableList<IQuickInputDescriptorFactory> matchingFactories = factories.get(matchingKey);
    if (matchingFactories.isEmpty()) {
        return null;
    }
    if (matchingFactories.size() > 1) {
        logger.warn("More than one factory found for {}. Using the first one: {}", matchingFactories);
    }
    return matchingFactories.get(0);
}


public boolean hasQuickInputEntityDescriptor(QuickInputDescriptorKey key){
    return getQuickInputEntityDescriptorOrNull(key) != null;
}


public QuickInputDescriptor getQuickInputEntityDescriptor(DocumentEntityDescriptor includedDocumentDescriptor){
    if (!includedDocumentDescriptor.isAllowQuickInput()) {
        throw new AdempiereException("Quick input is not supported for " + includedDocumentDescriptor.getInternalName());
    }
    final QuickInputDescriptorKey key = createQuickInputDescriptorKey(includedDocumentDescriptor);
    final QuickInputDescriptor descriptor = getQuickInputEntityDescriptorOrNull(key);
    if (descriptor == null) {
        throw new EntityNotFoundException("Batch input not available; found no QuickInputDescriptor for the given parameters").appendParametersToMessage().setParameter("key", key);
    }
    return descriptor;
}


public QuickInputDescriptor getQuickInputEntityDescriptorOrNull(QuickInputDescriptorKey key){
    return descriptors.getOrLoad(key, this::createQuickInputDescriptorOrNull);
}


public ImmutableListMultimap<IQuickInputDescriptorFactory.MatchingKey,IQuickInputDescriptorFactory> createFactoriesFromContext(List<IQuickInputDescriptorFactory> factoriesList){
    if (factoriesList == null || factoriesList.isEmpty()) {
        return ImmutableListMultimap.of();
    }
    final ImmutableListMultimap.Builder<IQuickInputDescriptorFactory.MatchingKey, IQuickInputDescriptorFactory> factoriesMap = ImmutableListMultimap.builder();
    for (final IQuickInputDescriptorFactory factory : factoriesList) {
        final Set<IQuickInputDescriptorFactory.MatchingKey> matchingKeys = factory.getMatchingKeys();
        if (matchingKeys == null || matchingKeys.isEmpty()) {
            logger.warn("Ignoring {} because it provides no matching keys", factory);
            break;
        }
        for (final IQuickInputDescriptorFactory.MatchingKey matchingKey : matchingKeys) {
            factoriesMap.put(matchingKey, factory);
        }
    }
    return factoriesMap.build();
}


public QuickInputDescriptorKey createQuickInputDescriptorKey(DocumentEntityDescriptor includedDocumentDescriptor){
    return QuickInputDescriptorKey.builder().documentType(includedDocumentDescriptor.getDocumentType()).documentTypeId(includedDocumentDescriptor.getDocumentTypeId()).includedTableName(Optional.ofNullable(includedDocumentDescriptor.getTableNameOrNull())).includedTabId(includedDocumentDescriptor.getDetailId()).soTrx(includedDocumentDescriptor.getSOTrx()).build();
}


public QuickInputDescriptor createQuickInputDescriptorOrNull(QuickInputDescriptorKey key){
    final IQuickInputDescriptorFactory quickInputDescriptorFactory = getQuickInputDescriptorFactory(key);
    if (quickInputDescriptorFactory == null) {
        return null;
    }
    return quickInputDescriptorFactory.createQuickInputDescriptor(key.getDocumentType(), key.getDocumentTypeId(), key.getIncludedTabId(), key.getSoTrx());
}


}