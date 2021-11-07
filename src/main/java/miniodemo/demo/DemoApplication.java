package miniodemo.demo;

import io.minio.MinioClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public MinioClient minio(MinioProperties minioClientProperties) {
        return MinioClient.builder()
                .endpoint(minioClientProperties.getUrl())
                .credentials(minioClientProperties.getAccessKey(), minioClientProperties.getSecretKey())
                .build();
    }
}
