package de.metas.ui.web.window.model.lookup;
 import java.util.function.Predicate;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Check;
public class LookupValueFilterPredicates {

 public  LookupValueFilterPredicate MATCH_ALL;

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


public LookupValueFilterPredicate of(String filter){
    // N/A
    final String adLanguage = null;
    return ofFilterAndLanguage(filter, adLanguage);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper("ContainsIgnoreCase").omitNullValues().addValue(filterNormalized).add("adLanguage", adLanguage).toString();
}


public LookupValueFilterPredicate ofFilterAndLanguage(String filter,String adLanguage){
    if (filter == null) {
        return MATCH_ALL;
    }
    final String filterNorm = filter.trim();
    if (filterNorm.isEmpty()) {
        return MATCH_ALL;
    }
    return new ContainsLookupValueFilterPredicate(filterNorm, adLanguage);
}


public String normalizeString(String str){
    return str.toLowerCase();
}


@Override
public boolean isMatchAll(){
    return false;
}


}