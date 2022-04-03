package de.metas.ui.web.view.descriptor;
 import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.experimental.UtilityClass;
@UtilityClass
public class SqlViewRowIdsConverters {

 public  SqlViewRowIdsConverter TO_INT_STRICT;

 public  SqlViewRowIdsConverter TO_INT_EXCLUDING_STRINGS;


@Override
public Set<Integer> convertToRecordIds(DocumentIdsSelection rowIds){
    return rowIds.stream().filter(// exclude non-int values
    DocumentId::isInt).map(DocumentId::toInt).collect(ImmutableSet.toImmutableSet());
}


}