package de.metas.ui.web.inout.process;
 import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.IHUShipperTransportationBL;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.inout.IHUInOutDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_InOut;
import de.metas.handlingunits.model.I_M_Package_HU;
import de.metas.inout.IInOutDAO;
import de.metas.inout.InOutId;
import de.metas.shipping.api.IShipperTransportationDAO;
import de.metas.shipping.model.I_M_ShipperTransportation;
import de.metas.shipping.model.I_M_ShippingPackage;
import de.metas.shipping.model.ShipperTransportationId;
import de.metas.util.GuavaCollectors;
import de.metas.util.Loggables;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.IQuery;
import org.compiere.model.I_M_Package;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
@Service
public class InOutToTransportationOrderService {


public ImmutableList<I_M_InOut> retrieveAllInOuts(ImmutableList<InOutId> inOutIds){
    final ImmutableList.Builder<I_M_InOut> result = ImmutableList.builder();
    final IInOutDAO inOutDAO = Services.get(IInOutDAO.class);
    for (final InOutId io : inOutIds) {
        // Object.requireNonNull used just to fix a warning
        result.add(Objects.requireNonNull(inOutDAO.getById(io, I_M_InOut.class)));
    }
    return result.build();
}


public void addShipmentsToTransportationOrder(ShipperTransportationId transportationOrderId,ImmutableList<InOutId> inOutIds){
    final IShipperTransportationDAO shipperTransportationDAO = Services.get(IShipperTransportationDAO.class);
    final I_M_ShipperTransportation transportationOrder = shipperTransportationDAO.retrieve(transportationOrderId);
    final IHUShipperTransportationBL huShipperTransportationBL = Services.get(IHUShipperTransportationBL.class);
    final ImmutableList<I_M_InOut> selectedInOuts = retrieveAllInOuts(inOutIds);
    final ShipperTransportationId shipperTransportationId = ShipperTransportationId.ofRepoId(Objects.requireNonNull(transportationOrder).getM_ShipperTransportation_ID());
    for (final I_M_InOut inOut : selectedInOuts) {
        final List<I_M_HU> husToTest = retrieveAllNonAnonymousHUs(inOut);
        if (husToTest.isEmpty()) {
            if (inOut.getM_ShipperTransportation_ID() != 0) {
                continue;
            }
            huShipperTransportationBL.addInOutWithoutHUToShipperTransportation(shipperTransportationId, ImmutableList.of(inOut));
            InterfaceWrapperHelper.save(inOut);
            Loggables.addLog("M_InOut={} added to M_ShipperTransportation_ID={}", inOut.getM_InOut_ID(), shipperTransportationId);
        } else {
            final ImmutableList<I_M_HU> husFiltered = selectOnlyHUsWithoutShipperTransportation(inOut);
            final boolean anyAdded = !huShipperTransportationBL.addHUsToShipperTransportation(shipperTransportationId, husFiltered).isEmpty();
            if (anyAdded) {
                // only link the InOut and the ShipperTransportation if any HUs were added.
                inOut.setM_ShipperTransportation_ID(shipperTransportationId.getRepoId());
                InterfaceWrapperHelper.save(inOut);
                Loggables.addLog("HUs added to M_ShipperTransportation_ID={}", shipperTransportationId);
            }
        }
    }
}


@NonNull
public ImmutableList<I_M_HU> selectOnlyHUsWithoutShipperTransportation(I_M_InOut inOut){
    final List<I_M_HU> huList = retrieveAllNonAnonymousHUs(inOut);
    final ImmutableList<Integer> allHuIds = huList.stream().map(I_M_HU::getM_HU_ID).collect(GuavaCollectors.toImmutableList());
    final ImmutableList<Integer> alreadyInHUs = findAllHUsWithAShipperTransportation(allHuIds);
    return huList.stream().filter(it -> !alreadyInHUs.contains(it.getM_HU_ID())).collect(GuavaCollectors.toImmutableList());
}


public ImmutableList<I_M_HU> retrieveAllNonAnonymousHUs(org.compiere.model.I_M_InOut inOut){
    final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
    final List<I_M_HU> allHUs = Services.get(IHUInOutDAO.class).retrieveHandlingUnits(inOut);
    final ImmutableList.Builder<I_M_HU> result = ImmutableList.builder();
    for (final I_M_HU hu : allHUs) {
        if (!handlingUnitsBL.isAnonymousHuPickedOnTheFly(hu)) {
            result.add(hu);
        }
    }
    return result.build();
}


@NonNull
public ImmutableList<Integer> findAllHUsWithAShipperTransportation(ImmutableList<Integer> allHuIds){
    final IQueryBL queryBL = Services.get(IQueryBL.class);
    final IQuery<I_M_ShipperTransportation> subQuery__M_ShipperTransportation = queryBL.createQueryBuilder(I_M_ShipperTransportation.class).create();
    final IQuery<I_M_ShippingPackage> subQuery__M_ShippingPackage__M_ShipperTransportation = queryBL.createQueryBuilder(I_M_ShippingPackage.class).addInSubQueryFilter().matchingColumnNames(I_M_ShippingPackage.COLUMNNAME_M_ShipperTransportation_ID, I_M_ShipperTransportation.COLUMNNAME_M_ShipperTransportation_ID).subQuery(subQuery__M_ShipperTransportation).end().create();
    final IQuery<I_M_Package> subQuery__M_Package__M_ShippingPackage__M_ShipperTransportation = queryBL.createQueryBuilder(I_M_Package.class).addInSubQueryFilter().matchingColumnNames(I_M_Package.COLUMNNAME_M_Package_ID, I_M_ShippingPackage.COLUMNNAME_M_Package_ID).subQuery(subQuery__M_ShippingPackage__M_ShipperTransportation).end().create();
    final List<Integer> list = queryBL.createQueryBuilder(I_M_Package_HU.class).addInArrayFilter(I_M_Package_HU.COLUMNNAME_M_HU_ID, allHuIds).addInSubQueryFilter().matchingColumnNames(I_M_Package_HU.COLUMNNAME_M_Package_ID, I_M_Package.COLUMNNAME_M_Package_ID).subQuery(subQuery__M_Package__M_ShippingPackage__M_ShipperTransportation).end().andCollect(I_M_Package_HU.COLUMN_M_HU_ID, I_M_HU.class).create().listIds();
    return ImmutableList.copyOf(list);
}


}