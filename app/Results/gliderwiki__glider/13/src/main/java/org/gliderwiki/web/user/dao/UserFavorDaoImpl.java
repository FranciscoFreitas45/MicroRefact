package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("userFavorDao")
public class UserFavorDaoImpl extends SqlSessionDaoSupportimplements UserFavorDao{


@Override
public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx){
    List<WikiFavoriteVo> wikiFavoriteVo = null;
    wikiFavoriteVo = (List<WikiFavoriteVo>) getSqlSession().selectList("MemberManage.getMyFavoriteWikiList", weUserIdx);
    return wikiFavoriteVo;
}


@Override
public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx){
    List<WikiFavoriteVo> wikiFavoriteVo = null;
    wikiFavoriteVo = (List<WikiFavoriteVo>) getSqlSession().selectList("MemberManage.getMyFavoriteSpaceList", weUserIdx);
    return wikiFavoriteVo;
}


}