package cn.gson.oasys.model.dao.processdao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cn.gson.oasys.model.entity.process.Notepaper;
import cn.gson.oasys.model.entity.user.User;
public interface NotepaperDao extends JpaRepository<Notepaper, Long>{


public Page<Notepaper> findByUserIdOrderByCreateTimeDesc(Pageable page)
;

public Page<Notepaper> findByTitleLikeOrderByCreateTimeDesc(String baseKey,Pageable page)
;

}