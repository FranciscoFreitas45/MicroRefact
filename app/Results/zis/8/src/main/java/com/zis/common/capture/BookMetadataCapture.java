package com.zis.common.capture;
 import java.util.List;
import com.zis.bookinfo.util.BookMetadata;
public interface BookMetadataCapture {


public BookMetadata captureDetailPage(String itemId)
;

public List<BookMetadata> captureListPage(String keyword)
;

}