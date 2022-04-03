package de.metas.ui.web.window.descriptor;
 import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
public interface LookupDescriptor {


public Class<?> getValueClass(){
    return isNumericKey() ? IntegerLookupValue.class : StringLookupValue.class;
}
;

public T castOrNull(Class<T> lookupDescriptorClass){
    if (lookupDescriptorClass.isAssignableFrom(getClass())) {
        @SuppressWarnings("unchecked")
        final T thisCasted = (T) this;
        return thisCasted;
    }
    return null;
}
;

public LookupDataSourceFetcher getLookupDataSourceFetcher()
;

public LookupSource getLookupSourceType()
;

public boolean isHighVolume()
;

public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}
;

public int getSearchStringMinLength(){
    return -1;
}
;

public Optional<String> getTableName(){
    return Optional.empty();
}
;

public Optional<Duration> getSearchStartDelay(){
    return Optional.empty();
}
;

public boolean isNumericKey()
;

public T cast(Class<T> lookupDescriptorClass){
    @SuppressWarnings("unchecked")
    final T thisCasted = (T) this;
    return thisCasted;
}
;

public boolean hasParameters()
;

@Override
public int hashCode()
;

@Override
public boolean equals(Object obj)
;

public Set<String> getDependsOnFieldNames()
;

public Set<String> getDependsOnTableNames(){
    return ImmutableSet.of();
}
;

}