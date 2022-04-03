package org.opengeoportal.download;
 import java.util.UUID;
import java.util.concurrent.Future;
public interface DownloadPackager {


public Future<Boolean> packageFiles(UUID requestId)
;

}