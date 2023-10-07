package ecorota.api.mapper;

import ecorota.api.dto.response.TokenJWTResponse;

public class TokenJWTMapper {

    public TokenJWTResponse parse(String token) {
        return new TokenJWTResponse(token);
    }
}
