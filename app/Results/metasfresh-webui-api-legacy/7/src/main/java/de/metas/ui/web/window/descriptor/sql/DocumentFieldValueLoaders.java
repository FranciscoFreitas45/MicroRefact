package de.metas.ui.web.window.descriptor.sql;
 import java.awt.Color;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import org.compiere.util.DisplayType;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.ColorValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.Password;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.lookup.LabelsLookup;
import de.metas.util.Check;
import de.metas.util.IColorRepository;
import de.metas.util.MFColor;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class DocumentFieldValueLoaders {

 private  Logger logger;

 private  String sqlColumnName;

 private  String sqlColumnName;

 private  String sqlColumnName;

 private  String sqlColumnName;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  String sqlColumnName;

 private  boolean encrypted;

 private  int precision;

 private  String sqlColumnName;

 private  boolean encrypted;

@NonNull
 private  String sqlColumnName;

@NonNull
 private  String sqlDisplayColumnName;

@NonNull
 private  String sqlColumnName;

@NonNull
 private  String sqlDisplayColumnName;

@NonNull
 private ITranslatableString displayName;

@NonNull
 private ITranslatableString description;

 private  String sqlColumnName;

 private  String sqlDisplayColumnName;

@NonNull
 private  String sqlColumnName;


public DocumentFieldValueLoader toPassword(String sqlColumnName,boolean encrypted){
    if (encrypted) {
        return new EncryptedPasswordDocumentFieldValueLoader(sqlColumnName);
    } else {
        return new PasswordDocumentFieldValueLoader(sqlColumnName);
    }
}


public DocumentFieldValueLoader toZonedDateTime(String sqlColumnName,boolean encrypted){
    return new ZonedDateTimeDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toLocalTime(String sqlColumnName,boolean encrypted){
    return new LocalTimeDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toLabelValues(String sqlColumnName){
    return new LabelsLookupValueDocumentFieldValueLoader(sqlColumnName);
}


@Override
public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor){
    final int adColorId = rs.getInt(sqlColumnName);
    if (adColorId <= 0) {
        return null;
    }
    final IColorRepository colorRepository = Services.get(IColorRepository.class);
    final MFColor color = colorRepository.getColorById(adColorId);
    if (color == null) {
        return null;
    }
    final Color awtColor = color.toFlatColor().getFlatColor();
    return ColorValue.ofRGB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
}


public DocumentFieldValueLoader toColor(String sqlColumnName){
    return new ColorDocumentFieldValueLoader(sqlColumnName);
}


public DocumentFieldValueLoader toBigDecimal(String sqlColumnName,boolean encrypted,Integer precision){
    if (precision != null) {
        return new BigDecimalWithPrecisionDocumentFieldValueLoader(sqlColumnName, encrypted, precision);
    } else {
        return new BigDecimalDocumentFieldValueLoader(sqlColumnName, encrypted);
    }
}


public DocumentFieldValueLoader toInstant(String sqlColumnName,boolean encrypted){
    return new InstantDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toInteger(String sqlColumnName,boolean encrypted){
    return new IntegerDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DisplayNameAndDescription extractDisplayNameAndDescription(ResultSet rs,String sqlDisplayColumnName,String adLanguage){
    final ITranslatableString displayName;
    final ITranslatableString description;
    final Array array = rs.getArray(sqlDisplayColumnName);
    if (array == null) {
        displayName = TranslatableStrings.empty();
        description = TranslatableStrings.empty();
    } else {
        final String[] nameAndDescription = (String[]) array.getArray();
        displayName = TranslatableStrings.singleLanguage(adLanguage, nameAndDescription[0]);
        final boolean hasDescription = nameAndDescription.length > 1;
        if (hasDescription) {
            description = TranslatableStrings.singleLanguage(adLanguage, nameAndDescription[1]);
        } else {
            description = TranslatableStrings.empty();
        }
    }
    return new DisplayNameAndDescription(displayName, description);
}


public DocumentFieldValueLoader toLookupValue(String sqlColumnName,String sqlDisplayColumnName,boolean numericKey){
    if (Check.isEmpty(sqlDisplayColumnName, true)) {
        throw new IllegalArgumentException("sqlDisplayColumnName shall not be null or empty");
    }
    if (numericKey) {
        return new IntegerLookupValueDocumentFieldValueLoader(sqlColumnName, sqlDisplayColumnName);
    } else {
        return new StringLookupValueDocumentFieldValueLoader(sqlColumnName, sqlDisplayColumnName);
    }
}


public DocumentFieldValueLoader toLocalDate(String sqlColumnName,boolean encrypted){
    return new LocalDateDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toBoolean(String sqlColumnName,boolean encrypted){
    return new BooleanDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toByteArray(String sqlColumnName,boolean encrypted){
    return new ByteArrayDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toJULDate(String sqlColumnName,boolean encrypted){
    return new JULDateDocumentFieldValueLoader(sqlColumnName, encrypted);
}


public DocumentFieldValueLoader toString(String sqlColumnName,boolean encrypted){
    if (encrypted) {
        return new EncryptedStringDocumentFieldValueLoader(sqlColumnName);
    } else {
        return new StringDocumentFieldValueLoader(sqlColumnName);
    }
}


public Object decrypt(Object value){
    if (value == null) {
        return null;
    }
    return SecureEngine.decrypt(value);
}


}