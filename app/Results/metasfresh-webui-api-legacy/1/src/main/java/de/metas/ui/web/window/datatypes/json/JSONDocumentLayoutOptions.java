package de.metas.ui.web.window.datatypes.json;
 import java.time.Duration;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Suppliers;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.factory.NewRecordDescriptorsProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class JSONDocumentLayoutOptions {

@Getter
 private  JSONOptions jsonOpts;

@Getter
 private  boolean showAdvancedFields;

@Getter
 private  boolean debugShowColumnNamesForCaption;

@Getter
 private  NewRecordDescriptorsProvider newRecordDescriptorsProvider;

 private  Supplier<Duration> defaultLookupSearchStartDelaySupplier;

 private  Supplier<Duration> ZERO_DURATION_SUPPLIER;

 private  Predicate<DocumentLayoutElementDescriptor> _documentLayoutElementFilter;

 private  Predicate<DocumentLayoutElementDescriptor> FILTER_DocumentLayoutElementDescriptor_BASIC;

 private  Predicate<DocumentLayoutElementDescriptor> FILTER_DocumentLayoutElementDescriptor_ALL;


@VisibleForTesting
@Deprecated
public JSONDocumentLayoutOptions ofAdLanguage(String adLanguage){
    final JSONOptions jsonOpts = JSONOptions.ofAdLanguage(adLanguage);
    return _builder().jsonOpts(jsonOpts).build();
}


@Override
public boolean test(DocumentLayoutElementDescriptor layoutElement){
    return true;
}


public JSONDocumentLayoutOptions of(UserSession userSession){
    return prepareFrom(userSession).build();
}


public Predicate<DocumentLayoutElementDescriptor> documentLayoutElementFilter(){
    if (_documentLayoutElementFilter == null) {
        _documentLayoutElementFilter = isShowAdvancedFields() ? FILTER_DocumentLayoutElementDescriptor_ALL : FILTER_DocumentLayoutElementDescriptor_BASIC;
    }
    return _documentLayoutElementFilter;
}


public JSONDocumentLayoutOptionsBuilder prepareFrom(UserSession userSession){
    return _builder().jsonOpts(JSONOptions.of(userSession)).debugShowColumnNamesForCaption(userSession.isShowColumnNamesForCaption()).defaultLookupSearchStartDelaySupplier(ExtendedMemorizingSupplier.of(userSession.getDefaultLookupSearchStartDelay()));
}


@Override
public String toString(){
    return "all layout elements";
}


public Duration getDefaultLookupSearchStartDelay(){
    final Duration duration = defaultLookupSearchStartDelaySupplier.get();
    return duration != null ? duration : Duration.ZERO;
}


public String getAdLanguage(){
    return getJsonOpts().getAdLanguage();
}


}