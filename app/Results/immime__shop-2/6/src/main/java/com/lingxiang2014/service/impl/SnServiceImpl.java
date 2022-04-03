package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.SnDao;
import com.lingxiang2014.entity.Sn.Type;
import com.lingxiang2014.service.SnService;
@Service("snServiceImpl")
public class SnServiceImpl implements SnService{

@Resource(name = "snDaoImpl")
 private  SnDao snDao;


@Transactional
public String generate(Type type){
    return snDao.generate(type);
}


}