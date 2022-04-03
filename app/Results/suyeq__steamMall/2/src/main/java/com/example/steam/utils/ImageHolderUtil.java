package com.example.steam.utils;
 import com.example.steam.entity.Image;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ImageHolderUtil {

 private  ThreadLocal<List<Image>> images;

 private  Lock lock;


public List<Image> getImageHolder(){
    lock.lock();
    List<Image> list = images.get();
    lock.unlock();
    return list;
}


public void setImageHolder(List<Image> imageList){
    images.set(imageList);
}


}