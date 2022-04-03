package de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
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
public class Builder {

 private  List<DocumentFilterDescriptor> descriptors;


public ImmutableDocumentFilterDescriptorsProvider build(){
    if (descriptors.isEmpty()) {
        return EMPTY;
    }
    return new ImmutableDocumentFilterDescriptorsProvider(descriptors);
}


public Builder addDescriptor(DocumentFilterDescriptor descriptor){
    descriptors.add(descriptor);
    return this;
}


public Builder addDescriptors(DocumentFilterDescriptorsProvider provider){
    addDescriptors(provider.getAll());
    return this;
}


}