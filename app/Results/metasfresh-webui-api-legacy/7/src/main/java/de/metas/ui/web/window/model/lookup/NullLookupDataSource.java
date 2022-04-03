package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import org.compiere.util.Evaluatee;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
public class NullLookupDataSource implements LookupDataSource{

 public  NullLookupDataSource instance;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of();
}


@Override
public DocumentZoomIntoInfo getDocumentZoomInto(int id){
    throw new UnsupportedOperationException();
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public LookupValuesList findEntities(Evaluatee ctx,int pageLength){
    return LookupValuesList.EMPTY;
}


@Override
public LookupValue findById(Object id){
    if (id == null) {
        return null;
    }
    if (id instanceof Integer) {
        return IntegerLookupValue.unknown((int) id);
    } else {
        return StringLookupValue.unknown(id.toString());
    }
}


@Override
public void cacheInvalidate(){
}


}