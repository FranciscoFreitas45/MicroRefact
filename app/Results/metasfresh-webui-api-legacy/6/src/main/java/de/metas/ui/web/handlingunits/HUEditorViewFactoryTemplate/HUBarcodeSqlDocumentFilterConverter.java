package de.metas.ui.web.handlingunits.HUEditorViewFactoryTemplate;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.ui.web.view.SqlViewFactory;
import org.adempiere.ad.dao.ConstantQueryFilter;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.adempiere.ad.dao.impl.InArrayQueryFilter;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.adempiere.model.PlainContextAware;
import org.adempiere.service.ISysConfigBL;
import org.compiere.model.I_AD_Tab;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import de.metas.cache.CCache;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.attribute.HUAttributeConstants;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.handlingunits.SqlHUEditorViewRepository.SqlHUEditorViewRepositoryBuilder;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewBindingFactory;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.factory.standard.LayoutFactory;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.Value;
public class HUBarcodeSqlDocumentFilterConverter implements SqlDocumentFilterConverter{

 private  String FILTER_ID;

 public  HUBarcodeSqlDocumentFilterConverter instance;

 private  String PARAM_Barcode;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts_NOTUSED,SqlDocumentFilterConverterContext context){
    final Object barcodeObj = filter.getParameter(PARAM_Barcode).getValue();
    if (barcodeObj == null) {
        throw new IllegalArgumentException("Barcode parameter is null: " + filter);
    }
    final String barcode = barcodeObj.toString().trim();
    if (barcode.isEmpty()) {
        throw new IllegalArgumentException("Barcode parameter is empty: " + filter);
    }
    final ImmutableSet<HuId> huIds = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder().setContext(PlainContextAware.newOutOfTrx()).onlyContextClient(// avoid enforcing context AD_Client_ID because it might be that we are not in a user thread (so no context)
    false).setOnlyWithBarcode(barcode).setOnlyTopLevelHUs(false).createQueryBuilder().setOption(IQueryBuilder.OPTION_Explode_OR_Joins_To_SQL_Unions).create().listIds(HuId::ofRepoId);
    if (huIds.isEmpty()) {
        return ConstantQueryFilter.of(false).getSql();
    }
    final ImmutableSet<HuId> topLevelHuIds = Services.get(IHandlingUnitsBL.class).getTopLevelHUs(huIds);
    final ISqlQueryFilter sqlQueryFilter = new InArrayQueryFilter<>(I_M_HU.COLUMNNAME_M_HU_ID, topLevelHuIds);
    final String sql = sqlQueryFilter.getSql();
    sqlParamsOut.collectAll(sqlQueryFilter);
    return sql;
}


public DocumentFilterDescriptor createDocumentFilterDescriptor(){
    final ITranslatableString barcodeCaption = Services.get(IMsgBL.class).translatable("Barcode");
    return DocumentFilterDescriptor.builder().setFilterId(FILTER_ID).setDisplayName(barcodeCaption).setParametersLayoutType(PanelLayoutType.SingleOverlayField).setFrequentUsed(false).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_Barcode).setDisplayName(barcodeCaption).setWidgetType(DocumentFieldWidgetType.Text).barcodeScannerType(BarcodeScannerType.QRCode)).build();
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


}