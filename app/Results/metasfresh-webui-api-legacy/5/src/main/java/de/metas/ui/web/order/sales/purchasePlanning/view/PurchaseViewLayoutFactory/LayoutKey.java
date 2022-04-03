package de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseViewLayoutFactory;
 import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
@lombok.Value
@lombok.Builder
public class LayoutKey {

 private WindowId windowId;

 private JSONViewDataType viewDataType;


}