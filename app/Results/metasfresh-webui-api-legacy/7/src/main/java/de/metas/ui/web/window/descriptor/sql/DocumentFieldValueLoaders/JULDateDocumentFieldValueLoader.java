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
public class JULDateDocumentFieldValueLoader implements DocumentFieldValueLoader{

 private  String sqlColumnName;

 private  boolean encrypted;


@Override
public Object retrieveFieldValue(ResultSet rs,boolean isDisplayColumnAvailable,String adLanguage,LookupDescriptor lookupDescriptor_NOTUSED){
    final Timestamp valueTS = rs.getTimestamp(sqlColumnName);
    final java.util.Date value = valueTS == null ? null : new java.util.Date(valueTS.getTime());
    return encrypted ? decrypt(value) : value;
}


}