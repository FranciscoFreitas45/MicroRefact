package de.metas.ui.web.view.ViewRowIdsOrderedSelections;
 import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@FunctionalInterface
public interface ViewRowIdsOrderedSelectionFactory {


public ViewRowIdsOrderedSelection create(ViewRowIdsOrderedSelection defaultSelection,DocumentQueryOrderByList orderBys)
;

}