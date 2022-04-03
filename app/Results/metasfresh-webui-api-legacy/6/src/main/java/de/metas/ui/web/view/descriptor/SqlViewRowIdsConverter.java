package de.metas.ui.web.view.descriptor;
 import java.util.Set;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
@FunctionalInterface
public interface SqlViewRowIdsConverter {


public Set<Integer> convertToRecordIds(DocumentIdsSelection rowIds)
;

}