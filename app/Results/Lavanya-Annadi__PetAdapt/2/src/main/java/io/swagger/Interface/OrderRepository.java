package io.swagger.Interface;
public interface OrderRepository {

   public List<Order> findByUser(User user);
}