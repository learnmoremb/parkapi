package zw.co.dreamhub.domain.dto.request.driver;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record ImageRequest(
        @NotEmpty Set<@NotNull MultipartFile> images
) {
}