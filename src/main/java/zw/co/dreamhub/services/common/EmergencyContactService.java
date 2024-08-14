package zw.co.dreamhub.services.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.common.EmergencyContactRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.driver.EmergencyContactMapper;
import zw.co.dreamhub.domain.models.common.EmergencyContact;
import zw.co.dreamhub.domain.models.users.User;
import zw.co.dreamhub.domain.repositories.common.EmergencyContactRepository;
import zw.co.dreamhub.domain.repositories.users.UserDetailRepository;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/20
 */

@Service
@RequiredArgsConstructor
public class EmergencyContactService {

    private final UserDetailRepository userDetailRepository;
    private final EmergencyContactMapper mapper;
    private final EmergencyContactRepository emergencyContactRepository;


    // todo : view emergency contacts with pagination

    public ApiResponse<String> setEmergencyContact(User user, EmergencyContactRequest request) {

        EmergencyContact contact = mapper.toEntity(request);
        contact.setUser(user);

        userDetailRepository.save(contact.getContact());
        emergencyContactRepository.save(contact);

        return ResponseUtils.created(StringUtils.SUCCESS);
    }
}
