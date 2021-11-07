package miniodemo.demo.service;

import io.minio.*;
import lombok.AllArgsConstructor;
import miniodemo.demo.MinioProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;


@Service
@AllArgsConstructor
public class MinioServiceImpl implements MinioService {

    private MinioProperties minioProperties;
    private MinioClient minioClient;

    @Override
    public void saveFile(MultipartFile file) throws Exception {
        String bucketName = minioProperties.getBucket();
        createBucketIfNotExists(bucketName);
        minioClient.putObject(PutObjectArgs.builder().
                bucket(bucketName)
                .object(file.getOriginalFilename())
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());

    }

    @Override
    public InputStream getFile(String name) throws Exception {

        return
                minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(minioProperties.getBucket())
                                .object(name)
                                .build());

    }

    private void createBucketIfNotExists(String bucketName) throws Exception {
        if (!checkIfBucketExists(bucketName)) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        }

    }

    private Boolean checkIfBucketExists(String name) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
    }
}
