package com.ushahidi.swiftriver.core.scheduling;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.dao.DropDao;
import com.ushahidi.swiftriver.core.api.service.DropIndexService;
import com.ushahidi.swiftriver.core.model.Drop;
public class DropIndexingTask {

@Autowired
 private  DropDao dropDao;

@Autowired
 private  DropIndexService dropIndexService;

 private  Properties indexerProperties;

 private  File propertiesFile;

 private  String lastDropIdPropertyKey;

 private  String batchSizePropertyKey;

 final  Logger logger;


public void setIndexerProperties(Properties indexerProperties){
    this.indexerProperties = indexerProperties;
}


public void setBatchSizePropertyKey(String batchSizePropertyKey){
    this.batchSizePropertyKey = batchSizePropertyKey;
}


public void setLastDropIdPropertyKey(String lastDropIdPropertyKey){
    this.lastDropIdPropertyKey = lastDropIdPropertyKey;
}


public void setPropertiesFile(File propertiesFile){
    this.propertiesFile = propertiesFile;
}


@Transactional
public void updateDropIndex() throws IOException{
    int batchSize = Integer.parseInt(indexerProperties.getProperty(batchSizePropertyKey));
    long lastDropId = Long.parseLong(indexerProperties.getProperty(lastDropIdPropertyKey));
    List<Drop> drops = dropDao.findAll(lastDropId, batchSize);
    if (drops.isEmpty()) {
        logger.info("No drops found");
        return;
    }
    // Add drops to search index
    dropIndexService.addAllToIndex(drops);
    // Get the max drop id from the returned list
    lastDropId = drops.get(drops.size() - 1).getId();
    logger.info("Saving ID of last indexed drop {}", lastDropId);
    indexerProperties.setProperty(lastDropIdPropertyKey, Long.toString(lastDropId));
    OutputStream outputStream = new FileOutputStream(propertiesFile);
    indexerProperties.store(outputStream, null);
}


}