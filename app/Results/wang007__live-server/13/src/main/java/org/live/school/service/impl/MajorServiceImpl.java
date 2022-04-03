package org.live.school.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.school.entity.Major;
import org.live.school.repository.MajorRepository;
import org.live.school.service.MajorService;
import org.live.school.vo.MajorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Service(value = "majorService")
public class MajorServiceImpl extends BaseServiceImpl<Major, String>implements MajorService{

@Autowired
 private  MajorRepository majorRepository;


@Override
public BaseRepository<Major,String> getRepository(){
    return majorRepository;
}


public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = majorRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<MajorVo> page = majorRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


@Override
public List<Major> findAll(){
    return majorRepository.findByEnableFlag(true);
}


}