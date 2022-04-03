package com.kingen.service.skill;
 import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.kingen.bean.PersonSkillScore;
import com.kingen.bean.SkillCat;
import com.kingen.bean.StoreRoom;
import com.kingen.repository.skill.SkillDao;
import com.kingen.service.CommonService;
import com.kingen.util.BeanUtils;
import com.kingen.util.Page;
import com.kingen.util.TreeConverter;
import com.kingen.util.mapper.BeanMapper;
import com.kingen.vo.SkillAndScoreVo;
import com.kingen.vo.TreeNode;
@Component
@Transactional
public class SkillService extends CommonService<SkillCat, String>{

 private  Logger logger;

@Autowired
 private  SkillDao dao;


public void delCascade(String id){
    List<SkillCat> all = list();
    List<TreeNode> allConverted = BeanMapper.mapList(all, TreeNode.class);
    List<TreeNode> children = TreeConverter.getChildren(allConverted, id);
    dao.delete(id);
    if (CollectionUtils.isEmpty(children)) {
        return;
    }
    for (TreeNode node : children) {
        dao.delete(node.getId());
    }
}


public Page<SkillAndScoreVo> findPersonScore(Page<SkillAndScoreVo> page,String userId){
    return dao.findPersonScore(page, userId);
}


public void saveBatch(String userId,List<PersonSkillScore> scores){
    if (!CollectionUtils.isEmpty(scores)) {
        for (PersonSkillScore s : scores) {
            s.setUserId(userId);
            dao.saveOrUpdateX(s);
        }
    }
}


public void updateForm(SkillCat data){
    SkillCat t = unique(data.getId());
    BeanUtils.copyNotNullProperties(data, t);
    update(t);
}


}