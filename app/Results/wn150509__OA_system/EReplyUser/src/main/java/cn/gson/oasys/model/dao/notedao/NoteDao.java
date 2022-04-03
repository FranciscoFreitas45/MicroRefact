package cn.gson.oasys.model.dao.notedao;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.note.Note;
import cn.gson.oasys.model.entity.note.Noteuser;
import cn.gson.oasys.model.entity.user.User;
@Repository
public interface NoteDao extends JpaRepository<Note, Long>{


@Query("FROM Note n WHERE  n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.createTime DESC")
public Page<Note> findByUserssOrderByCreateTimeDesc(Long userid,Pageable pa)
;

@Query("from Note n " + " where n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.typeId DESC " + "")
public Page<Note> findByUserssOrderByTypeIdDesc(long userid,Pageable pa)
;

@Query("from Note n " + " where n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.statusId DESC" + "")
public Page<Note> findByUserssOrderByStatusIdDesc(long userid,Pageable pa)
;

@Query("from Note n where (n.statusId in (SELECT s.statusId from SystemStatusList s where s.statusName like %?1%)" + " or DATE_format(n.createTime,'%Y-%m-%d') like %?1%" + " or n.typeId in (SELECT t.typeId from SystemTypeList t where t.typeName like %?1%) or n.title like %?1%) " + "and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2)" + "")
public Page<Note> findBytitleOrderByCreateTimeDesc(String basekey,long userid,Pageable pa)
;

@Query("delete from Note n where n.noteId=?1 ")
@Modifying
public Integer delete(long noteid)
;

@Query("from Note n" + " where n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.statusId ASC" + "")
public Page<Note> findByUserssOrderByStatusIdAsc(long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.statusId ASC")
public Page<Note> findByTypeIdOrderByStatusIdAsc(Long typeId,long userid,Pageable pa)
;

@Query("FROM Note n WHERE  n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.createTime ASC")
public Page<Note> findByUserssOrderByCreateTimeAsc(Long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.statusId DESC")
public Page<Note> findByTypeIdOrderByStatusIdDesc(Long typeId,long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.createTime DESC")
public Page<Note> findByCatalogIdOrderByCreateTimeDesc(long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.typeId ASC")
public Page<Note> findByTypeIdOrderByTypeIdAsc(Long typeId,long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.typeId DESC ")
public Page<Note> findByCatalogIdOrderByTypeIdDesc(Long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.createTime DESC")
public Page<Note> findByTypeIdOrderByCreateTimeAsc(Long typeId,long userid,Pageable pa)
;

@Query("from Note n " + " where n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1) ORDER BY n.typeId ASC" + "")
public Page<Note> findByUserssOrderByTypeIdAsc(long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.createTime ASC")
public Page<Note> findByCatalogIdOrderByCreateTimeAsc(Long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.isCollected=?1 and n.catalogId=?2 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?3)")
public Page<Note> findByIsCollectedAndCatalogIdOrderByCreateTimeDesc(long isCollected,long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.typeId ASC")
public Page<Note> findByCatalogIdOrderByTypeIdAsc(Long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.typeId DESC")
public Page<Note> findByTypeIdOrderByTypeIdDesc(Long typeId,long userid,Pageable pa)
;

@Query("from Note n where n.isCollected=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2)")
public Page<Note> findByIsCollectedOrderByCreateTimeDesc(long isCollected,long userid,Pageable pa)
;

@Query("from Note n where n.typeId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2)")
public Page<Note> findByTypeIdOrderByCreateTimeDesc(long typeId,long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.statusId DESC")
public Page<Note> findByCatalogIdOrderByStatusIdDesc(Long catalogId,long userid,Pageable pa)
;

@Query("from Noteuser nu where nu.noteId=?1 and nu.userId=?2")
public Noteuser finduserid(long noteid,Long userId)
;

@Query("from Note n where (n.statusId in (SELECT s.statusId from SystemStatusList s where s.statusName like %?1%)" + " or DATE_format(n.createTime,'%Y-%m-%d') like %?1%" + " or n.typeId in (SELECT t.typeId from SystemTypeList t where t.typeName like %?1%) or n.title like %?1%) " + "and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) and n.catalogId=?3" + "")
public Page<Note> findBytitleAndCatalogId(String basekey,long userid,long cataid,Pageable pa)
;

@Query("update Note n set n.isCollected=?1 where n.noteId=?2")
@Modifying
public Integer updatecollect(long isCollected,long noteId)
;

@Query("FROM Note n WHERE  n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?1)")
public Page<Note> findByUserss(Long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2) ORDER BY n.statusId ASC")
public Page<Note> findByCatalogIdOrderByStatusIdAsc(Long catalogId,long userid,Pageable pa)
;

@Query("from Note n where n.catalogId=?1 and n.noteId in (SELECT r.noteId from Noteuser r where r.userId=?2)")
public List<Note> findByCatalogId(long catalogId,long userid)
;

public void setReceiver(Long id,String receiver);

public void setCreatemanId(Long id,Long createmanId);

public void setUserss(Long id,Set<User> userss);

}