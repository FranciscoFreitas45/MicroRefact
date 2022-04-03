package DTO;
 import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import org.compiere.util.Evaluatee;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
public interface LookupDataSource extends LookupValueByIdSupplier{

 private int FIRST_ROW;

 private int DEFAULT_PageLength;


public List<CCacheStats> getCacheStats()
;

public DocumentZoomIntoInfo getDocumentZoomInto(int id)
;

@NonNull
public LookupValuesList findByIdsOrdered(Collection<? extends Object> ids){
    if (ids.isEmpty()) {
        return LookupValuesList.EMPTY;
    }
    // TODO @teo: avoid SQL N+1 problem and also return the data ordered in the input collection order
    return new LinkedHashSet<>(ids).stream().map(this::findById).filter(Objects::nonNull).collect(LookupValuesList.collect());
}
;

public Optional<WindowId> getZoomIntoWindowId()
;

public LookupValuesList findEntities(Evaluatee ctx){
    return findEntities(ctx, Integer.MAX_VALUE);
}
;

@Override
public LookupValue findById(Object id)
;

public void cacheInvalidate()
;

}