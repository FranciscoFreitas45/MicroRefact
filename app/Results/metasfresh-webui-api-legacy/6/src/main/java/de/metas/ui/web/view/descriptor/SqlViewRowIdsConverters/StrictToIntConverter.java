package de.metas.ui.web.view.descriptor.SqlViewRowIdsConverters;
 import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.experimental.UtilityClass;
public class StrictToIntConverter implements SqlViewRowIdsConverter{


@Override
public Set<Integer> convertToRecordIds(DocumentIdsSelection rowIds){
    return rowIds.toIntSet();
}


}