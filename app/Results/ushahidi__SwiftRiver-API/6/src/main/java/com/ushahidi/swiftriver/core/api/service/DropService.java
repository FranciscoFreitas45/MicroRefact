package com.ushahidi.swiftriver.core.api.service;
 import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.dao.AccountDao;
import com.ushahidi.swiftriver.core.api.dao.DropDao;
import com.ushahidi.swiftriver.core.api.dao.LinkDao;
import com.ushahidi.swiftriver.core.api.dao.PlaceDao;
import com.ushahidi.swiftriver.core.api.dao.TagDao;
import com.ushahidi.swiftriver.core.api.dto.CreateDropDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.TagDao;
@Service
@Transactional(readOnly = true)
public class DropService {

 final  Logger LOGGER;

@Autowired
 private  Mapper mapper;

@Autowired
 private  DropDao dropDao;

@Autowired
 private  AccountDao accountDao;

@Autowired
 private  LinkDao linkDao;

@Autowired
 private  PlaceDao placeDao;

@Autowired
 private  TagDao tagDao;


public AccountDao getAccountDao(){
    return accountDao;
}


public void setAccountDao(AccountDao accountDao){
    this.accountDao = accountDao;
}


public void setDropDao(DropDao dropDao){
    this.dropDao = dropDao;
}


public PlaceDao getPlaceDao(){
    return placeDao;
}


public void setPlaceDao(PlaceDao placeDao){
    this.placeDao = placeDao;
}


public Mapper getMapper(){
    return mapper;
}


public LinkDao getLinkDao(){
    return linkDao;
}


public DropDao getDropDao(){
    return dropDao;
}


public void setLinkDao(LinkDao linkDao){
    this.linkDao = linkDao;
}


public void setMapper(Mapper mapper){
    this.mapper = mapper;
}


public TagDao getTagDao(){
    return tagDao;
}


public void setTagDao(TagDao tagDao){
    this.tagDao = tagDao;
}


@Transactional(readOnly = false)
public List<GetDropDTO> createDrops(List<CreateDropDTO> dropDTOs){
    List<Drop> drops = new ArrayList<Drop>();
    for (CreateDropDTO dto : dropDTOs) {
        drops.add(mapper.map(dto, Drop.class));
    }
    dropDao.createDrops(drops);
    List<GetDropDTO> getDropDTOs = new ArrayList<GetDropDTO>();
    for (Drop drop : drops) {
        getDropDTOs.add(mapper.map(drop, GetDropDTO.class));
    }
    return getDropDTOs;
}


}