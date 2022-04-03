package com.zammc.service.dining.impl;
 import com.zammc.domain.table.DiningTableEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.DiningTableRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.dining.DiningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.zammc.Interface.IdWorker;
import com.zammc.DTO.PageBean;
@Service
public class DiningTableServiceImpl implements DiningTableService{

@Autowired
 private  IdWorker idWorker;

@Autowired
 private  DiningTableRepository diningTableRepository;


@Override
public void deleteDiningTable(DiningTableEntity diningTableEntity){
    DiningTableEntity one = diningTableRepository.findOne(diningTableEntity.getTableId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        diningTableRepository.saveAndFlush(one);
    }
}


@Override
public Message addDiningTable(DiningTableEntity diningTableEntity){
    if (null == diningTableEntity.getTableCode() || "".equals(diningTableEntity.getTableCode())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "餐位编码不能为空");
    }
    diningTableEntity.setTableId(idWorker.nextId());
    diningTableEntity.setTableStatus((byte) 1);
    diningTableRepository.saveAndFlush(diningTableEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "新增成功");
}


@Override
public void queryDiningTablePage(DiningTableEntity diningTableEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<DiningTableEntity> diningTablePage = diningTableRepository.findAll(DiningTableSpecification.where(diningTableEntity), pageable);
    pageBean.setPageSize(diningTablePage.getSize());
    pageBean.setPageNum(diningTablePage.getNumber() + 1);
    pageBean.setTotalPage(diningTablePage.getTotalPages());
    pageBean.setTotalRecorder(diningTablePage.getTotalElements());
    pageBean.setContent(diningTablePage.getContent());
}


@Override
public Message editDiningTable(DiningTableEntity diningTableEntity){
    if (null == diningTableEntity.getTableCode() || "".equals(diningTableEntity.getTableCode())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "餐位编码不能为空");
    }
    diningTableRepository.saveAndFlush(diningTableEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "修改成功");
}


@Override
public List<DiningTableEntity> indexDiningTableData(){
    return diningTableRepository.indexDiningTableData();
}


@Override
public Long queryFreeTableCount(){
    return diningTableRepository.queryFreeTableCount();
}


@Override
public DiningTableEntity queryDiningTableById(DiningTableEntity diningTableEntity){
    return diningTableRepository.findOne(diningTableEntity.getTableId());
}


}