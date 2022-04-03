package de.metas.ui.web.quickinput;
 import java.util.Set;
import de.metas.ui.web.window.datatypes.DocumentId;
public interface IQuickInputProcessor {


public Set<DocumentId> process(QuickInput quickInput)
;

}