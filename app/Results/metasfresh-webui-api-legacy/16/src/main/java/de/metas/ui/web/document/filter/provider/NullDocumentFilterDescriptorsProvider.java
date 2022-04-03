package de.metas.ui.web.document.filter.provider;
 import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
@Immutable
public class NullDocumentFilterDescriptorsProvider implements DocumentFilterDescriptorsProvider{

 public  NullDocumentFilterDescriptorsProvider instance;


public boolean isNotNull(DocumentFilterDescriptorsProvider provider){
    return !isNull(provider);
}


@JsonValue
public Map<String,String> toJson(){
    return ImmutableMap.of();
}


@Override
public Collection<DocumentFilterDescriptor> getAll(){
    return ImmutableList.of();
}


@Override
public DocumentFilterDescriptor getByFilterIdOrNull(String filterId){
    return null;
}


public boolean isNull(DocumentFilterDescriptorsProvider provider){
    return provider == null || provider == instance;
}


}