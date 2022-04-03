package DTO;
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

 private  JSONOptions jsonOpts;

 private  boolean showAdvancedFields;

 private  boolean debugShowColumnNamesForCaption;

 private  NewRecordDescriptorsProvider newRecordDescriptorsProvider;

 private  Supplier<Duration> defaultLookupSearchStartDelaySupplier;

 private  Supplier<Duration> ZERO_DURATION_SUPPLIER;

 private  Predicate<DocumentLayoutElementDescriptor> _documentLayoutElementFilter;

 private  Predicate<DocumentLayoutElementDescriptor> FILTER_DocumentLayoutElementDescriptor_BASIC;

 private  Predicate<DocumentLayoutElementDescriptor> FILTER_DocumentLayoutElementDescriptor_ALL;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Duration getDefaultLookupSearchStartDelay(){
    final Duration duration = defaultLookupSearchStartDelaySupplier.get();
    return duration != null ? duration : Duration.ZERO;
}


public String getAdLanguage(){
    return getJsonOpts().getAdLanguage();
}


public JSONDocumentLayoutOptions of(UserSession userSession){
    return prepareFrom(userSession).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("userSession",userSession);
JSONDocumentLayoutOptions aux = restTemplate.getForObject(builder.toUriString(),JSONDocumentLayoutOptions.class);
return aux;
}


}