package de.metas.ui.web.dashboard.KPIField;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.Language;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.NonNull;
@FunctionalInterface
public interface BucketValueExtractor {


public Object extractValue(String containingAggName,MultiBucketsAggregation.Bucket bucket)
;

}