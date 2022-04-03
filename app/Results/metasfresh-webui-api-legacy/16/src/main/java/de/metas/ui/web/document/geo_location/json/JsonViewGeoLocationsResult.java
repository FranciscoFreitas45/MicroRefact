package de.metas.ui.web.document.geo_location.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class JsonViewGeoLocationsResult {

@JsonProperty("viewId")
@NonNull
 private  String viewId;

@JsonProperty("locations")
@NonNull
 private List<JsonViewRowGeoLocation> locations;


}