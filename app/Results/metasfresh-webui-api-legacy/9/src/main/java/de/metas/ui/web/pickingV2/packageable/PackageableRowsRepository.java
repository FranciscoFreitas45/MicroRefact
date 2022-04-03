package de.metas.ui.web.pickingV2.packageable;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.adempiere.warehouse.WarehouseTypeId;
import org.adempiere.warehouse.api.IWarehouseDAO;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Shipper;
import org.compiere.util.Util.ArrayKey;
import org.slf4j.Logger;
import java.util.Objects;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.inoutcandidate.api.IPackagingDAO;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.inoutcandidate.api.PackageableQuery;
import de.metas.logging.LogManager;
import de.metas.money.Money;
import de.metas.money.MoneyService;
import de.metas.order.OrderId;
import de.metas.shipping.ShipperId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.pickingV2.packageable.PackageableRowsData.PackageableRowsDataBuilder;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
public class PackageableRowsRepository {

 private  Logger logger;

 private  IWarehouseDAO warehousesRepo;

 private  IPackagingDAO packageablesRepo;

 private  MoneyService moneyService;

 private  Supplier<LookupDataSource> bpartnerLookup;

 private  Supplier<LookupDataSource> shipperLookup;

 private  Supplier<LookupDataSource> userLookup;


public PackageableRow createPackageableRowNoFail(Collection<Packageable> packageables){
    try {
        return createPackageableRow(packageables);
    } catch (Exception ex) {
        logger.warn("Failed creating row from {}. Skip.", packageables, ex);
        return null;
    }
}


public PackageableRow createPackageableRow(Collection<Packageable> packageables){
    Check.assumeNotEmpty(packageables, "packageables is not empty");
    final BPartnerId customerId = Packageable.extractSingleValue(packageables, Packageable::getCustomerId).get();
    final LookupValue customer = bpartnerLookup.get().findById(customerId);
    final WarehouseTypeId warehouseTypeId = Packageable.extractSingleValue(packageables, Packageable::getWarehouseTypeId).orElse(null);
    final ITranslatableString warehouseTypeName;
    if (warehouseTypeId != null) {
        warehouseTypeName = warehousesRepo.getWarehouseTypeById(warehouseTypeId).getName();
    } else {
        warehouseTypeName = null;
    }
    final ShipperId shipperId = Packageable.extractSingleValue(packageables, Packageable::getShipperId).orElse(null);
    final LookupValue shipper = shipperLookup.get().findById(shipperId);
    final OrderId salesOrderId = Packageable.extractSingleValue(packageables, Packageable::getSalesOrderId).get();
    final String salesOrderDocumentNo = Packageable.extractSingleValue(packageables, Packageable::getSalesOrderDocumentNo).get();
    final UserId lockedByUserId = Packageable.extractSingleValue(packageables, Packageable::getLockedBy).orElse(null);
    final LookupValue lockedByUser = userLookup.get().findById(lockedByUserId);
    return PackageableRow.builder().orderId(salesOrderId).orderDocumentNo(salesOrderDocumentNo).customer(customer).warehouseTypeId(warehouseTypeId).warehouseTypeName(warehouseTypeName).lockedByUser(lockedByUser).lines(packageables.size()).shipper(shipper).lineNetAmt(buildNetAmtTranslatableString(packageables)).packageables(packageables).build();
}


public ITranslatableString buildNetAmtTranslatableString(Collection<Packageable> packageables){
    return packageables.stream().map(Packageable::getSalesOrderLineNetAmt).filter(Objects::nonNull).collect(Money.sumByCurrencyAndStream()).map(moneyService::toTranslatableString).collect(TranslatableStrings.joining(", "));
}


public PackageableQuery createPackageableQuery(DocumentFilterList filters){
    final PackageableViewFilterVO filterVO = PackageableViewFilters.extractPackageableViewFilterVO(filters);
    return PackageableQuery.builder().onlyFromSalesOrder(true).salesOrderId(filterVO.getSalesOrderId()).customerId(filterVO.getCustomerId()).warehouseTypeId(filterVO.getWarehouseTypeId()).deliveryDate(filterVO.getDeliveryDate()).preparationDate(filterVO.getPreparationDate()).shipperId(filterVO.getShipperId()).build();
}


public ArrayKey extractGroupingKey(Packageable packageable){
    return ArrayKey.of(PackageableRowId.of(packageable.getSalesOrderId(), packageable.getWarehouseTypeId()), packageable.getLockedBy());
}


public PackageableRowsDataBuilder newPackageableRowsData(){
    return PackageableRowsData.builder().repo(this);
}


public List<PackageableRow> retrieveRows(DocumentFilterList filters){
    final PackageableQuery query = createPackageableQuery(filters);
    return packageablesRepo.stream(query).collect(GuavaCollectors.toImmutableListMultimap(packageable -> extractGroupingKey(packageable))).asMap().values().stream().map(this::createPackageableRowNoFail).filter(Objects::nonNull).sorted(Comparator.comparing(PackageableRow::getPreparationDate).thenComparing(PackageableRow::getOrderDocumentNo)).collect(ImmutableList.toImmutableList());
}


}