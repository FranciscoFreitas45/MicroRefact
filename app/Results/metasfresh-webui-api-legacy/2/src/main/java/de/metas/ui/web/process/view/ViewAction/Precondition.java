package de.metas.ui.web.process.view.ViewAction;
 import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.ProcessInstanceResult.ResultAction;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
public interface Precondition {


public ProcessPreconditionsResolution matches(IView view,DocumentIdsSelection selectedDocumentIds)
;

}