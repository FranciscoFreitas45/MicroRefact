package com.softserve.edu.Resources.util;
 import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.io.InputStream;
@PropertySource("classpath:fileupload.properties")
public class FileUploadUtility extends FileUpload{


public String uploadFile(MultipartFile file,String code,String username){
    BasicAWSCredentials credentials = new BasicAWSCredentials("HereMustBeAccessKeyForAmazonS3", "HereMustBeSecretKeyForAmazonS3");
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    String bucketName = username;
    if (!s3Client.doesBucketExist(bucketName)) {
        s3Client.createBucket(bucketName);
    }
    String url = "";
    try {
        InputStream is = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        if (file.getContentType().equals("application/pdf")) {
            objectMetadata.setContentType("application/pdf");
            objectMetadata.setContentDisposition("inline");
            code = code + ".pdf";
        } else if (file.getContentType().equals("image/jpeg")) {
            code = code + ".jpg";
        } else {
            code = code + ".png";
        }
        s3Client.putObject(new PutObjectRequest(bucketName, code, is, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        // get a reference to the image object
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, code));
        // add to model
        // redirectAttributes.addAttribute("picUrl",s3Object.getObjectContent().getHttpRequest().getURI().toString());
        System.out.println(s3Object.getObjectContent().getHttpRequest().getURI().toString());
        url = s3Object.getObjectContent().getHttpRequest().getURI().toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return url;
}


}