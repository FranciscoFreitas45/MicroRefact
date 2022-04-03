package de.metas.ui.web.attachments;
 import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.jgoodies.common.base.Objects;
import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.AttachmentEntryId;
import de.metas.attachments.AttachmentEntryService;
import de.metas.attachments.listener.TableAttachmentListenerService;
import de.metas.ui.web.attachments.json.JSONAttachment;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.archive.api.IArchiveDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ITableRecordReference;
import org.adempiere.util.lang.ImmutablePair;
import org.compiere.model.I_AD_Archive;
import org.compiere.model.I_AD_AttachmentEntry;
import org.compiere.util.Env;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Stream;
public class DocumentAttachments {

 public  String ID_PREFIX_Attachment;

 public  String ID_PREFIX_Archive;

 private  String ID_SEPARATOR;

 private  Splitter ID_Splitter;

 private  Joiner ID_Joiner;

 private  AttachmentEntryService attachmentEntryService;

 private  DocumentPath documentPath;

 private  ITableRecordReference recordRef;

 private  DocumentEntityDescriptor entityDescriptor;

 private  DocumentWebsocketPublisher websocketPublisher;


public List<JSONAttachment> toJson(){
    final Stream<IDocumentAttachmentEntry> attachments = attachmentEntryService.getByReferencedRecord(recordRef).stream().map(entry -> DocumentAttachmentEntry.of(buildId(ID_PREFIX_Attachment, entry.getId().getRepoId()), entry));
    final Stream<DocumentArchiveEntry> archives = Services.get(IArchiveDAO.class).retrieveLastArchives(Env.getCtx(), recordRef, 10).stream().map(archive -> DocumentArchiveEntry.of(buildId(ID_PREFIX_Archive, archive.getAD_Archive_ID()), archive));
    return Stream.concat(attachments, archives).map(JSONAttachment::of).collect(ImmutableList.toImmutableList());
}


public void addURLEntry(String name,URI url){
    attachmentEntryService.createNewAttachment(recordRef, name, url);
    notifyRelatedDocumentTabsChanged();
}


public IPair<String,Integer> toPrefixAndEntryId(DocumentId id){
    final List<String> idParts = ID_Splitter.splitToList(id.toJson());
    if (idParts.size() != 2) {
        throw new IllegalArgumentException("Invalid attachment ID");
    }
    final String idPrefix = idParts.get(0);
    final int entryId = Integer.parseInt(idParts.get(1));
    return ImmutablePair.of(idPrefix, entryId);
}


public void addEntry(MultipartFile file){
    Check.assumeNotNull(file, "Parameter file is not null");
    final String name = file.getOriginalFilename();
    final byte[] data = file.getBytes();
    attachmentEntryService.createNewAttachment(recordRef, name, data);
    notifyRelatedDocumentTabsChanged();
}


public void deleteEntry(DocumentId id){
    final IPair<String, Integer> prefixAndId = toPrefixAndEntryId(id);
    final String idPrefix = prefixAndId.getLeft();
    final int entryId = prefixAndId.getRight();
    if (ID_PREFIX_Attachment.equals(idPrefix)) {
        final AttachmentEntry entry = attachmentEntryService.getById(AttachmentEntryId.ofRepoId(entryId));
        attachmentEntryService.unattach(recordRef, entry);
        notifyRelatedDocumentTabsChanged();
    } else if (ID_PREFIX_Archive.equals(idPrefix)) {
        final I_AD_Archive archive = Services.get(IArchiveDAO.class).retrieveArchiveOrNull(Env.getCtx(), recordRef, entryId);
        if (archive == null) {
            throw new EntityNotFoundException(id.toJson());
        }
        InterfaceWrapperHelper.delete(archive);
    } else {
        throw new EntityNotFoundException(id.toJson());
    }
}


public void notifyRelatedDocumentTabsChanged(){
    final ImmutableSet<DetailId> attachmentRelatedTabIds = entityDescriptor.getIncludedEntities().stream().filter(includedEntityDescriptor -> Objects.equals(includedEntityDescriptor.getTableNameOrNull(), I_AD_AttachmentEntry.Table_Name)).map(includedEntityDescriptor -> includedEntityDescriptor.getDetailId()).collect(ImmutableSet.toImmutableSet());
    if (attachmentRelatedTabIds.isEmpty()) {
        return;
    }
    websocketPublisher.staleTabs(documentPath.getWindowId(), documentPath.getDocumentId(), attachmentRelatedTabIds);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(recordRef).toString();
}


public DocumentId buildId(String idPrefix,int id){
    return DocumentId.ofString(ID_Joiner.join(idPrefix, id));
}


public IDocumentAttachmentEntry getEntry(DocumentId id){
    final IPair<String, Integer> prefixAndId = toPrefixAndEntryId(id);
    final String idPrefix = prefixAndId.getLeft();
    final int entryId = prefixAndId.getRight();
    if (ID_PREFIX_Attachment.equals(idPrefix)) {
        final AttachmentEntry entry = attachmentEntryService.getById(AttachmentEntryId.ofRepoId(entryId));
        if (entry == null) {
            throw new EntityNotFoundException(id.toJson());
        }
        return DocumentAttachmentEntry.of(id, entry);
    } else if (ID_PREFIX_Archive.equals(idPrefix)) {
        final I_AD_Archive archive = Services.get(IArchiveDAO.class).retrieveArchiveOrNull(Env.getCtx(), recordRef, entryId);
        if (archive == null) {
            throw new EntityNotFoundException(id.toJson());
        }
        return DocumentArchiveEntry.of(id, archive);
    } else {
        throw new EntityNotFoundException(id.toJson());
    }
}


}