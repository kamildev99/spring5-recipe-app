package guru.springframework.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;

public interface ImageService {

    void saveImageFile(Long id, MultipartFile file);
    void renderImageFromDB(Long id, HttpServletResponse response);
}
