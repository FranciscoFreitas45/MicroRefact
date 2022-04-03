package de.metas.ui.web.document.geo_location;
 import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ISysConfigBL;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Location;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.document.archive.model.I_C_BPartner;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsConstants;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProviderFactory;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.geo_location.GeoLocationDocumentDescriptor.LocationColumnNameType;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class GeoLocationDocumentService implements DocumentFilterDescriptorsProviderFactory{

 private  IMsgBL msgBL;

 private  ISysConfigBL sysConfigBL;

 private  AdMessageKey MSG_FILTER_CAPTION;

 private  String SYS_CONFIG_ENABLE_GEO_LOCATION_SEARCH;

 private  GeoLocationDocumentDescriptor DESCRIPTOR_FOR_LocationId;

 private  GeoLocationDocumentDescriptor DESCRIPTOR_FOR_BPartnerLocationId;

 private  GeoLocationDocumentDescriptor DESCRIPTOR_FOR_BPartnerId;


@Override
@Nullable
public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId_NOTUSED,String tableName,Collection<DocumentFieldDescriptor> fields){
    if (tableName == null) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    if (fields == null || fields.isEmpty()) {
        return null;
    }
    final ImmutableSet<String> fieldNames = extractFieldNames(fields);
    final GeoLocationDocumentDescriptor descriptor = getGeoLocationDocumentDescriptorOrNull(fieldNames);
    if (descriptor == null) {
        return NullDocumentFilterDescriptorsProvider.instance;
    }
    return ImmutableDocumentFilterDescriptorsProvider.of(createDocumentFilterDescriptor(descriptor));
}


public DocumentFilterDescriptor createDocumentFilterDescriptor(GeoLocationDocumentDescriptor descriptor){
    final ITranslatableString caption = msgBL.getTranslatableMsgText(MSG_FILTER_CAPTION);
    return DocumentFilterDescriptor.builder().setFilterId(GeoLocationFilterConverter.FILTER_ID).setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_GEO_LOCATION).setDisplayName(caption).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_Address1).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_Address1)).setWidgetType(DocumentFieldWidgetType.Text)).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_Postal).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_Postal)).setWidgetType(DocumentFieldWidgetType.Text)).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_City).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_City)).setWidgetType(DocumentFieldWidgetType.Text)).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_CountryId).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_CountryId)).setWidgetType(DocumentFieldWidgetType.Lookup).setLookupDescriptor(SqlLookupDescriptor.searchInTable(I_C_Country.Table_Name).provideForFilter())).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_Distance).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_Distance)).setWidgetType(DocumentFieldWidgetType.Integer)).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(GeoLocationFilterConverter.PARAM_VisitorsAddress).setDisplayName(msgBL.translatable(GeoLocationFilterConverter.PARAM_VisitorsAddress)).setWidgetType(DocumentFieldWidgetType.YesNo)).addInternalParameter(GeoLocationFilterConverter.PARAM_LocationAreaSearchDescriptor, descriptor).build();
}


@Nullable
public GeoLocationDocumentDescriptor getGeoLocationDocumentDescriptor(Set<String> fieldNames){
    final GeoLocationDocumentDescriptor descriptor = getGeoLocationDocumentDescriptorOrNull(fieldNames);
    if (descriptor == null) {
        throw new AdempiereException("No geo-location support for " + fieldNames);
    }
    return descriptor;
}


public ImmutableSet<String> extractFieldNames(Collection<DocumentFieldDescriptor> fields){
    if (fields.isEmpty()) {
        return ImmutableSet.of();
    }
    return fields.stream().map(DocumentFieldDescriptor::getFieldName).collect(ImmutableSet.toImmutableSet());
}


public boolean hasGeoLocationSupport(Set<String> fieldNames){
    return getGeoLocationDocumentDescriptorOrNull(fieldNames) != null;
}


public boolean isActive(){
    return sysConfigBL.getBooleanValue(SYS_CONFIG_ENABLE_GEO_LOCATION_SEARCH, Boolean.TRUE);
}


@NonNull
public DocumentFilter createDocumentFilter(DocumentEntityDescriptor entityDescriptor,GeoLocationDocumentQuery query){
    final ImmutableSet<String> fieldNames = extractFieldNames(entityDescriptor.getFields());
    final GeoLocationDocumentDescriptor descriptor = getGeoLocationDocumentDescriptor(fieldNames);
    return DocumentFilter.builder().setFilterId(GeoLocationFilterConverter.FILTER_ID).addInternalParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_LocationAreaSearchDescriptor, descriptor)).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_Address1, query.getAddress1())).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_City, query.getCity())).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_Postal, query.getPostal())).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_CountryId, query.getCountry())).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_Distance, query.getDistanceInKm())).addParameter(DocumentFilterParam.ofNameEqualsValue(GeoLocationFilterConverter.PARAM_VisitorsAddress, query.isVisitorsAddress())).build();
}


@Nullable
public GeoLocationDocumentDescriptor getGeoLocationDocumentDescriptorOrNull(Set<String> fieldNames){
    if (fieldNames.contains(DESCRIPTOR_FOR_LocationId.getLocationColumnName())) {
        return DESCRIPTOR_FOR_LocationId;
    } else if (fieldNames.contains(DESCRIPTOR_FOR_BPartnerLocationId.getLocationColumnName())) {
        return DESCRIPTOR_FOR_BPartnerLocationId;
    } else if (fieldNames.contains(DESCRIPTOR_FOR_BPartnerId.getLocationColumnName())) {
        return DESCRIPTOR_FOR_BPartnerId;
    } else {
        return null;
    }
}


}