package de.metas.ui.web.window.descriptor;
 import java.util.Optional;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
@FunctionalInterface
public interface LookupDescriptorProvider {


public Optional<LookupSource> getLookupSourceType(){
    return provide().map(LookupDescriptor::getLookupSourceType);
}
;

public Optional<LookupDescriptor> provide(){
    return provideForScope(LookupScope.DocumentField);
}
;

public Optional<String> getTableName(){
    return provide().flatMap(LookupDescriptor::getTableName);
}
;

public boolean isNumericKey(){
    return provide().map(LookupDescriptor::isNumericKey).orElse(false);
}
;

public Optional<LookupDescriptor> provideForScope(LookupScope scope)
;

public Optional<LookupDescriptor> provideForFilter(){
    return provideForScope(LookupScope.DocumentFilter);
}
;

}