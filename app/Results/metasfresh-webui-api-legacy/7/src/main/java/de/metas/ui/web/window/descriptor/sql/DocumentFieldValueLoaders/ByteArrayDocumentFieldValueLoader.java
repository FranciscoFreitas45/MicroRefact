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
public class ByteArrayDocumentFieldValueLoader implements DocumentFieldValueLoader{

 private  String sqlColumnName;

 private  boolean encrypted;


@Override
public byte[] retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor_NOTUSED){
    final Object valueObj = rs.getObject(sqlColumnName);
    final byte[] valueBytes;
    if (rs.wasNull()) {
        valueBytes = null;
    } else if (valueObj instanceof Clob) {
        final Clob lob = (Clob) valueObj;
        final long length = lob.length();
        valueBytes = lob.getSubString(1, (int) length).getBytes(StandardCharsets.UTF_8);
    } else if (valueObj instanceof Blob) {
        final Blob lob = (Blob) valueObj;
        final long length = lob.length();
        valueBytes = lob.getBytes(1, (int) length);
    } else if (valueObj instanceof String) {
        valueBytes = ((String) valueObj).getBytes(StandardCharsets.UTF_8);
    } else if (valueObj instanceof byte[]) {
        valueBytes = (byte[]) valueObj;
    } else {
        logger.warn("Unknown LOB value '{}' for {}. Considering it null.", valueObj, sqlColumnName);
        valueBytes = null;
    }
    // 
    return valueBytes;
}


}