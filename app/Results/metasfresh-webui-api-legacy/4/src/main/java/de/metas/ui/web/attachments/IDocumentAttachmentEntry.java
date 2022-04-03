package de.metas.ui.web.attachments;
 import java.net.URI;
import de.metas.attachments.AttachmentEntry;
import de.metas.ui.web.window.datatypes.DocumentId;
public interface IDocumentAttachmentEntry {


public URI getUrl()
;

public String getFilename()
;

public AttachmentEntry.Type getType()
;

public String getContentType()
;

public DocumentId getId()
;

public byte[] getData()
;

}