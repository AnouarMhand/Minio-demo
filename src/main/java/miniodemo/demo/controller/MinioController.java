package miniodemo.demo.controller;

import lombok.AllArgsConstructor;
import miniodemo.demo.service.MinioService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLConnection;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class MinioController {

    private MinioService minioService;

    @PostMapping
    public void storeFile(@RequestParam("file") MultipartFile file) throws Exception {
        minioService.saveFile(file);
    }

    @GetMapping("/{object}")
    public void getFile(@PathVariable("object") String object, HttpServletResponse response) throws Exception {

        InputStream inputStream = minioService.getFile(object);

        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));
        IOUtils.copy(inputStream, response.getOutputStream());

    }
}
