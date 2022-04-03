package com.zammc.service.restaurant.impl;
 import com.zammc.common.FileUtil;
import com.zammc.domain.restaurant.RestaurantEntity;
import com.zammc.page.PageBean;
import com.zammc.repository.RestaurantRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.restaurant.RestaurantService;
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
import com.zammc.DTO.PageBean;
@Service
public class RestaurantServiceImpl implements RestaurantService{

@Autowired
 private  RestaurantRepository restaurantRepository;


public void queryRestaurantPage(PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<RestaurantEntity> bannerPage = restaurantRepository.findAll(pageable);
    pageBean.setPageSize(bannerPage.getSize());
    pageBean.setPageNum(bannerPage.getNumber() + 1);
    pageBean.setTotalPage(bannerPage.getTotalPages());
    pageBean.setTotalRecorder(bannerPage.getTotalElements());
    pageBean.setContent(bannerPage.getContent());
}


public void editRestaurantRest(RestaurantEntity restaurantEntity){
    RestaurantEntity one = restaurantRepository.findOne(restaurantEntity.getRestaurantId());
    if (one.getDataStatus() != 1 && one.getStatus() == 1) {
        one.setStatus((byte) 0);
        restaurantRepository.saveAndFlush(one);
    }
}


public RestaurantEntity queryRestaurantById(RestaurantEntity restaurantEntity){
    return restaurantRepository.findOne(restaurantEntity.getRestaurantId());
}


public Message editRestaurant(RestaurantEntity restaurantEntity,HttpServletRequest request){
    if (null == restaurantEntity.getRestaurantName() || "".equals(restaurantEntity.getRestaurantName())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "店家名称不能为空");
    }
    if (null == restaurantEntity.getRestaurantCode() || "".equals(restaurantEntity.getRestaurantCode())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "店家编码不能为空");
    }
    RestaurantEntity one = restaurantRepository.findOne(restaurantEntity.getRestaurantId());
    if ("".equals(restaurantEntity.getRestaurantImg()) || null == restaurantEntity.getRestaurantImg() || !restaurantEntity.getRestaurantImg().equals(one.getRestaurantImg())) {
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
        MultipartFile image = mreq.getFile("image");
        if (image == null) {
            return new Message(MessageStatus.FAIL, MessageTitle.失败, "上传图片不能为空");
        } else {
            String img = "";
            try {
                img = FileUtil.uploadFile(image.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            restaurantEntity.setRestaurantImg(img);
        }
    }
    one.setRestaurantName(restaurantEntity.getRestaurantName());
    one.setRestaurantCode(restaurantEntity.getRestaurantCode());
    one.setRestaurantImg(restaurantEntity.getRestaurantImg());
    restaurantRepository.saveAndFlush(one);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "修改成功");
}


public void editRestaurantBusiness(RestaurantEntity restaurantEntity){
    RestaurantEntity one = restaurantRepository.findOne(restaurantEntity.getRestaurantId());
    if (one.getDataStatus() != 1 && one.getStatus() == 0) {
        one.setStatus((byte) 1);
        restaurantRepository.saveAndFlush(one);
    }
}


}