package com.kingen.service.assets;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.kingen.bean.Assets;
import com.kingen.bean.AssetsType;
import com.kingen.bean.PersonSkillScore;
import com.kingen.bean.SkillCat;
import com.kingen.repository.assets.AssetsDao;
import com.kingen.repository.assets.AssetsTypeDao;
import com.kingen.service.CommonService;
import com.kingen.util.BeanUtils;
import com.kingen.util.Page;
import com.kingen.util.TreeConverter;
import com.kingen.util.mapper.BeanMapper;
import com.kingen.vo.SkillAndScoreVo;
import com.kingen.vo.TreeNode;
@Component
@Transactional
public class AssetsService extends CommonService<Assets, String>{

 private  Logger logger;

@Autowired
 private  AssetsDao dao;

@Autowired
 private  AssetsTypeDao typeDao;


public void delCascade(String id){
    List<AssetsType> all = typeDao.find();
    List<TreeNode> allConverted = BeanMapper.mapList(all, TreeNode.class);
    List<TreeNode> children = TreeConverter.getChildren(allConverted, id);
    typeDao.delete(id);
    if (CollectionUtils.isEmpty(children)) {
        return;
    }
    for (TreeNode node : children) {
        typeDao.delete(node.getId());
    }
}


public void updateForm(AssetsType data){
    AssetsType t = typeDao.unique(data.getId());
    BeanUtils.copyNotNullProperties(data, t);
    typeDao.update(t);
}


}