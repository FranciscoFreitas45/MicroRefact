package de.metas.ui.web.document.geo_location.json;
 import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@Builder
public class JsonViewRowGeoLocation {

@NonNull
 private BigDecimal latitude;

@NonNull
 private BigDecimal longitude;

@NonNull
 private DocumentId rowId;


}