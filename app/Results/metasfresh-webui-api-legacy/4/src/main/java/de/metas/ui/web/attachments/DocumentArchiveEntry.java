package de.metas.ui.web.attachments;
 import java.net.URI;
import org.adempiere.archive.api.IArchiveBL;
import org.compiere.model.I_AD_Archive;
import org.compiere.util.MimeType;
import de.metas.attachments.AttachmentEntry;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.FileUtils;
import de.metas.util.Services;
public class DocumentArchiveEntry implements IDocumentAttachmentEntry{

 private  DocumentId id;

 private  I_AD_Archive archive;


@Override
public URI getUrl(){
    return null;
}


@Override
public String getFilename(){
    final String contentType = getContentType();
    final String fileExtension = MimeType.getExtensionByType(contentType);
    final String name = archive.getName();
    return FileUtils.changeFileExtension(name, fileExtension);
}


@Override
public AttachmentEntry.Type getType(){
    return AttachmentEntry.Type.Data;
}


public DocumentArchiveEntry of(DocumentId id,I_AD_Archive archive){
    return new DocumentArchiveEntry(id, archive);
}


@Override
public String getContentType(){
    final IArchiveBL archiveBL = Services.get(IArchiveBL.class);
    return archiveBL.getContentType(archive);
}


@Override
public DocumentId getId(){
    return id;
}


@Override
public byte[] getData(){
    final IArchiveBL archiveBL = Services.get(IArchiveBL.class);
    return archiveBL.getBinaryData(archive);
}


}