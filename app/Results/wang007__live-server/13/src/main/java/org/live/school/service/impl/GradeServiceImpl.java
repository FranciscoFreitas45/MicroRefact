package org.live.school.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseService;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.school.entity.Grade;
import org.live.school.repository.GradeRepository;
import org.live.school.service.GradeService;
import org.live.school.vo.GradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Service(value = "gradeService")
public class GradeServiceImpl extends BaseServiceImpl<Grade, String>implements GradeService{

@Autowired
 private  GradeRepository gradeRepository;


@Override
public BaseRepository<Grade,String> getRepository(){
    return gradeRepository;
}


public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = gradeRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<GradeVo> page = gradeRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


@Override
public List<Grade> findAll(){
    return gradeRepository.findByEnableFlag(true);
}


}