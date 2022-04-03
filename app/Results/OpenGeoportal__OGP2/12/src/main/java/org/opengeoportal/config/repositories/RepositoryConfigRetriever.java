package org.opengeoportal.config.repositories;
 import java.io.IOException;
import java.util.List;
import org.opengeoportal.config.repositories.RepositoryConfig;
public interface RepositoryConfigRetriever {


public List<RepositoryConfig> load()
;

public List<RepositoryConfig> getConfig()
;

}