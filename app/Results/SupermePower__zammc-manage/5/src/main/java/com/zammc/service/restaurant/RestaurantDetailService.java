package com.zammc.service.restaurant;
 import com.zammc.domain.restaurant.RestaurantPropertyEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface RestaurantDetailService {


public void deleteRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity)
;

public Message addRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity,HttpServletRequest request)
;

public RestaurantPropertyEntity queryRestaurantPropertyById(RestaurantPropertyEntity restaurantPropertyEntity)
;

public void queryRestaurantDetailPage(RestaurantPropertyEntity restaurantPropertyEntity,PageBean pageBean)
;

}