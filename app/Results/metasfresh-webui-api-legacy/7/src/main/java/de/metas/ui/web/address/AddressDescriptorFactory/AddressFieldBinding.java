package de.metas.ui.web.address.AddressDescriptorFactory;
 import java.util.function.BiConsumer;
import java.util.function.Function;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ISysConfigBL;
import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Location;
import org.compiere.model.I_C_Postal;
import org.compiere.model.I_C_Region;
import org.springframework.stereotype.Component;
import de.metas.cache.CCache;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.location.CountryId;
import de.metas.location.ICountryDAO;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.Services;
public class AddressFieldBinding implements DocumentFieldDataBindingDescriptor{

 private  String columnName;

 private  boolean mandatory;

 private  Function<I_C_Location,Object> readMethod;

 private  BiConsumer<I_C_Location,IDocumentFieldView> writeMethod;


public Object readValue(I_C_Location locationRecord){
    return readMethod.apply(locationRecord);
}


public AddressFieldBinding internalField(String columnName){
    final boolean mandatory = false;
    final Function<I_C_Location, Object> readMethod = (location) -> null;
    final BiConsumer<I_C_Location, IDocumentFieldView> writeMethod = (toLocationRecord, fromField) -> {
    };
    return new AddressFieldBinding(columnName, mandatory, readMethod, writeMethod);
}


public Object readValue_C_Region_ID(I_C_Location locationRecord){
    final I_C_Region region = locationRecord.getC_Region();
    if (region != null && region.getC_Region_ID() > 0) {
        final I_C_Region regionTrl = InterfaceWrapperHelper.translate(region, I_C_Region.class);
        return IntegerLookupValue.of(regionTrl.getC_Region_ID(), regionTrl.getName());
    }
    final String regionName = locationRecord.getRegionName();
    if (!Check.isEmpty(regionName, true)) {
        return IntegerLookupValue.of(-1, regionName);
    }
    return null;
}


public void writeValue_City(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setCity(fromField.getValueAs(String.class));
}


@Override
public String getColumnName(){
    return columnName;
}


public void writeValue_C_City_ID(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    final IntegerLookupValue city = fromField.getValueAs(IntegerLookupValue.class);
    if (city == null) {
        toLocationRecord.setC_City_ID(-1);
    } else if (city.getIdAsInt() <= 0) {
        toLocationRecord.setC_City_ID(-1);
        toLocationRecord.setCity(city.getDisplayName());
    } else {
        toLocationRecord.setC_City_ID(city.getIdAsInt());
        toLocationRecord.setCity(city.getDisplayName());
    }
}


public void writeValue_C_Country_ID(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    final IntegerLookupValue country = fromField.getValueAs(IntegerLookupValue.class);
    if (country == null) {
        toLocationRecord.setC_Country_ID(-1);
    } else if (country.getIdAsInt() <= 0) {
        toLocationRecord.setC_Country_ID(-1);
    } else {
        toLocationRecord.setC_Country_ID(country.getIdAsInt());
    }
}


public void writeValue_C_Postal_ID(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    final IntegerLookupValue postalLookupValue = fromField.getValueAs(IntegerLookupValue.class);
    final int postalId = postalLookupValue != null ? postalLookupValue.getIdAsInt() : -1;
    if (postalId <= 0) {
        toLocationRecord.setC_Postal_ID(-1);
        toLocationRecord.setPostal(null);
        toLocationRecord.setCity(null);
        toLocationRecord.setC_City_ID(-1);
    } else {
        final I_C_Postal postalRecord = InterfaceWrapperHelper.load(postalId, I_C_Postal.class);
        toLocationRecord.setC_Postal_ID(postalRecord.getC_Postal_ID());
        toLocationRecord.setPostal(postalRecord.getPostal());
        toLocationRecord.setPostal_Add(postalRecord.getPostal_Add());
        toLocationRecord.setC_City_ID(postalRecord.getC_City_ID());
        toLocationRecord.setCity(postalRecord.getCity());
        toLocationRecord.setC_Country_ID(postalRecord.getC_Country_ID());
        toLocationRecord.setC_Region_ID(postalRecord.getC_Region_ID());
        toLocationRecord.setRegionName(postalRecord.getRegionName());
    }
}


public void writeValue_C_Region_ID(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    final IntegerLookupValue region = fromField.getValueAs(IntegerLookupValue.class);
    if (region == null) {
        toLocationRecord.setC_Region_ID(-1);
    } else if (region.getIdAsInt() <= 0) {
        toLocationRecord.setC_Region_ID(-1);
        toLocationRecord.setRegionName(region.getDisplayName());
    } else {
        toLocationRecord.setC_Region_ID(region.getIdAsInt());
        toLocationRecord.setRegionName(region.getDisplayName());
    }
}


public void writeValue_Postal(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setPostal(fromField.getValueAs(String.class));
}


public void writeValue_Address2(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setAddress2(fromField.getValueAs(String.class));
}


public void writeValue_Address1(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setAddress1(fromField.getValueAs(String.class));
}


public Object readValue_C_Country_ID(I_C_Location locationRecord){
    final CountryId countryId = CountryId.ofRepoIdOrNull(locationRecord.getC_Country_ID());
    if (countryId != null) {
        final ITranslatableString displayName = Services.get(ICountryDAO.class).getCountryNameById(countryId);
        final ITranslatableString helpText = null;
        return IntegerLookupValue.of(countryId, displayName, helpText);
    } else {
        return null;
    }
}


public void writeValue(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    writeMethod.accept(toLocationRecord, fromField);
}


@Override
public boolean isMandatory(){
    return mandatory;
}


public void writeValue_Address4(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setAddress4(fromField.getValueAs(String.class));
}


public void writeValue_Address3(I_C_Location toLocationRecord,IDocumentFieldView fromField){
    toLocationRecord.setAddress3(fromField.getValueAs(String.class));
}


}