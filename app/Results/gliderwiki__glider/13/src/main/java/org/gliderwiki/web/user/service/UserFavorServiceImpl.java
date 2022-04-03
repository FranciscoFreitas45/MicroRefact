package org.gliderwiki.web.user.service;
 import java.util.List;
import org.gliderwiki.web.user.dao.UserFavorDao;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("userFavorService")
public class UserFavorServiceImpl implements UserFavorService{

 private Logger logger;

@Autowired
 private  UserFavorDao userFavorDao;


@Override
public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx){
    return userFavorDao.getMyFavoriteWikiList(weUserIdx);
}


@Override
public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx){
    return userFavorDao.getMyFavoriteSpaceList(weUserIdx);
}


}