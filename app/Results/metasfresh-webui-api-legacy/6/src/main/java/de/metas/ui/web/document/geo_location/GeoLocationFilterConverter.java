package de.metas.ui.web.document.geo_location;
 import java.util.Optional;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_C_Location;
import org.slf4j.Logger;
import com.jgoodies.common.base.Objects;
import de.metas.location.CountryId;
import de.metas.location.ICountryDAO;
import de.metas.location.geocoding.GeoCoordinatesRequest;
import de.metas.location.geocoding.GeocodingService;
import de.metas.location.geocoding.GeographicalCoordinates;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.document.geo_location.GeoLocationDocumentDescriptor.LocationColumnNameType;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.NonNull;
public class GeoLocationFilterConverter implements SqlDocumentFilterConverter{

 public  GeoLocationFilterConverter instance;

 private  String MSG_NoCoordinatesFoundForTheGivenLocation;

 static  String FILTER_ID;

 static  String PARAM_LocationAreaSearchDescriptor;

 static  String PARAM_Address1;

 static  String PARAM_City;

 static  String PARAM_Postal;

 static  String PARAM_CountryId;

 static  String PARAM_Distance;

 static  String PARAM_VisitorsAddress;

 private  Logger logger;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context_NOTUSED){
    final GeoLocationDocumentDescriptor descriptor = filter.getParameterValueAs(PARAM_LocationAreaSearchDescriptor);
    if (descriptor == null) {
        // shall not happen
        logger.warn("Cannot convert filter to SQL because parameter {} is not set: {}", PARAM_LocationAreaSearchDescriptor, filter);
        return null;
    }
    final GeographicalCoordinates addressCoordinates = getAddressCoordinates(filter).orElse(null);
    if (addressCoordinates == null) {
        logger.warn("Cannot convert filter to SQL because geo coordinates not found for {}", filter);
        throw new AdempiereException(MSG_NoCoordinatesFoundForTheGivenLocation).markAsUserValidationError();
    }
    final String visitorAddressQuery = constructVisitorAddressQuery(filter);
    final int distanceInKm = extractDistanceInKm(filter);
    if (LocationColumnNameType.LocationId.equals(descriptor.getType())) {
        return "EXISTS (" + " SELECT 1" + " FROM " + I_C_Location.Table_Name + " l" + " WHERE " + " l." + I_C_Location.COLUMNNAME_C_Location_ID + "=" + sqlOpts.getTableNameOrAlias() + "." + descriptor.getLocationColumnName() + // no visitorAddressQuery here
        " AND " + sqlGeographicalDistance(sqlParamsOut, "l", addressCoordinates, distanceInKm) + ")";
    } else if (LocationColumnNameType.BPartnerLocationId.equals(descriptor.getType())) {
        return "EXISTS (" + " SELECT 1" + " FROM " + I_C_BPartner_Location.Table_Name + " bpl" + " INNER JOIN " + I_C_Location.Table_Name + " l ON l." + I_C_Location.COLUMNNAME_C_Location_ID + "=bpl." + I_C_BPartner_Location.COLUMNNAME_C_Location_ID + " WHERE " + " bpl." + I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_ID + "=" + sqlOpts.getTableNameOrAlias() + "." + descriptor.getLocationColumnName() + visitorAddressQuery + " AND " + sqlGeographicalDistance(sqlParamsOut, "l", addressCoordinates, distanceInKm) + ")";
    } else if (LocationColumnNameType.BPartnerId.equals(descriptor.getType())) {
        return "EXISTS (" + " SELECT 1" + " FROM " + I_C_BPartner.Table_Name + " bp" + " INNER JOIN " + I_C_BPartner_Location.Table_Name + " bpl ON bpl." + I_C_BPartner_Location.COLUMNNAME_C_BPartner_ID + "=bp." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " INNER JOIN " + I_C_Location.Table_Name + " l ON l." + I_C_Location.COLUMNNAME_C_Location_ID + "=bpl." + I_C_BPartner_Location.COLUMNNAME_C_Location_ID + " WHERE " + " bp." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + "=" + sqlOpts.getTableNameOrAlias() + "." + descriptor.getLocationColumnName() + " AND bpl." + I_C_BPartner_Location.COLUMNNAME_IsActive + "='Y'" + visitorAddressQuery + " AND " + sqlGeographicalDistance(sqlParamsOut, "l", addressCoordinates, distanceInKm) + ")";
    } else {
        throw new AdempiereException("Unknown " + descriptor.getType());
    }
}


public int extractDistanceInKm(DocumentFilter filter){
    final int distanceInKm = filter.getParameterValueAsInt(PARAM_Distance, -1);
    return Math.max(distanceInKm, 0);
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


@NonNull
@SuppressWarnings("SameParameterValue")
public String sqlGeographicalDistance(SqlParamsCollector sqlParamsOut,String locationTableAlias,GeographicalCoordinates addressCoordinates,int distanceInKm){
    return "geographical_distance(" + // 
    locationTableAlias + "." + I_C_Location.COLUMNNAME_Latitude + "," + locationTableAlias + "." + I_C_Location.COLUMNNAME_Longitude + // 
    "," + sqlParamsOut.placeholder(addressCoordinates.getLatitude()) + "," + sqlParamsOut.placeholder(addressCoordinates.getLongitude()) + // 
    ") <= " + sqlParamsOut.placeholder(distanceInKm);
}


public Optional<GeographicalCoordinates> getAddressCoordinates(DocumentFilter filter){
    final GeoCoordinatesRequest request = createGeoCoordinatesRequest(filter).orElse(null);
    if (request == null) {
        return Optional.empty();
    }
    final GeocodingService geocodingService = SpringContextHolder.instance.getBean(GeocodingService.class);
    return geocodingService.findBestCoordinates(request);
}


@NonNull
public String constructVisitorAddressQuery(DocumentFilter filter){
    final boolean isVisitorAddress = filter.getParameterValueAsBoolean(PARAM_VisitorsAddress, false);
    final String visitorAddressQuery;
    if (isVisitorAddress) {
        visitorAddressQuery = " AND bpl." + I_C_BPartner_Location.COLUMNNAME_VisitorsAddress + "='Y' ";
    } else {
        visitorAddressQuery = "";
    }
    return visitorAddressQuery;
}


public Optional<GeoCoordinatesRequest> createGeoCoordinatesRequest(DocumentFilter filter){
    final String countryCode2 = extractCountryCode2(filter);
    if (countryCode2 == null) {
        return Optional.empty();
    }
    final GeoCoordinatesRequest request = GeoCoordinatesRequest.builder().countryCode2(countryCode2).postal(filter.getParameterValueAsString(PARAM_Postal, "")).address(filter.getParameterValueAsString(PARAM_Address1, "")).city(filter.getParameterValueAsString(PARAM_City, "")).build();
    return Optional.of(request);
}


public String extractCountryCode2(DocumentFilter filter){
    final CountryId countryId = filter.getParameterValueAsRepoIdOrNull(PARAM_CountryId, CountryId::ofRepoIdOrNull);
    if (countryId == null) {
        throw new FillMandatoryException(PARAM_CountryId);
    // return null;
    }
    return Services.get(ICountryDAO.class).retrieveCountryCode2ByCountryId(countryId);
}


}