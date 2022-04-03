package de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates;
 import java.util.function.Predicate;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Check;
public class ContainsLookupValueFilterPredicate implements LookupValueFilterPredicate{

 private  String filterNormalized;

 private  String adLanguage;


@Override
public boolean test(LookupValue lookupValue){
    if (lookupValue == null) {
        return false;
    }
    final String displayName = adLanguage != null ? lookupValue.getDisplayName(adLanguage) : lookupValue.getDisplayName();
    if (displayName == null) {
        return false;
    }
    final String displayNameNormalized = normalizeString(displayName);
    return displayNameNormalized.indexOf(filterNormalized) >= 0;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper("ContainsIgnoreCase").omitNullValues().addValue(filterNormalized).add("adLanguage", adLanguage).toString();
}


public String normalizeString(String str){
    return str.toLowerCase();
}


@Override
public boolean isMatchAll(){
    return false;
}


}