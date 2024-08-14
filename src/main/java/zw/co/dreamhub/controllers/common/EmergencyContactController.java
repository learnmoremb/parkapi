package zw.co.dreamhub.controllers.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.common.EmergencyContactRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.security.services.AuthUtils;
import zw.co.dreamhub.services.common.EmergencyContactService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/04
 */

@RestController
@RequestMapping("${info.url.secured}/emergency-contact")
@Tag(name = "Emergency - Contact Controller")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService service;
    private final AuthUtils authUtils;
    @PostMapping
    @Operation(summary = "Set Emergency Contact")
    public ResponseEntity<ApiResponse<String>> setEmergencyContact(@Valid @RequestBody EmergencyContactRequest request) {
        var response = service.setEmergencyContact(authUtils.getUser(), request);
        return ResponseUtils.respond(response);
    }
}
