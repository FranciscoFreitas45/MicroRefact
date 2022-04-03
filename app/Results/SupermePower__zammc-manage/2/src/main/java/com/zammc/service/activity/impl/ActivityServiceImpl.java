package com.zammc.service.activity.impl;
 import com.zammc.domain.activity.ActivityEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.ActivityRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.zammc.Interface.IdWorker;
@Service
public class ActivityServiceImpl implements ActivityService{

@Autowired
 private  ActivityRepository activityRepository;

@Autowired
 private  IdWorker idWorker;


@Override
public void queryActivityPage(ActivityEntity activityEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<ActivityEntity> activityPage = activityRepository.findAll(ActivitySpecification.where(activityEntity), pageable);
    pageBean.setPageSize(activityPage.getSize());
    pageBean.setPageNum(activityPage.getNumber() + 1);
    pageBean.setTotalPage(activityPage.getTotalPages());
    pageBean.setTotalRecorder(activityPage.getTotalElements());
    pageBean.setContent(activityPage.getContent());
}


@Override
public Message deleteActivity(ActivityEntity activityEntity){
    ActivityEntity one = activityRepository.findOne(activityEntity.getActivityId());
    if (one != null && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        activityRepository.saveAndFlush(one);
    }
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
}


@Override
public Message forbidden(ActivityEntity activityEntity){
    ActivityEntity one = activityRepository.findOne(activityEntity.getActivityId());
    if (one != null && one.getDataStatus() == 0 && one.getStatus() == 0) {
        one.setStatus((byte) 1);
        activityRepository.saveAndFlush(one);
    }
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
}


@Override
public Message addActivity(ActivityEntity activityEntity){
    activityEntity.setActivityId(idWorker.nextId());
    activityRepository.saveAndFlush(activityEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "新增成功");
}


@Override
public Message startUsing(ActivityEntity activityEntity){
    ActivityEntity one = activityRepository.findOne(activityEntity.getActivityId());
    if (one != null && one.getDataStatus() == 0 && one.getStatus() == 1) {
        one.setStatus((byte) 0);
        activityRepository.saveAndFlush(one);
    }
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
}


}