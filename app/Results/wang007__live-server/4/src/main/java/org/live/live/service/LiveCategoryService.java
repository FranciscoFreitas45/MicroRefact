package org.live.live.service;
 import org.live.app.vo.LiveCategoryVo;
import org.live.common.base.BaseService;
import org.live.live.entity.LiveCategory;
import java.util.List;
public interface LiveCategoryService extends BaseService<LiveCategory, String>{


public boolean deleteLiveCategoryById(String id)
;

public List<LiveCategoryVo> findLiveCategory4app()
;

public List<LiveCategory> findAllCategory()
;

public Integer findMaxSerialNo()
;

}