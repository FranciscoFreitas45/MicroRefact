package com.zammc.service.restaurant;
 import com.zammc.domain.restaurant.RestaurantEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface RestaurantService {


public void queryRestaurantPage(PageBean pageBean)
;

public void editRestaurantRest(RestaurantEntity restaurantEntity)
;

public RestaurantEntity queryRestaurantById(RestaurantEntity restaurantEntity)
;

public Message editRestaurant(RestaurantEntity restaurantEntity,HttpServletRequest request)
;

public void editRestaurantBusiness(RestaurantEntity restaurantEntity)
;

}