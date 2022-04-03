package run.halo.app.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Attachment;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.repository.base.BaseRepository;
public interface AttachmentRepository extends BaseRepository<Attachment, Integer>, JpaSpecificationExecutor<Attachment>{


@Query(value = "select distinct a.type from Attachment a")
public List<AttachmentType> findAllType()
;

@Query(value = "select distinct a.mediaType from Attachment a")
public List<String> findAllMediaType()
;

public long countByPath(String path)
;

}