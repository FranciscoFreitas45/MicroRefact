package de.metas.ui.web.document.filter.provider.standard;
 import de.metas.ui.web.window.datatypes.LookupValuesList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Builder
@Value
public class FacetFilterViewCache {

@NonNull
 private String filterId;

@NonNull
 private LookupValuesList availableValues;


}