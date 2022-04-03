package com.fosun.fc.projects.creepers.pipeline;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
@Transactional
@Component("basePipeline")
public class BasePipeline extends FilePersistentBaseimplements Pipeline{

@Autowired
 protected  ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

@Autowired
 protected  ICreepersListService creepersListServiceImpl;

public BasePipeline() {
    setPath("/data/webmagic/");
}public BasePipeline(String path) {
    setPath(path);
}
public ICreepersExceptionHandleService getCreepersExceptionHandleServiceImpl(){
    return creepersExceptionHandleServiceImpl;
}


@Override
public void process(ResultItems resultItems,Task task){
}


public void setCreepersExceptionHandleServiceImpl(ICreepersExceptionHandleService creepersExceptionHandleServiceImpl){
    this.creepersExceptionHandleServiceImpl = creepersExceptionHandleServiceImpl;
}


}