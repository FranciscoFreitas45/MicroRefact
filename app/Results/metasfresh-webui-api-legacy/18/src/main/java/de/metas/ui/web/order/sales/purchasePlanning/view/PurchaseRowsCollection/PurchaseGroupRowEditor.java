package de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseRowsCollection;
 import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
@FunctionalInterface
public interface PurchaseGroupRowEditor {


public void edit(PurchaseRow editableGroupRow,PurchaseRowId includedRowId,PurchaseRowChangeRequest request)
;

}