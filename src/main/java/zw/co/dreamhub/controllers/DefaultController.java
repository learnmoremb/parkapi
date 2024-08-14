package zw.co.dreamhub.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${info.url.unSecured}")
@Tag(name = "Default Controller", description = "default controller")
public class DefaultController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/default")
    @Operation(summary = "Get application name", description = "Returns the application name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    }),
    })
    public ResponseEntity<String> base() {
        return ResponseEntity.ok(applicationName);
    }

}