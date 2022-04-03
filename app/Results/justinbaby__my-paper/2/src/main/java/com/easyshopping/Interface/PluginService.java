package com.easyshopping.Interface;
public interface PluginService {

   public PaymentPlugin getPaymentPlugin(String id);
   public List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);
}