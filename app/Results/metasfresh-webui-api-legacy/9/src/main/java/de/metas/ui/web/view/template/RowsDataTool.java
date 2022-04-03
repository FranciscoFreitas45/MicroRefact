package de.metas.ui.web.view.template;
 import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class RowsDataTool {


public Map<DocumentId,T> extractAllIncludedRows(T topLevelRow){
    @SuppressWarnings("unchecked")
    final List<T> includedRows = (List<T>) topLevelRow.getIncludedRows();
    final ImmutableMap.Builder<DocumentId, T> resultOfThisInvocation = ImmutableMap.builder();
    for (final T includedRow : includedRows) {
        resultOfThisInvocation.put(includedRow.getId(), includedRow);
        resultOfThisInvocation.putAll(extractAllIncludedRows(includedRow));
    }
    return resultOfThisInvocation.build();
}


public Map<DocumentId,T> extractAllRows(Collection<T> topLevelRows){
    final ImmutableMap.Builder<DocumentId, T> allRows = ImmutableMap.builder();
    topLevelRows.forEach(topLevelRow -> {
        allRows.put(topLevelRow.getId(), topLevelRow);
        allRows.putAll(extractAllIncludedRows(topLevelRow));
    });
    return allRows.build();
}


}