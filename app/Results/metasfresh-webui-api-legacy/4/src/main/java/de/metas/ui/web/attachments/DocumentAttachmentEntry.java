package de.metas.ui.web.attachments;
 import lombok.NonNull;
import lombok.ToString;
import java.net.URI;
import org.compiere.Adempiere;
import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.AttachmentEntryService;
import de.metas.ui.web.window.datatypes.DocumentId;
@ToString
public class DocumentAttachmentEntry implements IDocumentAttachmentEntry{

 private  DocumentId id;

 private  AttachmentEntry entry;


@Override
public URI getUrl(){
    return entry.getUrl();
}


@Override
public String getFilename(){
    return entry.getFilename();
}


@Override
public AttachmentEntry.Type getType(){
    return entry.getType();
}


public DocumentAttachmentEntry of(DocumentId id,AttachmentEntry entry){
    return new DocumentAttachmentEntry(id, entry);
}


@Override
public String getContentType(){
    return entry.getMimeType();
}


@Override
public DocumentId getId(){
    return id;
}


@Override
public byte[] getData(){
    final AttachmentEntryService attachmentEntryService = Adempiere.getBean(AttachmentEntryService.class);
    return attachmentEntryService.retrieveData(entry.getId());
}


}