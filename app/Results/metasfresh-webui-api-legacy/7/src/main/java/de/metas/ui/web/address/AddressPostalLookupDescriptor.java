package de.metas.ui.web.address;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.DBException;
import org.compiere.model.I_C_Location;
import org.compiere.model.I_C_Postal;
import org.compiere.util.DB;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache.CCacheStats;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext.Builder;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.util.Check;
import lombok.NonNull;
public class AddressPostalLookupDescriptor implements LookupDataSourceFetcher,LookupDescriptor{

 private  Optional<String> LookupTableName;

 private  String CACHE_PREFIX;

 private  String CONTEXT_LookupTableName;

 private  AddressCountryLookupDescriptor countryLookup;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of();
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    // 
    // Determine what we will filter
    final String filter = evalCtx.getFilter();
    String filterUC;
    final int limit;
    final int offset = evalCtx.getOffset(0);
    if (filter == LookupDataSourceContext.FILTER_Any) {
        // N/A
        filterUC = "%";
        limit = evalCtx.getLimit(Integer.MAX_VALUE);
    } else if (Check.isEmpty(filter, true)) {
        return LookupValuesList.EMPTY;
    } else {
        filterUC = filter.trim().toUpperCase();
        if (!filterUC.startsWith("%")) {
            filterUC = "%" + filterUC;
        }
        if (!filterUC.endsWith("%")) {
            filterUC = filterUC + "%";
        }
        limit = evalCtx.getLimit(100);
    }
    final String sql = "SELECT " + "\n " + I_C_Postal.COLUMNNAME_C_Postal_ID + "\n, " + I_C_Postal.COLUMNNAME_Postal + "\n, " + I_C_Postal.COLUMNNAME_City + "\n, " + I_C_Postal.COLUMNNAME_Township + "\n, " + I_C_Postal.COLUMNNAME_C_Country_ID + "\n FROM " + I_C_Postal.Table_Name + "\n WHERE " + "\n " + I_C_Postal.COLUMNNAME_Postal + " ILIKE ?" + "\n OR " + I_C_Postal.COLUMNNAME_City + " ILIKE ?" + "\n ORDER BY " + I_C_Postal.COLUMNNAME_City + ", " + I_C_Postal.COLUMNNAME_Postal + ", " + I_C_Postal.COLUMNNAME_C_Postal_ID + "\n LIMIT ? OFFSET ?";
    final Object[] sqlParams = new Object[] { filterUC, filterUC, limit, offset };
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, ITrx.TRXNAME_None);
        DB.setParameters(pstmt, sqlParams);
        rs = pstmt.executeQuery();
        final List<LookupValue> lookupValues = new ArrayList<>();
        while (rs.next()) {
            final int postalId = rs.getInt(I_C_Postal.COLUMNNAME_C_Postal_ID);
            final String postal = rs.getString(I_C_Postal.COLUMNNAME_Postal);
            final String city = rs.getString(I_C_Postal.COLUMNNAME_City);
            final String township = rs.getString(I_C_Postal.COLUMNNAME_Township);
            final int countryId = rs.getInt(I_C_Postal.COLUMNNAME_C_Country_ID);
            final LookupValue countryLookupValue = countryLookup.getLookupValueById(countryId);
            lookupValues.add(buildPostalLookupValue(postalId, postal, city, township, countryLookupValue.getDisplayNameTrl()));
        }
        return LookupValuesList.fromCollection(lookupValues);
    } catch (final SQLException ex) {
        throw new DBException(ex, sql, sqlParams);
    } finally {
        DB.close(rs, pstmt);
    }
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.lookup;
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


public IntegerLookupValue buildPostalLookupValue(int postalId,String postal,String city,String township,ITranslatableString countryName){
    final ITranslatableString displayName = TranslatableStrings.join("", postal, " ", city, " ", township, " (", countryName, ")");
    return IntegerLookupValue.of(postalId, displayName, null);
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final int id = evalCtx.getIdToFilterAsInt(-1);
    if (id <= 0) {
        throw new IllegalStateException("No ID provided in " + evalCtx);
    }
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException();
}


@Override
public String getCachePrefix(){
    return CACHE_PREFIX;
}


@Override
public boolean isHighVolume(){
    return true;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public boolean isNumericKey(){
    return true;
}


@Override
public void cacheInvalidate(){
    countryLookup.cacheInvalidate();
}


@Override
public boolean isCached(){
    // not cached but returning true to avoid caching
    return true;
}


@Override
public Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName).putFilterById(id);
}


@Override
public boolean hasParameters(){
    return false;
}


@Override
public Optional<String> getLookupTableName(){
    return LookupTableName;
}


public IntegerLookupValue getLookupValueFromLocation(I_C_Location locationRecord){
    final I_C_Postal postalRecord = locationRecord.getC_Postal();
    if (postalRecord == null || postalRecord.getC_Postal_ID() <= 0) {
        return null;
    }
    final LookupValue countryLookupValue = countryLookup.getLookupValueById(postalRecord.getC_Country_ID());
    return buildPostalLookupValue(postalRecord.getC_Postal_ID(), postalRecord.getPostal(), postalRecord.getCity(), postalRecord.getTownship(), countryLookupValue.getDisplayNameTrl());
}


@Override
public Set<String> getDependsOnFieldNames(){
    return ImmutableSet.of();
}


@Override
public Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName);
}


}