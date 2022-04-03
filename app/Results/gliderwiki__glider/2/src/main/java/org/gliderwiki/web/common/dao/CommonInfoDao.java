package org.gliderwiki.web.common.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeMenu;
public interface CommonInfoDao {


public List<WeMenu> getSubMenuByAuth(Integer weMenuIdx,Integer weUserIdx,Integer weGrade)
;

public List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity,Integer weUserIdx,Integer we_grade)
;

}