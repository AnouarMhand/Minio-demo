package miniodemo.demo.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

public interface MinioService {

    void saveFile(MultipartFile multipartFile) throws Exception;

    InputStream getFile(String name) throws Exception;
}
