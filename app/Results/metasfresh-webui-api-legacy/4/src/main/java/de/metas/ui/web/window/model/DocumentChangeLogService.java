package de.metas.ui.web.window.model;
 import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import org.adempiere.ad.table.RecordChangeLog;
import org.adempiere.ad.table.RecordChangeLogRepository;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import de.metas.i18n.Language;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangeLog;
import de.metas.user.api.IUserDAO;
import de.metas.util.Services;
import lombok.NonNull;
@Service
public class DocumentChangeLogService {

 private  Logger logger;

 private  IUserDAO usersRepo;

 private  RecordChangeLogRepository changeLogRepository;


public JSONDocumentChangeLog toJson(RecordChangeLog changeLog,String adLanguage){
    final JSONDocumentChangeLog json = new JSONDocumentChangeLog();
    json.setCreatedByUsername(usersRepo.retrieveUserFullname(changeLog.getCreatedByUserId()));
    json.setLastChangedByUsername(usersRepo.retrieveUserFullname(changeLog.getLastChangedByUserId()));
    final ZonedDateTime created = TimeUtil.asZonedDateTime(changeLog.getCreatedTimestamp());
    json.setCreatedTimestamp(formatTimestamp(created, adLanguage));
    final ZonedDateTime updated = TimeUtil.asZonedDateTime(changeLog.getLastChangedTimestamp());
    json.setLastChangedTimestamp(formatTimestamp(updated, adLanguage));
    return json;
}


public String formatTimestamp(ZonedDateTime timestamp,String adLanguage){
    if (timestamp == null) {
        return null;
    }
    try {
        final Language language = Language.getLanguage(adLanguage);
        final SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.DateTime, language);
        return dateFormat.format(TimeUtil.asDate(timestamp));
    } catch (Exception ex) {
        logger.warn("Failed formating {}. Returning it a string.", timestamp, ex);
        return timestamp.toString();
    }
}


public JSONDocumentChangeLog getJSONDocumentChangeLog(TableRecordReference recordRef,String adLanguage){
    final RecordChangeLog changeLog = changeLogRepository.getSummaryByRecord(recordRef);
    return toJson(changeLog, adLanguage);
}


}