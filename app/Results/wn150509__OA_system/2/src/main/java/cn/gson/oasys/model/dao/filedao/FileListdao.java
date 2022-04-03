package cn.gson.oasys.model.dao.filedao;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.file.FileList;
import cn.gson.oasys.model.entity.file.FilePath;
import cn.gson.oasys.model.entity.user.User;
@Repository
public interface FileListdao extends PagingAndSortingRepository<FileList, Long>{


public List<FileList> findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(User user,Long istrash,String contenttype,String likefilename)
;

public List<FileList> findByUserAndFileIsshareAndFileIstrash(User user,Long isshare,Long istrash)
;

public List<FileList> findByFileIsshareAndFileIstrash(Long isshare,Long istrash)
;

public List<FileList> findByUserAndFileIstrashAndFileNameLike(User user,Long istrash,String likefile)
;

public List<FileList> findByFileIsshareAndFileNameLike(Long isshare,String likefile)
;

public List<FileList> findByUserAndContentTypeLikeAndFileIstrash(User user,String contenttype,Long istrash)
;

@Query("from FileList f where f.user=?1 and f.fileIstrash=0 and f.fileName LIKE ?2 and f.contentType NOT LIKE 'image/%' and f.contentType NOT LIKE 'application/x%' and f.contentType NOT LIKE 'video/%' and f.contentType NOT LIKE 'audio/%'")
public List<FileList> finddocumentlike(User user,String likefilename)
;

public FileList findByFileNameAndFpath(String filename,FilePath filepath)
;

public List<FileList> findByFpath(FilePath filepath)
;

public List<FileList> findByFpathAndFileIstrash(FilePath filepath,Long istrash)
;

public List<FileList> findByUserAndFileIstrash(User user,Long istrash)
;

@Query("from FileList f where f.user=?1 and f.fileIstrash=0 and f.contentType NOT LIKE 'image/%' and f.contentType NOT LIKE 'application/x%' and f.contentType NOT LIKE 'video/%' and f.contentType NOT LIKE 'audio/%'")
public List<FileList> finddocument(User user)
;

}