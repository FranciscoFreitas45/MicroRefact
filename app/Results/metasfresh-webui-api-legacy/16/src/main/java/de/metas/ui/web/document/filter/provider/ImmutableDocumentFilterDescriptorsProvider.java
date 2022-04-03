package de.metas.ui.web.document.filter.provider;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import lombok.NonNull;
@Immutable
public class ImmutableDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider{

 private  ImmutableDocumentFilterDescriptorsProvider EMPTY;

 private  ImmutableMap<String,DocumentFilterDescriptor> descriptorsByFilterId;

 private  List<DocumentFilterDescriptor> descriptors;


@Override
public Collection<DocumentFilterDescriptor> getAll(){
    return descriptorsByFilterId.values();
}


public ImmutableDocumentFilterDescriptorsProvider build(){
    if (descriptors.isEmpty()) {
        return EMPTY;
    }
    return new ImmutableDocumentFilterDescriptorsProvider(descriptors);
}


@Override
public DocumentFilterDescriptor getByFilterIdOrNull(String filterId){
    return descriptorsByFilterId.get(filterId);
}


public Builder addDescriptor(DocumentFilterDescriptor descriptor){
    descriptors.add(descriptor);
    return this;
}


public ImmutableDocumentFilterDescriptorsProvider of(DocumentFilterDescriptor descriptors){
    if (descriptors == null || descriptors.length == 0) {
        return EMPTY;
    }
    return new ImmutableDocumentFilterDescriptorsProvider(Arrays.asList(descriptors));
}


public Builder builder(){
    return new Builder();
}


public Builder addDescriptors(DocumentFilterDescriptorsProvider provider){
    addDescriptors(provider.getAll());
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(descriptorsByFilterId.keySet()).toString();
}


}