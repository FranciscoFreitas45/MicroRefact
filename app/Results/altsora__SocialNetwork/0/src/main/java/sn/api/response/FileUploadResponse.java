package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileUploadResponse extends AbstractResponse{

 private String id;

 private long ownerId;

 private String fileName;

 private String relativeFilePath;

 private String rawFileURL;

 private String fileFormat;

 private long bytes;

 private String fileType;

 private long createdAt;


}