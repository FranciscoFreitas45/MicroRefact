package de.metas.ui.web.process.ProcessInstanceResult;
 import java.io.File;
import org.compiere.util.Util;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
@lombok.Value
@lombok.Builder
public class OpenReportAction implements ResultAction{

@NonNull
 private  String filename;

@NonNull
 private  String contentType;

@NonNull
@Setter(AccessLevel.NONE)
@Getter(AccessLevel.NONE)
 private  File tempFile;


public byte[] getReportData(){
    return Util.readBytes(tempFile);
}


}