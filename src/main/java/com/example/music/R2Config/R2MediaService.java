package com.example.music.R2Config;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class R2MediaService {
private final S3Client s3Client;
@Value("${cloudflare.r2.bucket-name}")
    private String bucketName;
@Value("${cloudflare.r2.public-domain}")
private String publicDomain;

public String uploadFile(MultipartFile file) {
    String original= Optional.ofNullable(file.getOriginalFilename()).orElseThrow(
            ()->new IllegalArgumentException("File  not found"));

String contentType= Optional.ofNullable(file.getContentType())
        . orElseThrow(()->new IllegalArgumentException("File  not found"));


String ext =  getFileExtension(original);
String folder=switch(ext){
    case "jpg" , "png", "jpeg","gif"->"images";
    case "mp4" , "mov" , "avi"->"video";
    case "mp3" , "wav"->"audio";
    case "pdf" , "doc" ,"docx", "txt"->"file";

    default ->  throw new IllegalArgumentException("File  not found");
};

String key =String.format("%s/%s-%s",folder, UUID.randomUUID(), original);

PutObjectRequest req = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .contentType(contentType)
        .build();

try {
    s3Client.putObject(req, RequestBody.fromBytes(file.getBytes()));
}
catch (Exception e) {
    throw new RuntimeException("Error while uploading file", e);
}
return key;
}


public String updateFile(String oldKey, MultipartFile newFile) {
    if (oldKey !=null && !oldKey.isBlank()&& newFile!=null) {
        try{
            s3Client.deleteObject(DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(oldKey)
                    .build());
        }catch (Exception e){
            throw new RuntimeException("Error failed to update", e);
        }
    }

    return uploadFile(newFile);
}

public  void deleteFile(String oldKey) {
    DeleteObjectRequest request= DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(oldKey)
            .build();
    s3Client.deleteObject(request);
}


public String getPublicUrl(String key){
    return String.format("%s/%s",publicDomain,key);
}

private String getFileExtension (String filename){
    int idx=filename.lastIndexOf('.');
    if (idx<0||idx==filename.length()-1){
        throw new RuntimeException("invalid file extension in filename"+filename);

    }
    return filename.substring(idx+1);
}

}
