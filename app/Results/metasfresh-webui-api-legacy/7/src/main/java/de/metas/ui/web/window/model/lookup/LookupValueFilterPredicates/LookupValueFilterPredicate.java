package de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates;
 import java.util.function.Predicate;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Check;
public interface LookupValueFilterPredicate extends Predicate<LookupValue>{


@Override
public boolean test(LookupValue lookupValue)
;

public boolean isMatchAll()
;

}