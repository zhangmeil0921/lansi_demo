package demo.web.service.filter;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zml
 * @createDate 2021-07-23
 */
public interface QiniuStorageService {
    String uploadVideo(MultipartFile picture);
}
