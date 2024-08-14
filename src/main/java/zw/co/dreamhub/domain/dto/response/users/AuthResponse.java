package zw.co.dreamhub.domain.dto.response.users;


import zw.co.dreamhub.domain.models.enums.UserRole;

import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
public record AuthResponse(
        Token token, Set<Role> roles
) {

    public record Token(String access, long expiresIn,
                        String accessTokenType,
                        String refresh, long refreshExpiresIn) {
    }

    public record Role(UserRole type) {
    }


}
