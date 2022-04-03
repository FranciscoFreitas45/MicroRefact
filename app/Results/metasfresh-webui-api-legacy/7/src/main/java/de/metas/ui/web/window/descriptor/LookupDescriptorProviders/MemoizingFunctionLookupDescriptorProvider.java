package de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
 import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider.LookupScope;
import de.metas.util.Functions;
import de.metas.util.Functions.MemoizingFunction;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.UtilityClass;
@ToString
public class MemoizingFunctionLookupDescriptorProvider implements LookupDescriptorProvider{

 private  MemoizingFunction<LookupScope,LookupDescriptor> providerFunctionMemoized;


@Override
public Optional<LookupDescriptor> provideForScope(LookupScope scope){
    return Optional.ofNullable(providerFunctionMemoized.apply(scope));
}


}