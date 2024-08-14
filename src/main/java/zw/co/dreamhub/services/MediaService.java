package zw.co.dreamhub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.co.dreamhub.config.env.InfoEnv;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.models.common.Media;
import zw.co.dreamhub.domain.repositories.common.MediaRepository;
import zw.co.dreamhub.exception.ValidationException;
import zw.co.dreamhub.services.driver.ImageOperations;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */

@Service
@Slf4j
public class MediaService {

    private final InfoEnv infoEnv;
    private final MediaRepository mediaRepository;

    public MediaService(InfoEnv infoEnv, MediaRepository mediaRepository) {
        this.infoEnv = infoEnv;
        this.mediaRepository = mediaRepository;
    }

    private String saveFile(MultipartFile document) {
        String home = System.getProperty("user.dir");
        String path = home + File.separator + infoEnv.media().dir();

        File storage = new File(path);
        if (!storage.exists()) {
            boolean directoryCreated = storage.mkdirs();
            log.info("Media directory created: {}", directoryCreated);
        }

        String fileName = document.getOriginalFilename();
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        try {
            document.transferTo(file);
            return filePath;
        } catch (IOException e) {
            log.error("File storage exception: {}", e.getMessage());
            return null;
        }
    }

    public boolean validateImageFormats(MultipartFile file) {
        String png = "image/png";
        String jpg = "image/jpg";
        Set<String> formats = Set.of(png, jpg);
        return formats.stream().anyMatch(format -> format.equals(file.getContentType()));
    }


    public <T, R extends JpaRepository<T, String>, I extends ImageOperations<T>> ApiResponse<String> uploadImages(
            Set<MultipartFile> files, T entity, R repository, I imageOperations) {
        Set<Boolean> imagesValid = files.stream()
                .map(this::validateImageFormats)
                .collect(Collectors.toSet());

        if (imagesValid.contains(Boolean.FALSE)) {
            throw new ValidationException("Invalid image formats");
        }

        Set<Media> images = files.stream()
                .map(file ->{
                 Media media = new Media(saveFile(file),file.getContentType(),file.getOriginalFilename());
                 return mediaRepository.save(media);
                })
                .collect(Collectors.toSet());


        if (!images.isEmpty()) {
            imageOperations.addImages(entity, images);
            repository.save(entity);
        }

        return ResponseUtils.created(StringUtils.SUCCESS);
    }


}
