package de.metas.ui.web.order.pricingconditions.view.PricingConditionsViewFactoryTemplate;
 import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.order.pricingconditions.process.PricingConditionsView_CopyRowToEditable;
import de.metas.ui.web.order.pricingconditions.process.PricingConditionsView_SaveEditableRow;
import de.metas.ui.web.order.pricingconditions.process.WEBUI_SalesOrder_PricingConditionsView_Launcher;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowsLoader.PricingConditionsRowsLoaderBuilder;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@lombok.Value(staticConstructor = "of")
public class ViewLayoutKey {

 private WindowId windowId;

 private JSONViewDataType viewDataType;


}