package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.model.Apply;
import cn.maxcj.modular.system.dao.ApplyMapper;
import cn.maxcj.modular.system.service.IApplyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply>implements IApplyService{

@Resource
 private  ApplyMapper applyMapper;


@Override
public List<Apply> applys(Integer deptid){
    return applyMapper.applys(deptid);
}


@Override
public boolean apply_exist(Integer userid){
    if (applyMapper.apply_exist(userid) != null) {
        return true;
    }
    return false;
}


@Override
public List<Map<String,Object>> list(Integer deptid,String con){
    return applyMapper.listByDeptid(deptid, con);
}


}