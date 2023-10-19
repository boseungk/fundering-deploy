package com.theocean.fundering.global.utils;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.theocean.fundering.global.errors.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AWSS3Uploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadToS3(MultipartFile mFile) {
        String fileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();

        ObjectMetadata omd = new ObjectMetadata();
        try {
            omd.setContentLength(mFile.getInputStream().available());
            amazonS3Client.putObject(bucket, fileName, mFile.getInputStream(), omd);
        }catch (Exception e){
            throw new Exception500("FileInputStream 생성 오류");
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
