package com.zammc.service.restaurant.impl;
 import com.zammc.common.FileUtil;
import com.zammc.domain.restaurant.RestaurantEntity;
import com.zammc.domain.restaurant.RestaurantPropertyEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.RestaurantDetailRepository;
import com.zammc.repository.RestaurantRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.restaurant.RestaurantDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.zammc.Interface.IdWorker;
@Service
public class RestaurantDetailServiceImpl implements RestaurantDetailService{

@Autowired
 private  RestaurantDetailRepository restaurantDetailRepository;

@Autowired
 private  RestaurantRepository restaurantRepository;

@Autowired
 private  IdWorker idWorker;


public void deleteRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity){
    RestaurantPropertyEntity one = restaurantDetailRepository.findOne(restaurantPropertyEntity.getId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        restaurantDetailRepository.saveAndFlush(one);
    }
}


public Message addRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity,HttpServletRequest request){
    MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
    MultipartFile image = mreq.getFile("image");
    if (null == restaurantPropertyEntity.getRestaurantProperty() || "".equals(restaurantPropertyEntity.getRestaurantProperty())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "详情名称不能为空");
    }
    if (null == restaurantPropertyEntity.getPropertyMsg() || "".equals(restaurantPropertyEntity.getPropertyMsg())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "详情内容不能为空");
    }
    if (checkPropertyImg(restaurantPropertyEntity, image)) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "图片不能为空");
    }
    RestaurantEntity restaurantEntity = restaurantRepository.findAll().get(0);
    restaurantPropertyEntity.setRestaurantId(restaurantEntity.getRestaurantId());
    restaurantPropertyEntity.setId(idWorker.nextId());
    restaurantDetailRepository.saveAndFlush(restaurantPropertyEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "新增成功");
}


public RestaurantPropertyEntity queryRestaurantPropertyById(RestaurantPropertyEntity restaurantPropertyEntity){
    return restaurantDetailRepository.findOne(restaurantPropertyEntity.getId());
}


public boolean checkPropertyImg(RestaurantPropertyEntity restaurantPropertyEntity,MultipartFile image){
    if (image == null) {
        return true;
    } else {
        String img = "";
        try {
            img = FileUtil.uploadFile(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        restaurantPropertyEntity.setPropertyImg(img);
    }
    return false;
}


public void queryRestaurantDetailPage(RestaurantPropertyEntity restaurantPropertyEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<RestaurantPropertyEntity> all = restaurantDetailRepository.findAll(RestaurantDetailSpecification.where(restaurantPropertyEntity), pageable);
    pageBean.setPageSize(all.getSize());
    pageBean.setPageNum(all.getNumber() + 1);
    pageBean.setTotalPage(all.getTotalPages());
    pageBean.setTotalRecorder(all.getTotalElements());
    pageBean.setContent(all.getContent());
}


}