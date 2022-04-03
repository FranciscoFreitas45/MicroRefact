package de.metas.ui.web.dashboard;
 import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Element;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import de.metas.cache.CCache;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.base.model.I_WEBUI_KPI;
import de.metas.ui.web.base.model.I_WEBUI_KPI_Field;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
@Service
public class KPIRepository {

 private  Logger logger;

 private  IQueryBL queryBL;

 private  CCache<Integer,KPI> kpisCache;


public KPI createKPI(I_WEBUI_KPI kpiDef){
    final IModelTranslationMap trls = InterfaceWrapperHelper.getModelTranslationMap(kpiDef);
    Duration compareOffset = null;
    if (kpiDef.isGenerateComparation()) {
        final String compareOffetStr = kpiDef.getCompareOffset();
        compareOffset = Duration.parse(compareOffetStr);
    }
    return KPI.builder().setId(kpiDef.getWEBUI_KPI_ID()).setCaption(trls.getColumnTrl(I_WEBUI_KPI.COLUMNNAME_Name, kpiDef.getName())).setDescription(trls.getColumnTrl(I_WEBUI_KPI.COLUMNNAME_Description, kpiDef.getDescription())).setChartType(KPIChartType.forCode(kpiDef.getChartType())).setFields(retrieveKPIFields(kpiDef.getWEBUI_KPI_ID(), kpiDef.isGenerateComparation())).setCompareOffset(compareOffset).setTimeRangeDefaults(KPITimeRangeDefaults.builder().defaultTimeRangeFromString(kpiDef.getES_TimeRange()).defaultTimeRangeEndOffsetFromString(kpiDef.getES_TimeRange_End()).build()).setPollIntervalSec(kpiDef.getPollIntervalSec()).setESSearchIndex(kpiDef.getES_Index()).setESSearchTypes(kpiDef.getES_Type()).setESQuery(kpiDef.getES_Query()).build();
}


public Integer extractNumberPrecision(int displayType){
    if (displayType == DisplayType.Integer) {
        return 0;
    } else if (displayType == DisplayType.Amount || displayType == DisplayType.CostPrice || displayType == DisplayType.Quantity) {
        return 2;
    } else {
        return null;
    }
}


public Collection<KPI> getKPIs(Collection<Integer> kpiIds){
    return kpisCache.getAllOrLoad(kpiIds, this::retrieveKPIs);
}


public List<KPIField> retrieveKPIFields(int WEBUI_KPI_ID,boolean isComputeOffset){
    return queryBL.createQueryBuilder(I_WEBUI_KPI_Field.class, Env.getCtx(), ITrx.TRXNAME_None).addEqualsFilter(I_WEBUI_KPI_Field.COLUMN_WEBUI_KPI_ID, WEBUI_KPI_ID).addOnlyActiveRecordsFilter().orderBy().addColumn(I_WEBUI_KPI_Field.COLUMN_WEBUI_KPI_Field_ID).endOrderBy().create().stream(I_WEBUI_KPI_Field.class).map(kpiField -> createKPIField(kpiField, isComputeOffset)).collect(GuavaCollectors.toImmutableList());
}


public KPI getKPI(int id){
    final KPI kpi = getKPIOrNull(id);
    if (kpi == null) {
        throw new EntityNotFoundException("KPI (id=" + id + ")");
    }
    return kpi;
}


public KPIField createKPIField(I_WEBUI_KPI_Field kpiFieldDef,boolean isComputeOffset){
    final I_AD_Element adElement = kpiFieldDef.getAD_Element();
    final String elementColumnName = adElement.getColumnName();
    Check.assumeNotNull(elementColumnName, "The element {} does not have a column name set", adElement);
    final String fieldName = elementColumnName;
    // 
    // Extract field caption and description
    final IModelTranslationMap kpiFieldDefTrl = InterfaceWrapperHelper.getModelTranslationMap(kpiFieldDef);
    final ITranslatableString caption;
    final ITranslatableString description;
    if (Check.isEmpty(kpiFieldDef.getName(), true)) {
        final IModelTranslationMap adElementTrl = InterfaceWrapperHelper.getModelTranslationMap(adElement);
        caption = adElementTrl.getColumnTrl(I_AD_Element.COLUMNNAME_Name, adElement.getName());
        description = adElementTrl.getColumnTrl(I_AD_Element.COLUMNNAME_Description, adElement.getDescription());
    } else {
        caption = kpiFieldDefTrl.getColumnTrl(I_WEBUI_KPI_Field.COLUMNNAME_Name, kpiFieldDef.getName());
        description = TranslatableStrings.empty();
    }
    // 
    // Extract offset field's caption and description
    final ITranslatableString offsetCaption;
    if (!isComputeOffset) {
        offsetCaption = TranslatableStrings.empty();
    } else if (Check.isEmpty(kpiFieldDef.getOffsetName(), true)) {
        offsetCaption = caption;
    } else {
        offsetCaption = kpiFieldDefTrl.getColumnTrl(I_WEBUI_KPI_Field.COLUMNNAME_OffsetName, kpiFieldDef.getOffsetName());
    }
    return KPIField.builder().setFieldName(fieldName).setGroupBy(kpiFieldDef.isGroupBy()).setCaption(caption).setOffsetCaption(offsetCaption).setDescription(description).setUnit(kpiFieldDef.getUOMSymbol()).setValueType(KPIFieldValueType.fromDisplayType(kpiFieldDef.getAD_Reference_ID())).setNumberPrecision(extractNumberPrecision(kpiFieldDef.getAD_Reference_ID())).setColor(kpiFieldDef.getColor()).setESPath(kpiFieldDef.getES_FieldPath()).build();
}


public void invalidateKPI(int id){
    kpisCache.remove(id);
}


public Map<Integer,KPI> retrieveKPIs(Collection<Integer> kpiIds){
    return queryBL.createQueryBuilder(I_WEBUI_KPI.class).addInArrayFilter(I_WEBUI_KPI.COLUMN_WEBUI_KPI_ID, kpiIds).create().stream(I_WEBUI_KPI.class).map(kpiDef -> {
        try {
            return createKPI(kpiDef);
        } catch (final Exception ex) {
            logger.warn("Failed creating KPI for {}", kpiDef, ex);
            return null;
        }
    }).filter(kpi -> kpi != null).collect(GuavaCollectors.toImmutableMapByKey(KPI::getId));
}


public KPI getKPIOrNull(int WEBUI_KPI_ID){
    if (WEBUI_KPI_ID <= 0) {
        return null;
    }
    return kpisCache.getOrLoad(WEBUI_KPI_ID, () -> {
        final I_WEBUI_KPI kpiDef = InterfaceWrapperHelper.create(Env.getCtx(), WEBUI_KPI_ID, I_WEBUI_KPI.class, ITrx.TRXNAME_None);
        if (kpiDef == null) {
            return null;
        }
        return createKPI(kpiDef);
    });
}


}