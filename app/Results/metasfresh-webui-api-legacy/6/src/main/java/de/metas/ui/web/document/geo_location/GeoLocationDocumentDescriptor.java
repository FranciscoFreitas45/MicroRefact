package de.metas.ui.web.document.geo_location;
 import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class GeoLocationDocumentDescriptor {

@NonNull
 private LocationColumnNameType type;

@NonNull
 private String locationColumnName;


}