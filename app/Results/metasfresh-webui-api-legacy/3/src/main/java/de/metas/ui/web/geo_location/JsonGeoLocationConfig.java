package de.metas.ui.web.geo_location;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import javax.annotation.Nullable;
@Value
@Builder
public class JsonGeoLocationConfig {

@JsonProperty("provider")
@Nullable
 private  JsonGeoLocationProvider provider;

@JsonProperty("googleMapsApiKey")
@Nullable
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String googleMapsApiKey;


}