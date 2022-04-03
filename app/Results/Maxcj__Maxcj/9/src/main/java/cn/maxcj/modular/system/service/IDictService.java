package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Dict;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IDictService extends IService<Dict>{


public List<Dict> selectByCode(String code)
;

public void addDict(String dictCode,String dictName,String dictTips,String dictValues)
;

public void editDict(Integer dictId,String dictCode,String dictName,String dictTips,String dicts)
;

public void delteDict(Integer dictId)
;

public List<Map<String,Object>> list(String conditiion)
;

public List<Dict> selectByParentCode(String code)
;

}