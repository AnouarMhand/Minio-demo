package miniodemo.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
@Validated
public class MinioProperties {

    @NotNull
    private String bucket;
    @NotNull
    private Boolean enabled;
    @NotNull
    private String url;
    @NotNull
    private String accessKey;
    @NotNull
    private String secretKey;
}
