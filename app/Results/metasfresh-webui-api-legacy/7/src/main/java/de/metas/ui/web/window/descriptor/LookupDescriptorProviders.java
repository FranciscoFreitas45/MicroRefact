package de.metas.ui.web.window.descriptor;
 import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider.LookupScope;
import de.metas.util.Functions;
import de.metas.util.Functions.MemoizingFunction;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.UtilityClass;
@UtilityClass
public class LookupDescriptorProviders {

 public  LookupDescriptorProvider NULL;

 private  Optional<LookupDescriptor> lookupDescriptor;

 private  MemoizingFunction<LookupScope,LookupDescriptor> providerFunctionMemoized;


public LookupDescriptorProvider singleton(LookupDescriptor lookupDescriptor){
    return new SingletonLookupDescriptorProvider(lookupDescriptor);
}


public LookupDescriptorProvider ofNullableInstance(LookupDescriptor lookupDescriptor){
    return lookupDescriptor != null ? singleton(lookupDescriptor) : NULL;
}


public LookupDescriptorProvider fromMemoizingFunction(Function<LookupScope,LookupDescriptor> providerFunction){
    return new MemoizingFunctionLookupDescriptorProvider(providerFunction);
}


@Override
public Optional<LookupDescriptor> provideForScope(LookupScope scope){
    return Optional.ofNullable(providerFunctionMemoized.apply(scope));
}


}