package com.ssafy.dksl.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refresh_token", timeToLive = 14 * 24 * 60 * 60)
@Builder
@Getter
@ToString
public class RefreshToken {

    @Id
    private String clientId;
    @Indexed
    private String refreshToken;
}
