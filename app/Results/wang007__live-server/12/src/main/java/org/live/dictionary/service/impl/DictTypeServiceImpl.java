package org.live.dictionary.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.dictionary.entity.DictType;
import org.live.dictionary.repository.DictTypeRepository;
import org.live.dictionary.service.DictTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Service
public class DictTypeServiceImpl extends BaseServiceImpl<DictType, String>implements DictTypeService{

@Resource
 private  DictTypeRepository dictTypeRepository;


@Override
public BaseRepository<DictType,String> getRepository(){
    return this.dictTypeRepository;
}


@Override
public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = dictTypeRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<DictType> page = dictTypeRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


}