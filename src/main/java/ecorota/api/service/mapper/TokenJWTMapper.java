package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.TokenJWTResponse;

public class TokenJWTMapper {

    public TokenJWTResponse parse(String token) {
        return new TokenJWTResponse(token);
    }
}
