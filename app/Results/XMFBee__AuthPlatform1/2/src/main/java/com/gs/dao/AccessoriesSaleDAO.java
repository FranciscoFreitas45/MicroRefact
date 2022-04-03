package com.gs.dao;
 import com.gs.bean.AccessoriesSale;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AccessoriesSaleDAO extends BaseDAO<String, AccessoriesSale>{


public List<AccessoriesSale> queryByPagerDisable(Pager pager)
;

public int countByBlurred(AccessoriesSale accessoriesSale,User user)
;

public int countByDisable()
;

public List<AccessoriesSale> blurredQuery(Pager pager,AccessoriesSale accessoriesSale)
;

}