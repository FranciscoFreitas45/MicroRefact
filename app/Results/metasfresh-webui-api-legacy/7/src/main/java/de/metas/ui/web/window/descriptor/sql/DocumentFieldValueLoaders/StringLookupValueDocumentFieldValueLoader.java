package de.metas.ui.web.window.descriptor.sql.DocumentFieldValueLoaders;
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
@Value
public class StringLookupValueDocumentFieldValueLoader implements DocumentFieldValueLoader{

@NonNull
 private  String sqlColumnName;

@NonNull
 private  String sqlDisplayColumnName;


@Override
public StringLookupValue retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor_NOTUSED){
    final String key = rs.getString(sqlColumnName);
    if (rs.wasNull()) {
        return null;
    }
    if (isDisplayColumnAvailable) {
        final DisplayNameAndDescription result = DocumentFieldValueLoaders.extractDisplayNameAndDescription(rs, sqlDisplayColumnName, adLanguage);
        return StringLookupValue.builder().id(key).displayName(result.getDisplayName()).description(result.getDescription()).build();
    } else {
        return StringLookupValue.unknown(key);
    }
}


}