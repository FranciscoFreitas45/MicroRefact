package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.model.Finance;
import cn.maxcj.modular.system.dao.FinanceMapper;
import cn.maxcj.modular.system.service.IFinanceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance>implements IFinanceService{

@Resource
 private  FinanceMapper financeMapper;


@Override
public List<Map<String,Object>> getHistory(String condition,Integer category,Integer state){
    return financeMapper.getHistory(condition, category, state);
}


@Override
public List<Map<String,Object>> getClubFinance(String condition,Integer category){
    return financeMapper.getClubFinance(condition, category);
}


@Override
public List<Map<String,Object>> getMyClubFinance(Integer condition,Integer deptid){
    return financeMapper.getMyClubFinance(condition, deptid);
}


}