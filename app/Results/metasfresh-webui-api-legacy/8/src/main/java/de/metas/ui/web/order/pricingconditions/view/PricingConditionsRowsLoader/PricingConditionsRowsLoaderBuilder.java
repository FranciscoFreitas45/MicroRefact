package de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowsLoader;
 import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import java.util.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSetMultimap;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.BPartnerType;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.inout.IInOutDAO;
import de.metas.lang.SOTrx;
import de.metas.logging.LogManager;
import de.metas.money.Money;
import de.metas.order.OrderLineId;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PricingConditions;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.pricing.conditions.PricingConditionsBreakMatchCriteria;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.pricing.conditions.service.IPricingConditionsRepository;
import de.metas.product.ProductCategoryId;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.util.Services;
import de.metas.util.lang.Percent;
import lombok.Builder;
import lombok.NonNull;
public class PricingConditionsRowsLoaderBuilder {


public PricingConditionsRowData load(){
    return build().load();
}


}