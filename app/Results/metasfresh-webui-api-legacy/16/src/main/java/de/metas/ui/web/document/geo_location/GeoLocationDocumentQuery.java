package de.metas.ui.web.document.geo_location;
 import java.math.BigDecimal;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class GeoLocationDocumentQuery {

@NonNull
 private IntegerLookupValue country;

 private String address1;

 private String city;

 private String postal;

@NonNull
 private BigDecimal distanceInKm;

 private boolean visitorsAddress;


}