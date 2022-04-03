package de.metas.ui.web.window.descriptor.factory.standard.GenericDocumentSummaryValueProvider;
 import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.ad.service.ILookupDAO;
import org.adempiere.ad.service.ILookupDAO.ILookupDisplayInfo;
import org.adempiere.ad.service.ILookupDAO.ITableRefInfo;
import org.compiere.model.ILookupDisplayColumn;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.Language;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.IDocumentFieldValueProvider;
import de.metas.util.Services;
import lombok.Data;
public interface FieldValueExtractor {


public String extractFieldValueToString(Document document)
;

public String getFieldName()
;

}