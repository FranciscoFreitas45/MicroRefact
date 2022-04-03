package DTO;
 import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.model.PlainContextAware;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.UtilityClass;
public class HUIdsFilterData {

 private  ImmutableSet<HuId> initialHUIds;

 private  IHUQueryBuilder initialHUQuery;

 private  HashSet<HuId> mustHUIds;

 private  HashSet<HuId> shallNotHUIds;


public Set<HuId> getShallNotHUIds(){
    return ImmutableSet.copyOf(shallNotHUIds);
}


public IHUQueryBuilder getInitialHUQueryOrNull(){
    return initialHUQuery != null ? initialHUQuery.copy() : null;
}


public ImmutableSet<HuId> getInitialHUIds(){
    return initialHUIds;
}


public Set<HuId> getMustHUIds(){
    return ImmutableSet.copyOf(mustHUIds);
}


}