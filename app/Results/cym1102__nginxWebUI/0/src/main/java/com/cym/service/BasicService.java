package com.cym.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cym.model.Basic;
import cn.craccd.sqlHelper.bean.Sort;
import cn.craccd.sqlHelper.bean.Sort.Direction;
import cn.craccd.sqlHelper.utils.ConditionAndWrapper;
import cn.craccd.sqlHelper.utils.ConditionOrWrapper;
import cn.craccd.sqlHelper.utils.SqlHelper;
@Service
public class BasicService {

@Autowired
 private SqlHelper sqlHelper;


public boolean contain(String content){
    return sqlHelper.findCountByQuery(new ConditionOrWrapper().like(Basic::getValue, content).like(Basic::getName, content), Basic.class) > 0;
}


public List<Basic> findAll(){
    return sqlHelper.findAll(new Sort().add("seq", Direction.ASC), Basic.class);
}


@Transactional
public void setSeq(String basicId,Integer seqAdd){
    Basic basic = sqlHelper.findById(basicId, Basic.class);
    List<Basic> basicList = sqlHelper.findAll(new Sort("seq", Direction.ASC), Basic.class);
    if (basicList.size() > 0) {
        Basic tagert = null;
        if (seqAdd < 0) {
            for (int i = 0; i < basicList.size(); i++) {
                if (basicList.get(i).getSeq() < basic.getSeq()) {
                    tagert = basicList.get(i);
                }
            }
        } else {
            for (int i = basicList.size() - 1; i >= 0; i--) {
                if (basicList.get(i).getSeq() > basic.getSeq()) {
                    tagert = basicList.get(i);
                }
            }
        }
        if (tagert != null) {
            // 交换seq
            Long seq = tagert.getSeq();
            tagert.setSeq(basic.getSeq());
            basic.setSeq(seq);
            sqlHelper.updateById(tagert);
            sqlHelper.updateById(basic);
        }
    }
}


}