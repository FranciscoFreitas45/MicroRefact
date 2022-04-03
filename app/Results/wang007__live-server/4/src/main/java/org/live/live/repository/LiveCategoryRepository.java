package org.live.live.repository;
 import org.live.app.vo.LiveCategoryVo;
import org.live.common.base.BaseRepository;
import org.live.live.entity.LiveCategory;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface LiveCategoryRepository extends BaseRepository<LiveCategory, String>{


@Query("select new org.live.app.vo.LiveCategoryVo(l.id, l.categoryName, l.coverUrl) from LiveCategory l where l.enabled=true order by l.serialNo ASC")
public List<LiveCategoryVo> findLiveCategory4app()
;

@Query("select l from LiveCategory l order by l.enabled DESC, l.serialNo ASC")
public List<LiveCategory> findAllCategory()
;

@Query(value = " select max(l.serialNo) from LiveCategory l")
public Integer findMaxSerialNo()
;

public LiveCategory getCategory(String idHCR5);

public void setCategory(String idHCR5,LiveCategory category);

public LiveCategory getLiveCategory(String idW6JM);

public void setLiveCategory(String idW6JM,LiveCategory liveCategory);

}