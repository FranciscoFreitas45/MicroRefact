package com.gbcom.system.utils.jackson;
 import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ObjectMapperFactory {

 private  ObjectMapper objectMapper;


public ObjectMapper getObjectMapper(ClassesPostProcessor classesPostProcessor,JsonSerializer jsonSerializer){
    if (objectMapper == null) {
        objectMapper = new HibernateAwareObjectMapper();
    // objectMapper=new ObjectMapper();
    // SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
    // Set<Class<?>> achiveClasses = classesPostProcessor.getAchiveClasses();
    // for (Class<?> achiveClass : achiveClasses) {
    // testModule=testModule.addSerializer(achiveClass,jsonSerializer);
    // }
    // objectMapper.registerModule(testModule);
    }
    return objectMapper;
}


}