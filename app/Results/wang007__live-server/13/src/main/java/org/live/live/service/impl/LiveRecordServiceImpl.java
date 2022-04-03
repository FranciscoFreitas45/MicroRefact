package org.live.live.service.impl;
 import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.repository.LiveRecordRepository;
import org.live.live.service.LiveRecordService;
import org.live.live.vo.LiveRecordVo;
import org.live.live.vo.MobileUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.live.Interface.LiveRecordRepository;
@Service("liveRecordService")
public class LiveRecordServiceImpl implements LiveRecordService{

@Autowired
 private  LiveRecordRepository liveRecordRepository;


@Override
public DataTableModel findLiveRecordByPage(Map<String,Object> params){
    // 查询总记录数
    Long recordsTotal = liveRecordRepository.count();
    // 查询分页数据
    Page<LiveRecordVo> page = liveRecordRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


}