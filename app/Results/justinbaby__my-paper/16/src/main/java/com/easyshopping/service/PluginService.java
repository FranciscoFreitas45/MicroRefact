package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.plugin.PaymentPlugin;
import com.easyshopping.plugin.StoragePlugin;
public interface PluginService {


public List<StoragePlugin> getStoragePlugins(boolean isEnabled)
;

public PaymentPlugin getPaymentPlugin(String id)
;

public List<PaymentPlugin> getPaymentPlugins(boolean isEnabled)
;

public StoragePlugin getStoragePlugin(String id)
;

}