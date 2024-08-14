package zw.co.dreamhub.domain.mappers.users;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.users.UserRequest;
import zw.co.dreamhub.domain.models.users.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequest userRequest);

    UserRequest toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequest userRequest, @MappingTarget User user);
}