package de.metas.ui.web.window.descriptor.ListLookupDescriptor;
 import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.util.Check;
import lombok.NonNull;
public class Builder {

 private  LookupSource lookupSourceType;

 private  boolean numericKey;

 private  Function<LookupDataSourceContext,LookupValuesList> lookupValues;

 private  Function<LookupDataSourceContext,LookupValue> filteredLookupValues;

 private  Set<String> dependsOnFieldNames;

 private  Optional<String> lookupTableName;


public Builder setLookupSourceType(LookupSource lookupSourceType){
    this.lookupSourceType = lookupSourceType;
    return this;
}


public ListLookupDescriptor build(){
    return new ListLookupDescriptor(this);
}


public Builder setDependsOnFieldNames(String[] dependsOnFieldNames){
    this.dependsOnFieldNames = ImmutableSet.copyOf(dependsOnFieldNames);
    return this;
}


public Builder setStringLookupValues(Function<LookupDataSourceContext,LookupValuesList> lookupValues){
    setLookupValues(false, lookupValues);
    return this;
}


public Builder setLookupValues(boolean numericKey,Function<LookupDataSourceContext,LookupValuesList> lookupValues){
    this.numericKey = numericKey;
    this.lookupValues = lookupValues;
    return this;
}


public Builder setIntegerLookupValues(Function<LookupDataSourceContext,LookupValuesList> lookupValues){
    setLookupValues(true, lookupValues);
    return this;
}


public Builder setFilteredLookupValues(Function<LookupDataSourceContext,LookupValue> filteredLookupValues){
    this.filteredLookupValues = filteredLookupValues;
    return this;
}


public Builder setLookupTableName(String lookupTableName){
    this.lookupTableName = Check.isEmpty(lookupTableName, true) ? Optional.empty() : Optional.of(lookupTableName);
    return this;
}


}