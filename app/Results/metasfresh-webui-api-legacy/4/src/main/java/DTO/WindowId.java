package DTO;
 import java.util.OptionalInt;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class WindowId {

 private  String value;

 private  OptionalInt valueInt;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public WindowId of(DocumentId documentTypeId){
    if (documentTypeId.isInt()) {
        return new WindowId(documentTypeId.toInt());
    } else {
        return new WindowId(documentTypeId.toJson());
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("documentTypeId",documentTypeId);
WindowId aux = restTemplate.getForObject(builder.toUriString(),WindowId.class);
return aux;
}


public boolean isInt(){
    try {
        toInt();
        return true;
    } catch (Exception ex) {
        return false;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isInt"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public DocumentId toDocumentId(){
    return DocumentId.of(value);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentId"))

DocumentId aux = restTemplate.getForObject(builder.toUriString(),DocumentId.class);
return aux;
}


public AdWindowId toAdWindowIdOrNull(){
    return AdWindowId.ofRepoIdOrNull(toIntOr(-1));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toAdWindowIdOrNull"))

AdWindowId aux = restTemplate.getForObject(builder.toUriString(),AdWindowId.class);
return aux;
}


public AdWindowId toAdWindowId(){
    return AdWindowId.ofRepoId(toInt());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toAdWindowId"))

AdWindowId aux = restTemplate.getForObject(builder.toUriString(),AdWindowId.class);
return aux;
}


@JsonCreator
public WindowId fromJson(String json){
    return new WindowId(json);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromJson"))

.queryParam("json",json);
WindowId aux = restTemplate.getForObject(builder.toUriString(),WindowId.class);
return aux;
}


}