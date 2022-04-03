package run.halo.app.handler.file.SmmsFileHandler;
 import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.exception.ServiceException;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.properties.SmmsProperties;
import run.halo.app.model.support.HaloConst;
import run.halo.app.model.support.UploadResult;
import run.halo.app.service.OptionService;
import run.halo.app.utils.FilenameUtils;
import run.halo.app.utils.HttpClientUtils;
@Data
@NoArgsConstructor
public class SmmsResponseData {

 private  Integer width;

 private  Integer height;

 private  String filename;

 private  String storename;

 private  Integer size;

 private  String path;

 private  String hash;

 private  String url;

 private  String delete;

 private  String page;


}