package de.metas.ui.web.view;
 import java.util.Set;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
public class DefaultViewInvalidationAdvisor implements IViewInvalidationAdvisor{

 public  DefaultViewInvalidationAdvisor instance;


@Override
public Set<DocumentId> findAffectedRowIds(TableRecordReferenceSet recordRefs,IView view){
    final String viewTableName = view.getTableNameOrNull();
    if (viewTableName == null) {
        return ImmutableSet.of();
    }
    return recordRefs.streamByTableName(viewTableName).map(recordRef -> DocumentId.of(recordRef.getRecord_ID())).collect(ImmutableSet.toImmutableSet());
}


@Override
public WindowId getWindowId(){
    // this method shall never be called
    throw new UnsupportedOperationException();
}


}