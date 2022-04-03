package de.metas.ui.web.geo_location;
 import de.metas.location.geocoding.GeocodingConfig;
import de.metas.location.geocoding.GeocodingConfigRepository;
import de.metas.ui.web.config.WebConfig;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(GeoLocationConfigRestController.ENDPOINT)
public class GeoLocationConfigRestController {

 static  String ENDPOINT;

 private  GeocodingConfigRepository geocodingConfigRepository;


@NonNull
public JsonGeoLocationConfig getOpenStreetMapsConfig(){
    return JsonGeoLocationConfig.builder().provider(JsonGeoLocationProvider.OpenStreetMaps).build();
}


@NonNull
public JsonGeoLocationConfig getGoogleMapsConfig(GeocodingConfig geocodingConfig){
    final GeocodingConfig.GoogleMapsConfig googleMapsConfig = geocodingConfig.getGoogleMapsConfig();
    return JsonGeoLocationConfig.builder().provider(JsonGeoLocationProvider.GoogleMaps).googleMapsApiKey(googleMapsConfig.getApiKey()).build();
}


@GetMapping
public JsonGeoLocationConfig getConfig(){
    if (!geocodingConfigRepository.getGeocodingConfig().isPresent()) {
        return JsonGeoLocationConfig.builder().build();
    }
    final GeocodingConfig geocodingConfig = geocodingConfigRepository.getGeocodingConfig().get();
    switch(geocodingConfig.getProviderName()) {
        case GOOGLE_MAPS:
            return getGoogleMapsConfig(geocodingConfig);
        case OPEN_STREET_MAPS:
            return getOpenStreetMapsConfig();
        default:
            return JsonGeoLocationConfig.builder().build();
    }
}


}