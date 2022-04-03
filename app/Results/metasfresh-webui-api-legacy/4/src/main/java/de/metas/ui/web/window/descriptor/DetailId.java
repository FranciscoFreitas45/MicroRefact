package de.metas.ui.web.window.descriptor;
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
@Immutable
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class DetailId implements Comparable<DetailId>{

 private  String PARTS_SEPARATOR;

 private  String PREFIX_AD_TAB_ID;

@Getter
 private  String idPrefix;

@Getter
 private  int idInt;

 private  String _tableAlias;


@JsonValue
public String toJson(){
    return toJson(this);
}


public String getTableAlias(){
    if (_tableAlias == null) {
        _tableAlias = "d" + idInt;
    }
    return _tableAlias;
}


public DetailId fromPrefixAndId(String prefix,int id){
    return new DetailId(prefix, id);
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
}


public AdTabId toAdTabId(){
    if (PREFIX_AD_TAB_ID.equals(idPrefix)) {
        return AdTabId.ofRepoId(idInt);
    }
    return null;
}


@Override
public String toString(){
    return toJson(this);
}


@Override
public int compareTo(DetailId o){
    if (o == null) {
        return 1;
    }
    return Objects.compare(toJson(), o.toJson(), Comparator.naturalOrder());
}


public DetailId fromAD_Tab_ID(int adTabId){
    return new DetailId(PREFIX_AD_TAB_ID, adTabId);
}


}