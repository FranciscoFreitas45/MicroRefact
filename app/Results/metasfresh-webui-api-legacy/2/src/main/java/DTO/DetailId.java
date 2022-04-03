package DTO;
 import de.metas.util.Check.assumeNotEmpty;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.ad.element.api.AdTabId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableSet;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
public class DetailId implements Comparable<DetailId>{

 private  String PARTS_SEPARATOR;

 private  String PREFIX_AD_TAB_ID;

 private  String idPrefix;

 private  int idInt;

 private  String _tableAlias;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getTableAlias(){
    if (_tableAlias == null) {
        _tableAlias = "d" + idInt;
    }
    return _tableAlias;
}


@JsonCreator
public DetailId fromJson(String json){
    if (json == null) {
        return null;
    }
    final String jsonToUse = json.trim();
    if (jsonToUse.isEmpty()) {
        return null;
    }
    final String[] prefixAndId = jsonToUse.split(PARTS_SEPARATOR);
    Check.assume(prefixAndId.length == 2, "The given json needs to consist of a prefix and the actual ID, separated by {}; json={}", PARTS_SEPARATOR, json);
    final String prefix = prefixAndId[0];
    final int idInt = Integer.parseInt(prefixAndId[1]);
    return DetailId.fromPrefixAndId(prefix, idInt);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromJson"))

.queryParam("json",json);
DetailId aux = restTemplate.getForObject(builder.toUriString(),DetailId.class);
return aux;
}


}