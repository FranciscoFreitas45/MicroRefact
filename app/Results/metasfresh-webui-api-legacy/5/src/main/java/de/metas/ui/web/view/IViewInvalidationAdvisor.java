package de.metas.ui.web.view;
 import java.util.Set;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.springframework.stereotype.Component;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
public interface IViewInvalidationAdvisor {


public Set<DocumentId> findAffectedRowIds(TableRecordReferenceSet recordRefs,IView view)
;

public WindowId getWindowId()
;

}