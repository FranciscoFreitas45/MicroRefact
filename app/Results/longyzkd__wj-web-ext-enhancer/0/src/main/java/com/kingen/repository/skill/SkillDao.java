package com.kingen.repository.skill;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.kingen.bean.SkillCat;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
import com.kingen.util.Parameter;
import com.kingen.vo.SkillAndScoreVo;
@Component
public class SkillDao extends CommonDao<SkillCat, String>{


public Page<SkillAndScoreVo> findPersonScore(Page<SkillAndScoreVo> page,String userId){
    Parameter parameter = new Parameter(userId);
    // hql 用left join 要先在类里加引用
    StringBuilder sql = new StringBuilder("select s.name as name , score.id as id, score.user_Id as userId,s.id as skillId,score.score as score " + // 超级管理员不在页面显示
    "from t_skill_cat s left join t_person_skill_score score on s.id = score.skill_Id  and score.user_Id = :p1 ");
    return findPageBySql(page, sql.toString(), parameter, SkillAndScoreVo.class);
}


}