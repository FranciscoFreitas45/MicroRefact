package de.metas.ui.web.mail;
 import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Util;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import lombok.NonNull;
@Component
public class WebuiMailAttachmentsRepository implements InitializingBean{

 private  Logger logger;

 private  String PROPERTY_AttachmentsDir;

@Value("${metasfresh.webui.email.attachmentsDir:}")
 private  String attachmentsFilePath;

 private  File attachmentsDir;


public byte[] getAttachmentAsByteArray(String emailId,LookupValue attachment){
    final File attachmentFile = getAttachmentFile(emailId, attachment.getIdAsString());
    return Util.readBytes(attachmentFile);
}


public File checkCreateAttachmentsDir(String attachmentsFilePath){
    final File attachmentsDir;
    if (Check.isEmpty(attachmentsFilePath, true)) {
        logger.warn("Using default attachments directory. It's highly recommended to define a proper one. To configure it, please set '{}' property.", PROPERTY_AttachmentsDir);
        String tmpdir = System.getProperty("java.io.tmpdir");
        attachmentsDir = new File(tmpdir, "metasfresh-webui/email_attachments");
    } else {
        attachmentsDir = new File(attachmentsFilePath);
    }
    if (!attachmentsDir.exists() && !attachmentsDir.mkdirs()) {
        throw new AdempiereException("Cannot create " + attachmentsDir);
    }
    return attachmentsDir;
}


@Override
public void afterPropertiesSet(){
    attachmentsDir = checkCreateAttachmentsDir(attachmentsFilePath);
    logger.info("Attachments directory: {}", attachmentsDir);
}


public LookupValue createAttachment(String emailId,String filename,byte[] fileContent){
    final String attachmentId = UUID.randomUUID().toString();
    // 
    // Store it to internal attachments storage
    final File attachmentFile = getAttachmentFile(emailId, attachmentId);
    try {
        FileCopyUtils.copy(fileContent, attachmentFile);
    } catch (final IOException e) {
        throw new AdempiereException("Failed storing " + filename).setParameter("filename", fileContent).setParameter("attachmentFile", attachmentFile);
    }
    // 
    return StringLookupValue.of(attachmentId, filename);
}


public File getAttachmentFile(String emailId,String attachmentId){
    if (emailId.contains("/") || emailId.contains("\\")) {
        throw new IllegalArgumentException("Invalid emailId: " + emailId);
    }
    if (attachmentId.contains("/") || attachmentId.contains("\\")) {
        throw new IllegalArgumentException("Invalid attachmentId: " + attachmentId);
    }
    return new File(getAttachmentsDir(), emailId + "_" + attachmentId);
}


@NonNull
public File getAttachmentsDir(){
    return attachmentsDir;
}


public void deleteAttachments(String emailId,LookupValuesList attachmentsList){
    attachmentsList.stream().forEach(attachment -> deleteAttachment(emailId, attachment));
}


public void deleteAttachment(String emailId,LookupValue attachment){
    final String attachmentId = attachment.getIdAsString();
    final File attachmentFile = getAttachmentFile(emailId, attachmentId);
    if (!attachmentFile.exists()) {
        logger.debug("Attachment file {} is missing. Nothing to delete", attachmentFile);
        return;
    }
    if (!attachmentFile.delete()) {
        attachmentFile.deleteOnExit();
        logger.warn("Cannot delete attachment file {}. Scheduled to be deleted on exit", attachmentFile);
    } else {
        logger.debug("Deleted attachment file {}", attachmentFile);
    }
}


}