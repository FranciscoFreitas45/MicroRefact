package org.live.live.service.impl;
 import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.entity.Report;
import org.live.live.repository.ReportRepository;
import org.live.live.service.ReportService;
import org.live.live.vo.MobileUserVo;
import org.live.live.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service("reportService")
public class ReportServiceImpl implements ReportService{

@Autowired
 private  ReportRepository reportRepository;


@Override
public void updateReport(String id){
    Report report = reportRepository.findOne(id);
    // 设置为已处理
    report.setHandleType(true);
    // 更新记录
    reportRepository.save(report);
}


@Override
public DataTableModel findReportByPage(Map<String,Object> params,boolean handleType){
    // 查询总记录数
    Long recordsTotal = reportRepository.count();
    // 查询分页数据
    Page<ReportVo> page = reportRepository.findReports((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"), handleType);
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


}