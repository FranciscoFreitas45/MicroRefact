package cn.gson.oasys.model.dao.filedao;
 import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.file.FileList;
import cn.gson.oasys.model.entity.file.FilePath;
import cn.gson.oasys.model.entity.user.User;
@Repository
public interface FilePathdao extends PagingAndSortingRepository<FilePath, Long>{


public List<FilePath> findByParentId(Long parentId)
;

public List<FilePath> findByParentIdAndPathIstrash(Long parentId,Long istrash)
;

public List<FilePath> findByPathUserIdAndPathIstrash(Long userid,Long istrash)
;

public List<FilePath> findByPathUserIdAndPathIstrashAndPathNameLikeAndParentIdNot(Long userid,Long istrash,String likefilename,Long userrootpathid)
;

public FilePath findByPathName(String pathname)
;

public FilePath findByPathNameAndParentId(String pathname,Long parentId)
;

public FilePath findByParentIdAndPathUserId(Long parentId,Long userid)
;

}